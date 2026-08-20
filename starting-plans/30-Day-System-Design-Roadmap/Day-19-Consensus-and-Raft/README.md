# Day 19 — Distributed Consensus & Leader Election
## Topic: Getting Distributed Nodes to Agree on Anything

---

## 🎯 What to Learn Today

- Why distributed consensus is hard (the Byzantine Generals Problem)
- Paxos algorithm — conceptual understanding
- Raft consensus algorithm — how it works step by step
- Leader election patterns in distributed systems
- Apache ZooKeeper: what it does and how it's used
- etcd: Kubernetes' brain for distributed coordination

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Raft Consensus Visual Demo | https://raft.github.io/ | 20 min |
| Raft Paper (Understandable) | https://raft.github.io/raft.pdf | 25 min |
| ZooKeeper Documentation | https://zookeeper.apache.org/doc/current/ | 15 min |
| Distributed Consensus — ByteByteGo | https://www.youtube.com/@ByteByteGo | 15 min |

---

## 🧠 Key Concepts

- **Consensus Problem**: How do N distributed nodes agree on a single value when nodes can fail?
- **Paxos**: Classic consensus algorithm — complex but proven. Requires 2 rounds (Prepare + Accept).
- **Raft**: Designed to be easy to understand. Separates into: Leader Election, Log Replication, Safety.
- **Raft Leader**: One node elected as leader. All writes go through leader. Leader replicates to followers.
- **Raft Election**: If followers don't hear from leader (election timeout), they become candidate and request votes.
- **Quorum**: Majority of nodes (N/2 + 1). Raft requires quorum to commit an entry.
- **ZooKeeper**: Distributed coordination service using ZAB (Zookeeper Atomic Broadcast) — similar to Paxos.
- **etcd**: Strongly consistent key-value store using Raft. Used by Kubernetes for cluster state.

---

## 🔢 Raft Leader Election

```
Normal operation:
  Leader → sends heartbeats to all followers every 50ms
  Followers → reset election timer on each heartbeat

Leader fails:
  1. Followers stop receiving heartbeats
  2. Election timer expires (150-300ms random)
  3. First follower to timeout → becomes Candidate
  4. Candidate increments term, votes for itself
  5. Requests votes from all other nodes
  6. If receives majority → becomes new Leader
  7. New leader sends heartbeats → election ends

Split vote → no majority → new election with higher term
```

---

## 🧩 Problem Statement to Solve

> **Design a Distributed Job Scheduler (like Airflow at Scale)**
> - Schedule millions of cron jobs across a fleet of 1,000 worker nodes
> - Exactly-once execution: no job runs twice, no job is missed
> - Handle worker node failures gracefully (job must be retried)
> - Support job dependencies (Job B runs only after Job A completes)
> - The scheduler itself must be fault-tolerant (no single point of failure)

**Consensus Design Task:**
1. How do you make the job scheduler itself fault-tolerant? (elect one master scheduler via Raft/ZooKeeper)
2. How does leader election work if the master scheduler crashes?
3. How do you prevent two workers from running the same job? (distributed lock in ZooKeeper/Redis)
4. Job A triggers Job B after completion. How is this dependency tracked?
5. A worker node crashes mid-job. How does the scheduler detect and reschedule?

**Architecture Questions to Answer:**
- Why can't you use a single master scheduler without consensus? (single point of failure)
- How does ZooKeeper's ephemeral node pattern work for leader election?
- How does the 2-node brain split problem (network partition) get avoided with quorum?

---

## 💬 Interview Q&A

### Q1: What is the consensus problem in distributed systems?
**Ans:** Given N nodes where any can fail at any time, how do they agree on a single value? Challenges: network delays (messages may arrive out of order), node crashes, network partitions. Without consensus, different nodes may have different views of truth (split brain). Consensus algorithms like Raft ensure all non-faulty nodes agree on the same sequence of decisions.

### Q2: How does Raft handle leader failure?
**Ans:** Each follower has a randomized election timeout (150-300ms). If no heartbeat is received within this window, the follower becomes a candidate and starts an election. It votes for itself and asks others for votes. If it gets votes from a majority (quorum) of nodes, it becomes the new leader. Randomized timeouts prevent all nodes from starting elections simultaneously.

### Q3: What is a distributed lock and how do you implement it?
**Ans:** A distributed lock ensures only one node executes a critical section at a time. With ZooKeeper: client creates an ephemeral sequential node. The client with the lowest sequence number holds the lock. When done, the node is deleted. With Redis: `SET lock_key unique_id NX PX 30000` — atomic set-if-not-exists with 30s expiry. Redlock algorithm uses multiple Redis nodes for safety.

### Q4: What is the "split brain" problem and how does quorum solve it?
**Ans:** Network partition splits N nodes into two groups that can't communicate. Without quorum, both groups might elect a leader and accept writes, leading to conflicting state (split brain). With quorum (N/2 + 1), only the larger partition can reach majority and elect a leader. The minority partition rejects all writes — preventing conflicting states.

---

## 🗒️ Quick Cheat Sheet
- **Raft terms**: Monotonically increasing election round number. Higher term = more recent.
- **Log entry committed**: Once majority of nodes have stored it (quorum write)
- **ZooKeeper ephemeral node**: Deleted automatically when client disconnects — used for leader election and service registration
- **etcd**: 3 or 5 node cluster (odd number for quorum). Used by Kubernetes.
- **Quorum for 5 nodes**: 3. Can survive 2 node failures.

---

## 📓 Your Notes
*Draw the Raft leader election timeline. Design the distributed job scheduler architecture.*
