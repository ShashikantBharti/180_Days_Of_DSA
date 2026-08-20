# Day 05 — Load Balancing & Horizontal Scaling
## Topic: Distributing Traffic Across Many Servers

---

## 🎯 What to Learn Today

- Vertical vs Horizontal scaling — tradeoffs
- What a Load Balancer does and where it sits
- Load balancing algorithms: Round Robin, Least Connections, IP Hash, Weighted
- Layer 4 vs Layer 7 load balancers
- Health checks and failover
- Session persistence (sticky sessions) — problem and solutions

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Load Balancing — ByteByteGo | https://www.youtube.com/@ByteByteGo | 20 min |
| NGINX Load Balancing Docs | https://docs.nginx.com/nginx/admin-guide/load-balancer/ | 15 min |
| AWS ELB vs ALB vs NLB | https://aws.amazon.com/elasticloadbalancing/features/ | 15 min |
| Horizontal vs Vertical Scaling | https://github.com/donnemartin/system-design-primer | 10 min |

---

## 🧠 Key Concepts

- **Vertical Scaling (Scale Up)**: Add more CPU/RAM to same server. Limit: hardware ceiling. No code changes needed.
- **Horizontal Scaling (Scale Out)**: Add more servers. Unlimited scale. Needs stateless app design.
- **Round Robin**: Each server gets requests in turn. Simple. Works when servers are identical.
- **Least Connections**: Route to server with fewest active connections. Better for varied request durations.
- **IP Hash**: Same client IP always → same server. Used for sticky sessions.
- **Layer 4 LB**: Routes based on IP/TCP. Fast, no payload inspection.
- **Layer 7 LB**: Routes based on HTTP content (URL, headers, cookies). Smarter, slightly slower.
- **Health Check**: LB pings each server; removes unhealthy ones from rotation automatically.

---

## 🔢 Scaling Comparison

```
Vertical Scaling:
  ✅ Simple — no code changes
  ✅ No distributed system complexity
  ❌ Has hardware limit
  ❌ Single point of failure
  ❌ Expensive at high end

Horizontal Scaling:
  ✅ Unlimited scale
  ✅ No single point of failure
  ✅ Cheaper (commodity hardware)
  ❌ App must be stateless
  ❌ Need load balancer
  ❌ Distributed complexity
```

---

## 🧩 Problem Statement to Solve

> **Design a File Upload Service**
> - Users upload files (images, documents) up to 100MB each
> - 1 million uploads per day, peak 50,000 concurrent uploads
> - Files must be retrievable globally with < 200ms latency
> - Support resumable uploads (if connection drops, continue where left off)
> - Service must handle server failures without losing uploads

**Load Balancing Design Task:**
1. Where do you place the load balancer in your architecture?
2. Which LB algorithm is best for file uploads? (Least Connections — uploads take variable time)
3. The upload takes 30 seconds. How do you ensure the same server handles the full upload?
4. One upload server crashes mid-upload. How do you recover?

**Architecture Questions to Answer:**
- How to handle sticky sessions for resumable uploads?
- Should file data go through the LB or directly to storage (pre-signed URLs)?
- How does CDN help with file downloads?

---

## 💬 Interview Q&A

### Q1: What is the difference between Layer 4 and Layer 7 load balancers?
**Ans:** Layer 4 routes based on TCP/IP info (IP, port) without reading content — very fast. Layer 7 reads HTTP content (URL path, headers, cookies) to make smarter routing decisions (e.g., route `/api` to API servers, `/static` to CDN) — slightly more overhead but much more flexible.

### Q2: What is a sticky session and what problem does it solve?
**Ans:** Sticky sessions (session affinity) ensure that a user's requests always go to the same server — needed when session state is stored on the server. The problem: breaks horizontal scaling if the server fails. Solution: store session state externally (Redis) so any server can handle any request.

### Q3: How does a load balancer detect that a server is down?
**Ans:** Health checks — the LB periodically sends a ping (TCP or HTTP) to each server. If a server doesn't respond within a timeout (e.g., 3 retries over 10 seconds), it's marked unhealthy and removed from rotation. When it recovers, it's automatically re-added.

### Q4: What is the difference between horizontal and vertical scaling?
**Ans:** Vertical scaling adds more resources (CPU/RAM) to one machine — limited by hardware ceiling and creates single point of failure. Horizontal scaling adds more machines — theoretically unlimited, more resilient, but requires stateless application design and introduces distributed system complexity.

---

## 🗒️ Quick Cheat Sheet
- **LB algorithms**: Round Robin (simple), Least Conn (varied durations), IP Hash (sticky)
- **Health check interval**: typically every 5-30 seconds
- **Stateless app**: Session stored in Redis, not in server memory
- **Active-Active**: Multiple LBs handling traffic simultaneously
- **Active-Passive**: Standby LB takes over if primary fails

---

## 📓 Your Notes
*Draw the load balancing architecture for the file upload service. Label each component.*
