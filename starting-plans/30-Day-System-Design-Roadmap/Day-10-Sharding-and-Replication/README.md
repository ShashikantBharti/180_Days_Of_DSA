# Day 10 — Database Sharding & Replication
## Topic: Scaling Databases Horizontally

---

## 🎯 What to Learn Today

- What is database sharding and why you need it
- Sharding strategies: Range, Hash, Directory-based, Geographic
- Database replication: Master-Slave, Master-Master
- Read replicas for scaling reads
- The hard problems with sharding: cross-shard queries, rebalancing, hotspots
- When NOT to shard (most systems don't need it!)

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Database Sharding — ByteByteGo | https://www.youtube.com/@ByteByteGo | 20 min |
| MySQL Replication Explained | https://dev.mysql.com/doc/refman/8.0/en/replication.html | 15 min |
| Vitess (YouTube's DB sharding) | https://vitess.io/docs/overview/ | 15 min |
| System Design Primer - Database | https://github.com/donnemartin/system-design-primer | 20 min |

---

## 🧠 Key Concepts

- **Sharding**: Split data across multiple DB instances (shards), each owning a subset of data
- **Shard Key**: The column used to determine which shard a row belongs to (choose carefully!)
- **Range Sharding**: Shard 1: user_id 1-1M, Shard 2: 1M-2M. Problem: hotspot if new users all go to latest shard
- **Hash Sharding**: `shard = hash(user_id) % N`. Even distribution but hard to do range queries
- **Directory-based**: Lookup service maps key → shard. Flexible but single point of failure
- **Master-Slave Replication**: Master handles writes, slaves handle reads. Slaves may lag slightly.
- **Master-Master Replication**: Both handle reads and writes. Need conflict resolution.
- **Read Replica**: Async replica of master used only for reads. Reduces read load on master by 10x.

---

## 🔢 When to Shard

```
Try these FIRST (before sharding):
  1. Add read replicas (handles 10x read traffic)
  2. Add caching layer (Redis) → reduces DB load 90%
  3. Vertical scaling (more RAM/CPU)
  4. Query optimization (better indexes)
  5. Archive old data (move to cold storage)

Shard ONLY when:
  - Single DB can't handle write throughput
  - Dataset > 5TB (single disk limitation)
  - Latency requirements can't be met with above solutions
```

---

## 🧩 Problem Statement to Solve

> **Design Twitter's Tweet Storage System**
> - 500 million tweets per day (writes)
> - 10 billion tweet reads per day
> - Tweets retrievable by tweet_id (exact lookup) and by user_id (all of user's tweets)
> - Support timeline generation: get latest N tweets from a set of user IDs
> - Store media references (photos, videos attached to tweets)

**Sharding Design Task:**
1. Shard by user_id or tweet_id? Argue both sides.
2. If shard by user_id: what happens when a celebrity (100M followers) is on one shard?
3. If shard by tweet_id: how do you efficiently get "all tweets by user X"?
4. How many shards do you start with? How do you add more later?
5. How do read replicas help with 10B reads/day?

**Architecture Questions to Answer:**
- Which sharding strategy handles the "celebrity problem" best?
- How does replication factor affect durability?
- For timeline generation (get tweets from 500 followees), how do you avoid 500 cross-shard queries?

---

## 💬 Interview Q&A

### Q1: What is database sharding and when do you need it?
**Ans:** Sharding splits data across multiple database instances, each storing a subset. Needed when: single DB write throughput is maxed out, dataset is too large for one disk, or latency requirements force geographic distribution. Most systems should exhaust read replicas + caching before sharding.

### Q2: What are the problems with database sharding?
**Ans:** (1) Cross-shard queries require scatter-gather (slow), (2) Transactions across shards require distributed transactions (complex), (3) Rebalancing when adding shards is painful, (4) Data hotspots if shard key is poorly chosen, (5) Schema changes must be applied to all shards.

### Q3: What is master-slave replication and what is replication lag?
**Ans:** Master accepts all writes. Slaves asynchronously copy the master's write log (binlog) and apply it. Replication lag = time between master write and slave having it. Typically milliseconds but can be seconds under high write load. Problem: reading from slave right after a write may return stale data.

### Q4: How do you choose a good shard key?
**Ans:** Good shard key: (1) High cardinality (many unique values), (2) Even distribution (no hotspots), (3) Aligns with most common query pattern (avoid cross-shard queries), (4) Doesn't change over time. Bad example: shard by status (active/inactive) — huge hotspot on 'active'.

---

## 🗒️ Quick Cheat Sheet
- **Shard**: One DB instance holding a subset of data
- **Replication Factor**: N = number of copies (3 is standard for durability)
- **Read Replica Lag**: < 100ms normally, can spike during heavy writes
- **Hotspot**: One shard getting disproportionate load — rethink shard key
- **Vitess**: MySQL sharding middleware used by YouTube, Slack

---

## 📓 Your Notes
*Design the tweet storage schema. Decide on sharding strategy and justify your choice.*
