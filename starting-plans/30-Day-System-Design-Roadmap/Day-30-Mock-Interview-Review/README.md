# Day 30 — Mock Interview Day + Final Review
## Topic: Put It All Together — Interview-Ready System Design

---

## 🎯 Today's Goal

This is your final day. Today you prove to yourself you are interview-ready.

---

## 🎬 Mock Interview Problem (Solve in 45 Minutes)

> **Design a Live Event Streaming Platform for the FIFA World Cup Final**
> - 500 million concurrent viewers globally
> - Live video with < 3 second latency worldwide
> - Real-time score updates and live commentary feed
> - Personalized ads based on viewer location and profile
> - System must handle traffic ramp: 0 → 500M concurrent viewers in 2 hours
> - Chat feature: 100,000 messages per second during the match

**Before reading the hints below — set your timer and design!**

---

## 💡 Hint Architecture (Read after your attempt)

```
Video Source (Stadium) → Encoder → RTMP → Ingest Servers
  ↓ (transcode to HLS: 360p, 720p, 1080p chunks)
Object Storage (S3) ← push 2-sec chunks
  ↓ (edge caching)
CDN (Pull) → 300+ edge PoPs globally → Viewer

Live Score / Commentary:
  Stats API → Kafka → Score Workers → Redis (latest score)
  Viewer WebSocket → Score Service → Redis → push updates

Chat (100K msg/sec):
  Viewer sends message → Kafka → Chat fanout workers → Redis Pub/Sub → WebSockets

Ads:
  Ad request with viewer profile → Ad Targeting Service → Ad Server
  Ad inserted at client-side (SSAI or CSAI) into HLS stream

Traffic Ramp:
  Auto-scaling: CDN handles infinite scale (edge caches absorb traffic)
  Origin servers auto-scale via Kubernetes HPA
  Pre-warm CDN caches with first video segment before event starts
```

---

## ✅ Final Interview Checklist

For ANY system design problem, can you answer all of these?

| Checkpoint | Question |
|---|---|
| **Estimation** | QPS? Storage? Bandwidth? Machine count? |
| **Requirements** | Functional vs Non-Functional clearly separated? |
| **API Design** | Clean API contracts defined? |
| **Data Model** | DB schema or document structure designed? |
| **High-Level Arch** | All major components drawn with data flow? |
| **Scale Bottlenecks** | Where does this break at 10x load? |
| **Failure Handling** | What happens when each service fails? |
| **CAP Tradeoffs** | Consistency vs Availability choice justified? |
| **Caching** | What is cached? Where? With what TTL? |
| **Queues** | What is processed async? Why? |
| **Database Choice** | SQL or NoSQL? Justified? Indexed? |
| **Observability** | Metrics, logs, traces. What SLOs? |
| **Security** | Auth, authz, encryption, rate limiting? |

---

## 📊 Progress Self-Assessment

Look back at the 30 days. Rate each area:

| Topic | Confidence (1-5) |
|---|---|
| Estimation (QPS, storage) | |
| API Design | |
| SQL + NoSQL Databases | |
| Caching (Redis strategies) | |
| Load Balancing + CDN | |
| Message Queues + Kafka | |
| Consistent Hashing | |
| Database Sharding | |
| Distributed Transactions | |
| Search Systems | |
| Real-Time / WebSockets | |
| Microservices + Circuit Breaker | |
| Observability (logs/metrics/traces) | |
| Security (OAuth, JWT, encryption) | |
| Distributed Storage | |
| Consensus + Leader Election | |
| Time-Series Analytics | |
| ML Systems Design | |
| Feed Systems | |
| WhatsApp / Uber / Google / Stripe / S3 | |

**Focus next 2 weeks on any score ≤ 3.**

---

## 💬 Top 10 System Design Interview Tips

1. **Clarify before diving in** — spend 3-5 minutes asking requirements questions
2. **Estimate first** — always compute QPS and storage before picking technology
3. **Start simple, evolve** — begin with single server, then add scale layer by layer
4. **Name your bottlenecks** — "this DB would be overwhelmed at 10x, so I'd add read replicas"
5. **Justify every choice** — not "I'll use Kafka" but "I'll use Kafka because we need replay capability and high throughput"
6. **Think out loud** — interviewers want to see your reasoning, not just the answer
7. **Draw on the whiteboard** — architecture diagrams communicate structure faster than words
8. **Discuss tradeoffs** — every choice has a cost; show you know both sides
9. **Check for single points of failure** — for every component, ask "what if this goes down?"
10. **Know your numbers**: 1M QPS needs ~100 servers; 1PB needs ~1000 HDDs; latency: RAM < 1μs, SSD ~ 1ms, Network ~ 1-150ms

---

## 📚 What to Study Next

If you want to go deeper after Day 30:

| Topic | Resource |
|---|---|
| Designing Data-Intensive Applications (DDIA) | Book by Martin Kleppmann — must read |
| Google SRE Book | Free online — site.reliability.engineering |
| System Design Interview Vol 1 & 2 | Alex Xu — excellent real interview questions |
| ByteByteGo Newsletter | Weekly system design deep dives |
| High Scalability Blog | Real architecture case studies |
| AWS Well-Architected Framework | Cloud best practices from AWS |

---

## 🏆 Congratulations!

You have completed the 30-Day System Design Roadmap.

```
Phase 1 ✅ Foundations (Days 1-8)
Phase 2 ✅ Core Components (Days 9-17)
Phase 3 ✅ Advanced Distributed Systems (Days 18-24)
Phase 4 ✅ Real-World Designs + Mock Interview (Days 25-30)
```

You now have the knowledge to design:
- URL shorteners, chat systems, file storage
- Distributed caches, search engines, real-time systems
- Payment systems, messaging platforms, ride-hailing apps
- Cloud storage, recommendation engines, global CDNs

**Go ace that interview! 🚀**

---

## 📓 Your Notes — Final Reflection
*What were your 3 biggest learning moments? What will you study more deeply?*
