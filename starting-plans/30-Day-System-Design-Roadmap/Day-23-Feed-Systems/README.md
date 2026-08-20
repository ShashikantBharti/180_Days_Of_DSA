# Day 23 — Scalable Feed & Timeline Systems
## Topic: How Twitter, Instagram and Facebook Generate Your Feed

---

## 🎯 What to Learn Today

- Fan-out on write vs Fan-out on read — the fundamental tradeoff
- Hybrid approach for mixed follower counts
- The celebrity/hotspot problem and how to solve it
- Timeline ranking: chronological vs algorithmic (ML-based)
- Feed storage with Redis (pre-computed feeds)
- How Instagram, Twitter, and Facebook actually design their feeds

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Twitter Feed — ByteByteGo | https://www.youtube.com/@ByteByteGo | 20 min |
| Instagram Architecture Blog | https://instagram-engineering.com/ | 20 min |
| Facebook News Feed 2004-2024 | https://engineering.fb.com/ | 15 min |
| System Design Primer - Twitter | https://github.com/donnemartin/system-design-primer | 15 min |

---

## 🧠 Key Concepts

- **Fan-out on Write (Push)**: When user A posts, immediately push post to all A's followers' feeds. Fast reads (feed pre-built). Slow writes for users with many followers.
- **Fan-out on Read (Pull)**: When user B opens feed, fetch recent posts from all B's followees on-demand. Slow reads (real-time aggregation). Fast writes.
- **Hybrid**: Fan-out on write for regular users. Fan-out on read for celebrities (too many followers to push to all).
- **Celebrity Problem**: User with 100M followers. Fan-out on write = 100M Redis writes per post. Solution: serve celebrity posts directly at read time.
- **Ranking**: ML model re-ranks feed based on: user engagement history, post freshness, author relationship strength.
- **Feed Storage**: Each user has a "feed list" in Redis (sorted set of post IDs). New posts prepended. Trim to 1000 entries.

---

## 🔢 Fan-out Comparison

```
Fan-out on Write:
  Post arrives → write to all N followers' feeds
  Feed read → Redis lookup: O(1)
  Problem: User with 10M followers → 10M Redis writes on post

Fan-out on Read:
  Post stored once in posts table
  Feed read → fetch posts from all followees, merge, sort
  Problem: User follows 1000 people → 1000 DB reads per feed refresh

Hybrid (Twitter's approach):
  Regular user (< 10K followers): fan-out on write → feeds pre-built
  Celebrity (> 10K followers): their tweets served at read time
  Feed read: merge pre-built feed + celebrity tweets on-the-fly
```

---

## 🧩 Problem Statement to Solve

> **Design Instagram Home Feed**
> - 1 billion users; average user follows 500 accounts
> - Celebrities can have 100 million+ followers (Cristiano Ronaldo, etc.)
> - Feed must load in < 500ms
> - Content types: photos, reels, stories, ads
> - Ranked feed (not just chronological — ML-ranked by predicted engagement)
> - Stories disappear after 24 hours

**Feed System Design Task:**
1. Regular user (Jane, 500 followers) posts a photo — what happens? (fan-out on write → 500 Redis writes)
2. Celebrity (Ronaldo, 100M followers) posts — what happens? (no fan-out → serve at read time)
3. Jane opens her feed — how is it assembled? (her pre-built feed + follow any celebrities → merge → rank)
4. ML Ranking: what features predict if Jane will like Post X? (Jane's history, Post X engagement, author relationship)
5. Stories expire after 24hrs. How does the system clean them up?

**Architecture Questions to Answer:**
- Redis data structure for Jane's feed? (Sorted Set: post_id → timestamp score)
- How do ads get inserted into the feed? (ad server inserts sponsored posts at certain positions)
- Fan-out worker crashed after writing to 500K of 1M followers. How do you resume? (checkpoint + idempotent)

---

## 💬 Interview Q&A

### Q1: What is fan-out and what are the tradeoffs?
**Ans:** Fan-out on write: push content to all followers immediately at post time → feeds are pre-built, reads are fast (O(1) Redis lookup), but writes are expensive for users with many followers. Fan-out on read: aggregate content at read time → writes are fast, reads are expensive (merge N followees' posts). Hybrid: write fan-out for normal users, read fan-out for celebrities.

### Q2: How does Instagram's feed ranking work?
**Ans:** Instead of chronological order, ML model scores each candidate post. Features: (1) Interest score (how often user interacts with this author/content type), (2) Post engagement (likes/comments from others), (3) Freshness (how recent), (4) Relationship score (close friend vs casual follow). Model predicts probability of user engaging → ranked list served.

### Q3: How do you handle the celebrity problem at scale?
**Ans:** Don't pre-push celebrity posts to all followers. Instead: when a user's feed is requested, query the pre-built fan-out feed + separately query recent posts from any celebrities the user follows + merge and rank. Since user follows O(10) celebrities at most, this is O(10) lookups added at read time — manageable.

### Q4: How does a feed get populated for a new user with no history?
**Ans:** Cold start for feeds: (1) Show globally trending posts, (2) Show popular content in user's interest categories (selected during signup), (3) Show posts from accounts suggested by the algorithm (based on demographics/contacts), (4) Use engagement signals from first sessions to rapidly personalize within days.

---

## 🗒️ Quick Cheat Sheet
- **Feed TTL**: Keep last N days or last K posts in Redis per user (trim old entries)
- **Redis Sorted Set**: ZADD feed:user_123 <timestamp> <post_id> — natural time ordering
- **Fan-out worker**: Async background job (Kafka consumer) that handles fan-out after post is created
- **Pagination**: Feed loads first 20 posts, user scrolls → load next 20 (cursor-based pagination)
- **Stories**: Stored with TTL in Redis; permanent in cold storage for user's archive

---

## 📓 Your Notes
*Draw the feed assembly architecture. Show regular user vs celebrity post flows.*
