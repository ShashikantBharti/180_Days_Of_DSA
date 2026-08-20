# Day 21 — Geo-Distributed Systems
## Topic: Building Systems That Work for Every User on Earth

---

## 🎯 What to Learn Today

- Multi-region vs single-region architecture
- Active-Active vs Active-Passive multi-region setup
- Conflict resolution in geo-distributed writes
- Global load balancing (GeoDNS, Anycast)
- Data sovereignty and compliance (GDPR)
- Latency optimization strategies for global users

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Multi-Region Architecture — AWS | https://aws.amazon.com/blogs/architecture/ | 20 min |
| Anycast Routing Explained | https://www.cloudflare.com/learning/cdn/glossary/anycast-network/ | 15 min |
| CockroachDB Geo-Distributed | https://www.cockroachlabs.com/docs/stable/multi-region-overview | 15 min |
| Google Spanner Paper | https://research.google/pubs/pub39966/ | 20 min |

---

## 🧠 Key Concepts

- **Active-Passive**: One region handles all traffic (primary). Standby region activates if primary fails. Simple, but standby is wasted cost + failover takes time.
- **Active-Active**: Multiple regions handle traffic simultaneously. User routed to nearest region. More complex, need conflict resolution for writes.
- **GeoDNS**: DNS resolves to nearest datacenter IP based on user's IP location.
- **Anycast**: Same IP announced from multiple PoPs. BGP routing sends traffic to nearest PoP automatically.
- **Multi-Master Replication**: All regions accept writes. Need conflict resolution (last-write-wins, CRDTs).
- **Read-Your-Writes Consistency**: After you write, your reads see your own writes (even from same region).
- **GDPR/Data Residency**: EU user data must stay in EU. User data pinned to specific regions.

---

## 🔢 Active-Active vs Active-Passive

```
Active-Passive:
  Region A (Primary): handles all traffic
  Region B (Standby): warm standby, ready to take over
  Failover time: 30 seconds to minutes
  Cost: Region B mostly idle
  Consistency: Simple (one writer)

Active-Active:
  Region A: handles EU + Asia traffic
  Region B: handles US traffic
  Failover time: seconds (just DNS change)
  Cost: Both regions fully utilized
  Consistency: Complex (multiple writers — conflicts possible)
```

---

## 🧩 Problem Statement to Solve

> **Design a Global CDN / Edge Computing Platform (like Cloudflare)**
> - Serve 10 trillion HTTP requests per month globally
> - Route each user to the nearest PoP (< 10ms routing decision)
> - DDoS mitigation: absorb 1 Tbps+ attack at the edge
> - Cache invalidation: push updates to 300+ global PoPs within 10 seconds
> - Edge compute: run user code at the PoP (like Cloudflare Workers)

**Geo-Distribution Design Task:**
1. How does GeoDNS work? User in Mumbai → which PoP IP is returned?
2. Anycast: How can 300 PoPs all have the same IP address? (BGP routing selects nearest)
3. DDoS attack with 10M req/sec from botnets: how does the edge absorb it?
4. Cache invalidation: an image is updated → how do you purge it from 300 PoPs in 10 seconds?
5. Data sovereignty: EU user's data must never leave EU region. How do you enforce this?

**Architecture Questions to Answer:**
- How does edge computing (running code at PoP) reduce latency for dynamic content?
- What is the difference between full-page caching and partial page caching?
- How do you handle a PoP being completely taken offline?

---

## 💬 Interview Q&A

### Q1: What is the difference between GeoDNS and Anycast?
**Ans:** GeoDNS: DNS resolves to different IP addresses based on user's geolocation. Different PoPs have different IPs. Works at application layer. Anycast: Same IP address is announced by multiple PoPs via BGP routing. Network infrastructure routes to nearest PoP automatically. Works at network layer — more reliable, no DNS TTL delay.

### Q2: How do you handle write conflicts in Active-Active multi-region setups?
**Ans:** Several strategies: (1) Last-Write-Wins (LWW): most recent timestamp wins — risk of losing concurrent writes. (2) CRDT: data structures that merge without conflicts. (3) Application-level conflict resolution: surface conflicts to application to resolve domain-specifically. (4) Primary-region for writes: for the user's "home" region, always write to their primary region.

### Q3: What is data sovereignty and how does it affect system design?
**Ans:** Data sovereignty means data must be stored and processed within certain geographic boundaries (GDPR requires EU citizen data to stay in EU). Design impact: (1) Route EU users only to EU servers, (2) Cross-region replication must not copy EU data to non-EU regions, (3) Separate data stores per jurisdiction, (4) Geo-fenced API Gateway routing.

### Q4: How do large-scale DDoS attacks get mitigated at the edge?
**Ans:** Anycast absorbs attack traffic across all PoPs (attack distributed across 300 locations instead of hitting one). Edge rate limiting drops requests from known bad IPs. ML-based traffic classification distinguishes bots from humans. Challenge pages (CAPTCHA) for suspicious traffic. BGP blackholing for massive volumetric attacks. Most attack traffic never reaches the origin.

---

## 🗒️ Quick Cheat Sheet
- **RTO (Recovery Time Objective)**: Max acceptable downtime during failover
- **RPO (Recovery Point Objective)**: Max acceptable data loss during failover
- **Active-Active RPO**: Near zero (both regions accepting writes)
- **Active-Passive RPO**: Depends on replication lag (can be seconds to minutes)
- **Google Spanner**: Globally consistent SQL database using atomic clocks (TrueTime) for ordering

---

## 📓 Your Notes
*Draw the global CDN architecture. Show user request routing from Mumbai to nearest PoP.*
