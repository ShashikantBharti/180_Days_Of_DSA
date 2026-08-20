# Day 26 — Design Uber / Real-Time Location System
## Topic: Full System Design — Ride-Hailing at 100 Million Trips/Day

---

## 🎯 Today's Goal

Design Uber end-to-end in 45 minutes. Set a timer and design before reading the reference.

---

## 📐 Requirements

**Functional:**
- Riders request trips; matched to nearby available driver
- Track driver location every 4 seconds (real-time GPS updates)
- Dynamic surge pricing based on supply/demand ratio per zone
- Trip lifecycle: requested → driver accepted → in-progress → completed → rated
- Driver can accept/reject trip requests
- ETA computation for pickup and drop-off

**Non-Functional:**
- 10 million concurrent drivers online
- Match rider to driver within 5 seconds of request
- Handle 10 million location updates per 4 seconds = 2.5M writes/second
- 99.99% availability; location data eventual consistency is acceptable

---

## 🧠 Key Design Decisions

### Driver Location Ingestion
- Drivers send GPS coordinates every 4 seconds via WebSocket or HTTP
- Locations flow: Driver App → Kafka (2.5M writes/sec) → Location Consumer → Redis + Cassandra
- **Redis**: Store current driver location (GeoSpatial) — for nearby driver lookup (hot path)
- **Cassandra**: Store location history per trip — for billing and audit

### Geospatial Search (Finding Nearby Drivers)
- **GeoHash**: Divide earth into rectangular cells. Drivers in same cell are "nearby". Expand to neighboring cells if no drivers found.
- **Redis GEOADD / GEORADIUS**: Redis native geospatial commands. Store driver location, search by radius.
- **S2 Geometry (Google)**: Hierarchical grid used by Uber. More accurate, handles edge cases at cell boundaries.

### Trip Matching
- Rider requests trip → Trip Service creates pending trip
- Supply Service queries Redis for available drivers within 5km radius
- Sort candidates by ETA (not just distance — traffic matters)
- Offer trip to best candidate → driver accepts/rejects (timeout 10s) → retry next candidate

### Surge Pricing
- Surge Service polls supply/demand per H3 hexagon zone every 30 seconds
- Supply: available drivers in zone. Demand: outstanding trip requests in zone.
- Surge multiplier = f(demand/supply ratio) — pre-defined function
- Stored in Redis with TTL. Rider sees surge price before confirming.

### Trip State Machine
```
REQUESTED → DRIVER_ACCEPTED → DRIVER_ARRIVED → IN_PROGRESS → COMPLETED
     ↓ (no drivers)
   CANCELLED
```

---

## 📊 Data Model

```sql
-- Trips Table (PostgreSQL)
CREATE TABLE trips (
  trip_id    UUID PRIMARY KEY,
  rider_id   UUID,
  driver_id  UUID,
  status     TEXT,     -- requested, accepted, in_progress, completed
  pickup_lat FLOAT,
  pickup_lng FLOAT,
  dest_lat   FLOAT,
  dest_lng   FLOAT,
  fare       DECIMAL,
  surge_mult FLOAT,
  start_time TIMESTAMP,
  end_time   TIMESTAMP
);

-- Driver Locations (Redis GeoSpatial)
GEOADD available_drivers <longitude> <latitude> <driver_id>
GEORADIUS available_drivers <rider_lng> <rider_lat> 5 km ASC COUNT 10
```

---

## 🏗️ Architecture Diagram

```
Driver App (GPS every 4s)
  ↓ WebSocket
Location Service → Kafka (2.5M/s)
  ↓ Consumer
Redis GeoSpatial + Cassandra (location history)

Rider App requests trip
  ↓
Trip Service → Supply Service (query Redis for nearby drivers)
  ↓ GEORADIUS
Sorted driver candidates → Match Service
  ↓ offer via WebSocket
Driver accepts → Trip Status updated in PostgreSQL
  ↓
Trip In Progress → Driver location tracked → ETA Service → Rider App
  ↓ Trip Completed
Fare Calculation Service → Payment Service → Notification Service
```

---

## 💬 Interview Q&A

### Q1: How do you find nearby drivers efficiently?
**Ans:** Redis GeoSpatial data type stores driver locations as geohash internally. `GEORADIUS` command returns all drivers within N km in O(n) where n is number of results. For high load: partition drivers by city into separate Redis keys. Alternatively, use GeoHash to find drivers in same or neighboring cells — pure string prefix matching.

### Q2: How do you handle 2.5M location writes per second?
**Ans:** Kafka absorbs the 2.5M/sec write burst (Kafka partitioned by driver_id for ordering per driver). Kafka consumers (Location Workers) process events and update Redis GeoSpatial + Cassandra. Redis handles ~500K writes/sec per node — need Redis cluster with multiple nodes. Driver location in Redis has TTL — stale drivers removed automatically.

### Q3: How does surge pricing work?
**Ans:** Earth divided into H3 hexagonal zones. Every 30 seconds, Surge Service computes: active riders requesting / active available drivers in each zone. If ratio > threshold → surge multiplier = 1.2x, 1.5x, 2x, etc. Surge prices stored in Redis (30s TTL). Before rider confirms trip, app fetches surge for their pickup zone.

### Q4: How do you handle a driver who doesn't respond to a trip request?
**Ans:** Trip request sent to driver with 10-second timeout. If driver doesn't respond (or rejects), the match service moves to the next best candidate. Trip is offered to at most K drivers before it's marked "no driver found." Driver who times out is temporarily marked lower priority in future matching.

---

## 🗒️ Quick Cheat Sheet
- **2.5M location writes/sec**: Kafka → batch to Cassandra, individual to Redis
- **GeoHash**: 6-char = ~1.2km precision. Good for city-level matching.
- **H3 (Uber's hexagonal grid)**: Uber open-sourced this — hexagons are more uniform than rectangles
- **ETA**: Calculated by routing service (Google Maps API or custom road graph with live traffic)
- **Driver matching radius**: Start 1km, expand to 3km, 5km if no drivers found

---

## 📓 Your Notes — Self Assessment
Rate yourself: 🔴 Missed key components | 🟡 Partial design | 🟢 Complete design

*Document what geospatial approach you used and why.*
