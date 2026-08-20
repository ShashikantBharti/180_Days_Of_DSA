# Day 09 — Consistent Hashing
## Topic: Distributing Data Evenly Across Distributed Nodes

---

## 🎯 What to Learn Today

- Problem with naive modulo sharding (why n % servers fails when servers change)
- How Consistent Hashing ring works
- Virtual nodes (vnodes) for even load distribution
- How to handle node addition and removal gracefully
- Real-world usage: Cassandra, DynamoDB, Redis Cluster, Memcached

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Consistent Hashing — ByteByteGo | https://www.youtube.com/@ByteByteGo | 20 min |
| Consistent Hashing Visual Demo | https://www.toptal.com/big-data/consistent-hashing | 15 min |
| System Design Primer - Consistent Hashing | https://github.com/donnemartin/system-design-primer | 15 min |
| DynamoDB Partitioning | https://aws.amazon.com/blogs/database/ | 15 min |

---

## 🧠 Key Concepts

- **Naive Modulo**: `server = hash(key) % N`. Problem: when N changes, almost ALL keys remap → massive cache invalidation.
- **Consistent Hashing Ring**: Place servers and keys on a circular ring (0 to 2^32). Each key maps to the next server clockwise.
- **Adding a Node**: Only the keys between the new node and its predecessor are remapped. ~1/N keys move.
- **Removing a Node**: Only keys on the removed node move to the next server. ~1/N keys move.
- **Virtual Nodes (vnodes)**: Each physical server gets multiple positions on the ring (e.g., 150 vnodes/server). Ensures even distribution even with different server capacities.
- **Hot Spot**: One key gets disproportionate traffic. Solution: key range splitting or local cache.

---

## 🔢 Consistent Hashing Math

```
With N servers:
  Naive modulo: Adding 1 server → (N-1)/N of all keys remap
  Consistent hashing: Adding 1 server → ~1/N keys remap

Example with 100 servers:
  Adding 1 server:
    Naive: 99% of keys remap (disaster!)
    Consistent: ~1% of keys remap ✅

Virtual nodes:
  150 vnodes per server → ±2% standard deviation in load
  Without vnodes → some servers can get 10x more data
```

---

## 🧩 Problem Statement to Solve

> **Design a Distributed Cache (like Memcached Cluster)**
> - 1 billion cache entries distributed across 100 nodes
> - Adding or removing nodes should cause minimum cache misses (minimal remapping)
> - Even data distribution across all nodes
> - Handle hot keys (one key getting 90% of traffic)
> - Support nodes with different RAM capacity (some 32GB, some 64GB)

**Consistent Hashing Design Task:**
1. Draw a consistent hashing ring with 4 servers (A, B, C, D) at positions 0, 90, 180, 270.
2. Where does `hash("user:123") = 150` go? (Server C at 180)
3. Server B goes down. Which server gets its keys? (Server C gets them)
4. New server E is added at position 120. Which keys move? (Keys from 90 to 120 move to E)
5. How many vnodes should each server get? Justify your answer.

**Architecture Questions to Answer:**
- How do you handle a hot key (one cache key = 90% of traffic)?
- How does the client know which server has which key?
- What happens during node failure — how is the ring updated?

---

## 💬 Interview Q&A

### Q1: What is the problem with simple modulo-based hashing for distributed caches?
**Ans:** `server = hash(key) % N`. When you add or remove a server, N changes, and almost all keys map to different servers — causing a massive cache miss storm and overwhelming the database. With 100 servers, adding 1 causes 99% of keys to remap.

### Q2: How does consistent hashing solve the remapping problem?
**Ans:** By placing both servers and keys on a circular ring. When a server is added/removed, only the keys that were between that server and its predecessor on the ring need to remap — approximately 1/N of all keys. The rest stay exactly where they are.

### Q3: Why do we use virtual nodes in consistent hashing?
**Ans:** Without vnodes, key distribution is uneven — some servers might get 2-3x more data than others by chance. With vnodes (e.g., 150 per server), each server has many positions on the ring, statistically averaging out to near-equal distribution. Also allows weighted assignment — a server with 2x RAM gets 2x vnodes.

### Q4: How do you handle hot key problem with consistent hashing?
**Ans:** (1) Add local in-process cache at the application level for very hot keys, (2) Replicate hot keys across multiple nodes and randomly route reads, (3) Split the key into N sub-keys (e.g., `user:123:0` through `user:123:9`) and aggregate on read.

---

## 🗒️ Quick Cheat Sheet
- **Hash Function**: MD5 or SHA-1 maps any key to integer 0..2^128
- **Clockwise ring**: Key goes to first server clockwise from its hash position
- **1/N remapping**: Adding 1 server to N-server ring remaps ~1/N keys
- **vnodes**: 100-200 per server is typical in production (Cassandra default: 256)
- **Replication**: Each key replicated to next K servers clockwise (K=3 in Cassandra)

---

## 📓 Your Notes
*Draw the consistent hashing ring. Simulate adding/removing a node and show which keys move.*
