# Day 14 — Event-Driven Architecture & Apache Kafka
## Topic: Building Systems Around Events Instead of APIs

---

## 🎯 What to Learn Today

- Event-Driven Architecture (EDA) principles
- Event Sourcing pattern — store state as a sequence of events
- Apache Kafka deep dive: brokers, topics, partitions, consumer groups, offsets
- Exactly-once semantics in Kafka (transactions)
- Stream processing with Kafka Streams / Apache Flink
- CQRS (Command Query Responsibility Segregation)

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Kafka in 5 Minutes | https://www.youtube.com/watch?v=PzPXRmVHMxI | 5 min |
| Event Sourcing — ByteByteGo | https://www.youtube.com/@ByteByteGo | 20 min |
| Kafka Docs | https://kafka.apache.org/documentation/ | 20 min |
| CQRS Pattern | https://microservices.io/patterns/data/cqrs.html | 15 min |

---

## 🧠 Key Concepts

- **Event-Driven Architecture**: Services communicate via events (asynchronously) instead of direct API calls
- **Event**: An immutable record of something that happened ("OrderPlaced", "PaymentProcessed")
- **Event Sourcing**: Instead of storing current state, store the log of all events. Rebuild state by replaying.
- **Kafka Topic**: Named stream of events (like a log file that multiple readers can read)
- **Kafka Partition**: Topic is split into partitions for parallelism. Events within a partition are ordered.
- **Consumer Group**: N consumers sharing topic consumption. Each partition → exactly one consumer in group.
- **Offset**: Position of a consumer in a partition. Consumer commits offsets to track progress.
- **CQRS**: Separate write model (commands) from read model (queries). Write to event log, derive read-optimized views.

---

## 🔢 Kafka Architecture

```
Producers → Kafka Cluster → Consumer Groups

Kafka Cluster:
  - Multiple Brokers (servers)
  - Topics split into Partitions
  - Each Partition replicated across N brokers
  - ZooKeeper/KRaft for cluster coordination

Consumer Group A (Order Service):
  Partition 0 → Consumer A1
  Partition 1 → Consumer A2
  Partition 2 → Consumer A3

Consumer Group B (Analytics Service):
  Partition 0 → Consumer B1  (reads same events independently)
  Partition 1 → Consumer B1
  Partition 2 → Consumer B2
```

---

## 🧩 Problem Statement to Solve

> **Design an Order Processing System for E-Commerce**
> - 1 million orders per day; handle flash sale spikes (100x normal = 100M orders in 1 hour)
> - Order lifecycle: placed → payment verified → inventory reserved → shipped → delivered
> - Each lifecycle step is a different microservice
> - Must not lose orders even if services crash
> - Support replaying events for debugging and audit
> - Generate real-time analytics: orders per minute, revenue per hour

**Kafka Design Task:**
1. Define Kafka topics: `orders`, `payments`, `inventory`, `shipping` — or one topic per state change?
2. What is the partition key for the `orders` topic? (order_id for ordering, or user_id for per-user ordering)
3. Order payment service is down. What happens to orders in Kafka? (they wait, not lost)
4. How do you ensure a payment is never processed twice? (consumer idempotency + unique payment_id)
5. How does the analytics dashboard get real-time metrics? (Kafka Streams reading events)

**Architecture Questions to Answer:**
- Event Sourcing: how do you rebuild the order state from events?
- CQRS: how does read model for "order history" differ from the event log?
- Exactly-once vs at-least-once: which for payment processing?

---

## 💬 Interview Q&A

### Q1: What is the difference between Event-Driven Architecture and microservices with REST APIs?
**Ans:** REST: synchronous, direct service-to-service calls — caller waits for response, services tightly coupled. EDA: services publish events to a broker (Kafka) — caller returns immediately, services are decoupled. EDA enables temporal decoupling (services can be down and catch up later) and makes systems more resilient.

### Q2: What is Event Sourcing?
**Ans:** Instead of persisting the current state (e.g., `order.status = "shipped"`), you persist a sequence of events ("OrderPlaced", "PaymentVerified", "ItemShipped"). Current state is derived by replaying events. Benefits: complete audit trail, ability to replay to fix bugs, temporal queries ("what was the order status at 3pm yesterday?").

### Q3: How does Kafka guarantee ordering within a partition?
**Ans:** Within a single partition, Kafka maintains strict event ordering — events are written and read in the order they were produced. Across partitions, there is NO ordering guarantee. Therefore, if you need all events for a specific order to be ordered, use order_id as the partition key — all events for that order go to the same partition.

### Q4: What is CQRS?
**Ans:** Command Query Responsibility Segregation separates writes (commands) from reads (queries). Write side: events published to Kafka. Read side: different consumers build optimized read models (e.g., materialized views in Redis or Elasticsearch). A query like "get order history" doesn't go to the event log — it reads the pre-built read model.

---

## 🗒️ Quick Cheat Sheet
- **Kafka retention**: Default 7 days. Can replay any event in this window.
- **Partition count**: More partitions = more parallelism (but more overhead). Start with 6-12.
- **Replication factor**: 3 (survives 2 broker failures)
- **Consumer lag**: How far behind consumer is. High lag = consumer can't keep up with producers.
- **Compacted topic**: Keeps only the latest event per key — perfect for state/changelog topics

---

## 📓 Your Notes
*Design the order processing system. Define all Kafka topics, partition keys, and consumer groups.*
