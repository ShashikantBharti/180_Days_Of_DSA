# Day 07 — Message Queues & Async Processing
## Topic: Decoupling Services with Queues and Events

---

## 🎯 What to Learn Today

- Why async processing? What problems do queues solve?
- Message Queue vs Event Streaming — key differences
- RabbitMQ architecture (exchanges, queues, bindings)
- Apache Kafka architecture (topics, partitions, consumer groups)
- At-most-once vs At-least-once vs Exactly-once delivery
- Dead Letter Queue (DLQ) — what happens to failed messages

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Kafka in 100 Seconds | https://www.youtube.com/watch?v=uvb00oaa3k8 | 5 min |
| Message Queue vs Event Streaming | https://www.youtube.com/@ByteByteGo | 20 min |
| RabbitMQ Tutorials | https://www.rabbitmq.com/tutorials | 20 min |
| Kafka Documentation | https://kafka.apache.org/documentation/ | 20 min |

---

## 🧠 Key Concepts

- **Message Queue (RabbitMQ, SQS)**: Producer sends messages, consumer processes them. Message deleted after consumption. Point-to-point or pub/sub.
- **Event Streaming (Kafka)**: Events stored on disk as immutable log. Multiple consumer groups can replay events independently.
- **Producer**: Service that sends messages to queue
- **Consumer**: Service that reads and processes messages from queue
- **Topic/Queue**: Named channel for messages
- **Partition**: Kafka divides topics into partitions for parallel processing
- **Consumer Group**: Multiple consumers share processing of a topic (each partition → one consumer in group)
- **At-least-once**: Message may be delivered multiple times. Consumer must be idempotent.
- **Exactly-once**: Message delivered exactly once (complex to achieve — use Kafka transactions)
- **DLQ**: Failed messages (after N retries) are moved to Dead Letter Queue for inspection

---

## 🔢 RabbitMQ vs Kafka

```
RabbitMQ (Message Queue):
  ✅ Messages consumed and deleted
  ✅ Complex routing (topic exchange, fanout, direct)
  ✅ Good for task queues (job processing)
  ❌ Messages not replayable after consumption
  Use for: Email sending, background jobs, microservice RPC

Kafka (Event Streaming):
  ✅ Events stored durably on disk (days/weeks)
  ✅ Multiple consumers can read same events independently
  ✅ Extremely high throughput (millions of events/sec)
  ✅ Replay events from any point in time
  Use for: Event sourcing, analytics pipeline, audit log, real-time data
```

---

## 🧩 Problem Statement to Solve

> **Design an Email Notification System**
> - Send transactional emails: OTPs, order receipts, password resets, promotional
> - 10 million emails per day, peak 500,000 per hour (flash sales)
> - Guarantee delivery — no lost emails
> - Support retry on failure (email provider might be down temporarily)
> - Track delivery status: sent, delivered, bounced, opened

**Queue Design Task:**
1. Without a queue: what happens when email provider is down? (requests fail directly!)
2. With a queue: draw the flow: API → Queue → Email Worker → Email Provider
3. How many consumer workers do you need for 500K emails/hour?
4. If email sending fails, what happens? How many retries? With what backoff?
5. Where does DLQ fit in this architecture?

**Architecture Questions to Answer:**
- Kafka or RabbitMQ for this use case? Why?
- How to prevent sending the same email twice (idempotency)?
- How to track open/click events from email clients?

---

## 💬 Interview Q&A

### Q1: Why do we use message queues instead of direct service-to-service calls?
**Ans:** Queues provide: (1) **Decoupling** — producer doesn't need to know about consumer, (2) **Buffering** — absorbs traffic spikes without dropping requests, (3) **Retry** — failed messages can be retried automatically, (4) **Async** — producer returns immediately, consumer processes in background.

### Q2: What is the difference between at-least-once and exactly-once delivery?
**Ans:** At-least-once: message is never lost but may be processed multiple times (consumer crashes after processing but before acknowledging). Exactly-once: message processed exactly once — very hard to achieve, requires distributed transactions. Solution: make consumers idempotent (safe to process same message twice).

### Q3: What is a Dead Letter Queue (DLQ)?
**Ans:** A special queue where messages that couldn't be processed after N retries are sent for manual inspection. Prevents bad messages from blocking the queue forever. Engineers can inspect, fix, and replay DLQ messages.

### Q4: How does Kafka ensure high throughput?
**Ans:** (1) Sequential disk writes (much faster than random writes), (2) Topics split into partitions (parallel processing), (3) Batch writing and compression, (4) Zero-copy data transfer to consumers.

---

## 🗒️ Quick Cheat Sheet
- **ACK**: Consumer acknowledges message → queue deletes it
- **NACK**: Consumer rejects message → requeued or DLQ
- **Partition Key**: Determines which Kafka partition a message goes to
- **Consumer Group Offset**: Tracks which messages each consumer group has read
- **Exponential Backoff**: Retry after 1s, 2s, 4s, 8s... to avoid hammering failing service

---

## 📓 Your Notes
*Draw the email system architecture with queue. Define retry strategy and DLQ flow.*
