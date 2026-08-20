# Day 24 — Global-Scale Key-Value Store
## Topic: Designing DynamoDB / Cassandra from Scratch

---

## 🎯 What to Learn Today

- DynamoDB internals: partition keys, sort keys, consistent hashing
- Cassandra data modeling: partition key + clustering key
- Eventual consistency tradeoffs in distributed KV stores
- Vector clocks for conflict detection
- Gossip protocol for node discovery and failure detection
- Bloom filters for membership testing

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Amazon DynamoDB Paper (Dynamo) | https://www.allthingsdistributed.com/files/amazon-dynamo-sosp2007.pdf | 25 min |
| Cassandra Data Modeling | https://cassandra.apache.org/doc/latest/cassandra/data_modeling/ | 20 min |
| Gossip Protocol Explained | https://www.youtube.com/@ByteByteGo | 15 min |
| Vector Clocks | https://medium.com/system-design-blog | 15 min |

---

## 🧠 Key Concepts

- **Partition Key**: Hash of this key determines which node stores the data. Must be high cardinality.
- **Sort Key (Range Key)**: Secondary key within a partition. Enables range queries within a partition.
- **Replication Factor (N)**: Each item is stored on N nodes. Standard: N=3.
- **Write Quorum (W)**: Number of nodes that must acknowledge a write. W=2 of 3.
- **Read Quorum (R)**: Number of nodes that must respond for a read. R=2 of 3.
- **Strong Consistency**: R + W > N ensures reads always see the latest write. (2+2=4 > 3)
- **Eventual Consistency**: R + W ≤ N — reads may return stale data but better availability.
- **Gossip Protocol**: Nodes randomly share state with each other — failure detection propagates in O(log N) rounds.
- **Bloom Filter**: Probabilistic data structure. Says "definitely not in set" or "probably in set". No false negatives.

---

## 🔢 Consistency Tuning

```
N=3 (replication factor)

Strong Consistency: W=2, R=2 → R+W=4 > N=3 ✅
  - Always reads latest write
  - Higher latency (must wait for 2 acks)
  - Less available (if 2 nodes down → fail)

Eventual Consistency: W=1, R=1 → R+W=2 ≤ N=3
  - May read stale data
  - Fastest reads and writes
  - Highest availability

Typical production: W=2, R=1 (fast reads, durable writes)
```

---

## 🧩 Problem Statement to Solve

> **Design Amazon DynamoDB (Simplified)**
> - A globally distributed key-value store used by Amazon itself
> - 99.999% availability SLA (5 nines = ~5 minutes downtime/year)
> - Single-digit millisecond reads and writes at any scale (from 1 req/s to 1M req/s)
> - Support both strong and eventual consistency per-request choice
> - Multi-region active-active replication
> - No hot partition problem — any partition key can handle any load

**KV Store Design Task:**
1. How does consistent hashing determine which node handles `key = "user:123"`?
2. With N=3, W=2, R=2: user writes their profile. Which 3 nodes get the write? How does read quorum work?
3. Network partition splits the 3 nodes: Node1 and Node2 on one side, Node3 on other. What happens?
4. Two clients update same key simultaneously. How does vector clock detect and resolve the conflict?
5. How does gossip protocol detect that Node2 has failed?

**Architecture Questions to Answer:**
- How does DynamoDB handle hot partitions? (adaptive capacity, request routing)
- How does DynamoDB's LSM tree (write-optimized storage) work?
- How does a Bloom filter help avoid unnecessary disk reads?

---

## 💬 Interview Q&A

### Q1: What is the CAP theorem and how does it apply to DynamoDB?
**Ans:** CAP: a distributed system can guarantee only 2 of 3: Consistency (all nodes see same data), Availability (system always responds), Partition Tolerance (works despite network splits). Since network partitions are unavoidable, choose CA or CP or AP. DynamoDB by default is AP (available during partitions, eventually consistent). With strong consistency option, it's CP for that request.

### Q2: What are vector clocks and why are they used?
**Ans:** Vector clocks track the causal history of updates. Each node maintains a counter per node. On write: increment own counter. On merge: take max of each node's counter. If two versions have incomparable vector clocks → conflict detected. Client or application resolves conflict. Used by Riak, Amazon Shopping Cart (keep all conflicting versions, merge on read).

### Q3: How does the Gossip protocol work?
**Ans:** Each node periodically (every 1 second) randomly picks another node and exchanges state (which nodes it knows about, their last-seen timestamps). Failed nodes' timestamps stop updating — detected as failed after N rounds without update. Failure information propagates to all nodes in O(log N) gossip rounds — fast and decentralized.

### Q4: What is an LSM tree and why is it used in KV stores?
**Ans:** Log-Structured Merge tree: writes go to in-memory memtable first (fast). Memtable flushed to immutable SSTables on disk periodically. Reads check memtable + SSTables (using Bloom filters to skip ones that don't have the key). Background compaction merges and sorts SSTables. Used by LevelDB, RocksDB, Cassandra. Optimizes write throughput at cost of read amplification.

---

## 🗒️ Quick Cheat Sheet
- **SSTable**: Sorted String Table — immutable, on-disk key-value store sorted by key
- **Memtable**: In-memory write buffer (WAL backed for durability)
- **Compaction**: Merging SSTables, removing deleted/old versions — expensive but necessary
- **Bloom Filter**: 1% false positive rate uses ~10 bits per element. Cheap in memory.
- **Hinted Handoff**: If target node is down, another node stores the write temporarily and delivers when target recovers

---

## 📓 Your Notes
*Design the simplified DynamoDB. Draw the read/write path with consistent hashing and replication.*
