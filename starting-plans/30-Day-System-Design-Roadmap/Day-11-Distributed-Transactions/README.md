# Day 11 — Distributed Transactions & Saga Pattern
## Topic: Keeping Data Consistent Across Multiple Services

---

## 🎯 What to Learn Today

- Why distributed transactions are hard (the 2-generals problem)
- Two-Phase Commit (2PC) — how it works and why it's problematic
- Saga Pattern: Choreography vs Orchestration
- Compensating transactions (how to "undo" in a saga)
- Idempotency keys — how to safely retry operations
- Outbox Pattern — reliable event publishing

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Saga Pattern — ByteByteGo | https://www.youtube.com/@ByteByteGo | 20 min |
| 2PC vs Saga | https://microservices.io/patterns/data/saga.html | 15 min |
| Idempotency in APIs | https://stripe.com/blog/idempotency | 15 min |
| Outbox Pattern | https://microservices.io/patterns/data/transactional-outbox.html | 15 min |

---

## 🧠 Key Concepts

- **Distributed Transaction**: Transaction spanning multiple services/databases
- **2PC (Two-Phase Commit)**: Phase 1: Coordinator asks all participants "can you commit?" Phase 2: If all say yes → commit; if any say no → rollback. Problem: coordinator is single point of failure, blocking.
- **Saga Pattern**: Break distributed transaction into a sequence of local transactions with compensating actions
- **Choreography Saga**: Each service publishes events; next service listens and reacts. No central coordinator.
- **Orchestration Saga**: Central orchestrator tells each service what to do. Easier to understand and debug.
- **Compensating Transaction**: Undo action for a step (e.g., if payment fails, refund the deducted amount)
- **Idempotency Key**: Unique ID per operation — safe to retry because duplicate detected and ignored

---

## 🔢 Saga Example: Order Flow

```
Order Saga (Orchestration):
  Step 1: Reserve Inventory      → Compensate: Release Inventory
  Step 2: Process Payment        → Compensate: Refund Payment
  Step 3: Assign Delivery Slot   → Compensate: Cancel Slot
  Step 4: Send Confirmation Email → No compensation needed

If Step 3 fails:
  → Refund Payment (compensate step 2)
  → Release Inventory (compensate step 1)
  → Saga complete (order cancelled gracefully)
```

---

## 🧩 Problem Statement to Solve

> **Design a Bank Transfer System**
> - Transfer money between accounts, potentially in different microservices/banks
> - Must be ACID: no money lost, no double charging
> - 100,000 transactions per day
> - Must support rollback if any step fails
> - Handle retries safely (network timeouts should not cause duplicate charges)

**Transaction Design Task:**
1. Why can't you use a single SQL transaction for cross-service money transfer?
2. Design the Saga steps for: Debit Account A → Credit Account B
3. What is the compensating transaction if Credit fails after Debit succeeded?
4. How does an idempotency key prevent double-charging on retry?
5. Design the `transfers` table schema with status tracking.

**Architecture Questions to Answer:**
- Choreography or Orchestration saga for this use case?
- How do you handle a saga that is stuck (orchestrator crashed mid-saga)?
- What is the Outbox Pattern and how does it ensure events are published reliably?

---

## 💬 Interview Q&A

### Q1: What is Two-Phase Commit and what are its problems?
**Ans:** 2PC has a coordinator that asks all participants to prepare (Phase 1), then if all agree, sends commit (Phase 2). Problems: (1) Blocking — if coordinator fails after prepare but before commit, participants hold locks forever, (2) Single point of failure (coordinator), (3) Low throughput due to 2 round trips.

### Q2: What is the Saga pattern?
**Ans:** Instead of one distributed transaction, Saga breaks it into a sequence of local transactions. If a step fails, compensating transactions undo previous steps. No locking across services. Two types: Choreography (event-driven, decentralized) and Orchestration (central coordinator tells services what to do).

### Q3: What is an idempotency key?
**Ans:** A unique client-generated ID (UUID) sent with each request. The server stores this ID after first processing. On retry with same ID, server detects the duplicate and returns the original result without processing again. Critical for payment systems where retrying must not cause double-charge.

### Q4: What is the Outbox Pattern?
**Ans:** Instead of publishing an event directly (which can fail after DB commit), write the event to an outbox table in the SAME DB transaction. A separate poller reads the outbox and publishes events to Kafka/queue. Guarantees events are published exactly when data is committed — no lost events.

---

## 🗒️ Quick Cheat Sheet
- **Compensating Transaction**: The "undo" for a saga step
- **Saga State Machine**: Track saga progress in DB (PENDING, STEP_1_DONE, COMPLETED, FAILED)
- **Idempotency**: Same input → same output, even if called N times
- **Outbox Table**: `(id, event_type, payload, published_at)` — guaranteed event delivery
- **Eventually Consistent**: Saga does not lock; data is consistent eventually, not immediately

---

## 📓 Your Notes
*Design the bank transfer saga. Draw the state machine. Define compensating actions.*
