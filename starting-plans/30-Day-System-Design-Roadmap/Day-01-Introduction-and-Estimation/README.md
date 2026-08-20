# Day 01 — Introduction to System Design & Estimation
## Topic: What is System Design + Back-of-Envelope Estimation

---

## 🎯 What to Learn Today

- What is System Design and why it matters in interviews
- Functional vs Non-Functional Requirements
- How to approach a system design problem step-by-step
- Back-of-the-envelope estimation (QPS, Storage, Bandwidth, Memory)
- Key numbers every engineer must know

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| System Design Primer (GitHub) | https://github.com/donnemartin/system-design-primer | 30 min |
| ByteByteGo - System Design Basics | https://www.youtube.com/@ByteByteGo | 20 min |
| Latency Numbers Every Programmer Should Know | https://colin-scott.github.io/personal_website/research/interactive_latency.html | 10 min |

---

## 🧠 Key Concepts

- **Functional Requirements**: What the system DOES (features, APIs)
- **Non-Functional Requirements**: How the system PERFORMS (latency, availability, consistency)
- **QPS (Queries Per Second)**: Measure of system load
- **Estimation Formula**: `Daily Requests / 86,400 = QPS`
- **Storage Estimation**: users × data_per_user × retention_period
- **Key Numbers**: L1 cache ~1ns, Memory ~100ns, SSD ~1ms, Network ~150ms (cross-region)

---

## 🔢 Estimation Cheat Sheet

```
1 KB  = 1,000 bytes
1 MB  = 1,000 KB
1 GB  = 1,000 MB
1 TB  = 1,000 GB
1 PB  = 1,000 TB

1 day = 86,400 seconds
1 month = 2.5 million seconds
```

---

## 🧩 Problem Statement to Solve

> **Design a URL Shortener (like bit.ly)**
> - Handle 100 million URL shortenings per day
> - Each short URL must be unique and ≤ 7 characters
> - Redirect must happen in < 10ms latency
> - Support analytics: click count per URL
> - System must be available 99.99% uptime

**Your Estimation Task:**
1. Calculate: How many writes/second (QPS)?
2. Calculate: How much storage needed for 5 years?
3. Calculate: How many reads/second (assume 10:1 read:write)?

**Architecture Questions to Answer:**
- How to generate short codes? (hash vs counter vs base62)
- Which database for storing URL mappings? (SQL or NoSQL)
- Where to add caching to reduce database reads?
- How to handle redirects at massive scale?

---

## 💬 Interview Q&A

### Q1: What are the steps to approach a system design interview?
**Ans:** (1) Clarify requirements, (2) Estimate scale, (3) Define APIs, (4) Design data model, (5) Draw high-level design, (6) Deep dive into bottlenecks, (7) Discuss tradeoffs.

### Q2: What is the difference between Functional and Non-Functional requirements?
**Ans:** Functional = what the system does (e.g., "user can shorten a URL"). Non-Functional = how well it does it (e.g., "latency < 10ms, 99.99% uptime").

### Q3: How do you estimate QPS for a system?
**Ans:** Take daily active users × actions per user per day, then divide by 86,400 seconds/day.

### Q4: Why is back-of-envelope estimation important?
**Ans:** It helps identify the right technology choices early. A system handling 100 QPS needs very different architecture than one handling 1M QPS.

---

## 🗒️ Quick Cheat Sheet
- **Availability 99.9%** = ~8.7 hours downtime/year
- **Availability 99.99%** = ~52 minutes downtime/year
- **Availability 99.999%** = ~5 minutes downtime/year
- **1 million users × 1KB data = 1 GB storage**
- **10M requests/day ÷ 86,400 = ~116 QPS**

---

## 📓 Your Notes
*Add your architecture diagram, calculations, and observations here.*
