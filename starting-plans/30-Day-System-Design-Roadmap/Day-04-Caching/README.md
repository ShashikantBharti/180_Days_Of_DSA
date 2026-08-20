# Day 04 — Caching Strategies
## Topic: Making Systems 100x Faster with Caching

---

## 🎯 What to Learn Today

- Why caching exists and what problems it solves
- Cache levels: Client cache, CDN cache, Application cache, Database cache
- Cache strategies: Cache-aside, Read-through, Write-through, Write-behind
- Cache eviction policies: LRU, LFU, TTL
- Redis vs Memcached — which to choose
- Cache invalidation (the hardest problem in CS!)

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Caching Patterns — ByteByteGo | https://www.youtube.com/@ByteByteGo | 20 min |
| Redis Documentation | https://redis.io/docs/ | 15 min |
| Cache Eviction Policies Explained | https://medium.com/system-design-blog | 15 min |
| System Design Primer - Cache | https://github.com/donnemartin/system-design-primer#cache | 20 min |

---

## 🧠 Key Concepts

- **Cache-Aside (Lazy Loading)**: App checks cache first; on miss, loads from DB and writes to cache
- **Read-Through**: Cache sits in front of DB; on miss, cache fetches from DB automatically
- **Write-Through**: Write goes to cache AND DB simultaneously. Always consistent, slower writes
- **Write-Behind (Write-Back)**: Write to cache first, async flush to DB later. Fast writes, risk of data loss
- **TTL (Time-To-Live)**: Cache entry expires after N seconds automatically
- **LRU (Least Recently Used)**: Evicts the entry that was accessed longest ago
- **LFU (Least Frequently Used)**: Evicts the entry that was accessed fewest times
- **Cache Hit Ratio**: (hits / total requests) × 100 — target > 90%

---

## 🔢 Redis vs Memcached

```
Redis:
  ✅ Supports rich data types (String, Hash, List, Set, Sorted Set)
  ✅ Persistence (AOF/RDB snapshots)
  ✅ Pub/Sub, Lua scripting, Transactions
  ✅ Built-in replication and clustering
  Use for: Sessions, leaderboards, pub/sub, distributed locks

Memcached:
  ✅ Simple key-value only (strings)
  ✅ Multi-threaded (better CPU utilization)
  ✅ Slightly faster for simple string caching
  Use for: Pure caching of simple values at very high throughput
```

---

## 🧩 Problem Statement to Solve

> **Design a Leaderboard System for a Mobile Game**
> - Track scores for 10 million players globally
> - Top-100 leaderboard must update in near real-time (< 1 second lag)
> - Read:Write ratio = 100:1 (far more reads than writes)
> - Support global leaderboard AND regional leaderboards (by country)
> - Players check leaderboard every 30 seconds on average

**Caching Design Task:**
1. Where would you cache the leaderboard? (Redis Sorted Set!)
2. What TTL would you set? Why?
3. How do you handle a score update — write-through or write-behind?
4. How to keep global + 50 regional leaderboards in sync?

**Architecture Questions to Answer:**
- What Redis data structure is perfect for ranked leaderboards?
- When a player's score changes, what exactly do you update in Redis?
- What happens to the leaderboard when the Redis node fails?

---

## 💬 Interview Q&A

### Q1: What is cache-aside pattern and when do you use it?
**Ans:** The application first checks the cache. On a cache miss, it reads from the database and populates the cache. On cache hit, returns directly. Used when data is read frequently but written infrequently (read-heavy workloads).

### Q2: What is cache invalidation and why is it hard?
**Ans:** Cache invalidation means removing or updating stale cache entries when underlying data changes. It's hard because: distributed caches may have multiple copies, TTL-based expiry may serve stale data, and invalidating too aggressively defeats the purpose of caching.

### Q3: What is the difference between LRU and LFU eviction?
**Ans:** LRU evicts the entry not accessed for the longest time — good for temporal locality. LFU evicts the entry accessed the fewest total times — better for skewed access patterns where some items are always hot.

### Q4: What is a cache stampede (thundering herd)?
**Ans:** When a popular cached item expires, thousands of requests simultaneously query the database before any single one can repopulate the cache. Solutions: mutex lock on repopulation, probabilistic early expiration, or background refresh.

---

## 🗒️ Quick Cheat Sheet
- **Redis Sorted Set (ZADD/ZRANK)**: Perfect for leaderboards
- **Redis String with TTL**: Perfect for session tokens
- **Redis Hash**: Perfect for user profile fields
- **Cache Hit Ratio < 80%**: Investigate — your cache may be too small or TTL too short
- **Hotspot Key**: One cache key gets 90% of traffic — add local app-level cache

---

## 📓 Your Notes
*Design the leaderboard caching architecture here. What Redis commands would you use?*
