# Day 08 — API Rate Limiting & API Gateway
## Topic: Protecting APIs from Abuse and Overload

---

## 🎯 What to Learn Today

- Why rate limiting is essential (protect from abuse, ensure fairness)
- Rate limiting algorithms: Fixed Window, Sliding Window Log, Sliding Window Counter, Token Bucket, Leaky Bucket
- Where to enforce rate limits: client-side, API Gateway, application layer
- API Gateway pattern — what it does and why it's used
- Distributed rate limiting (across multiple servers)
- Throttling vs Rate Limiting — the difference

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Rate Limiting Algorithms — ByteByteGo | https://www.youtube.com/@ByteByteGo | 20 min |
| System Design Primer - Rate Limiting | https://github.com/donnemartin/system-design-primer | 15 min |
| Token Bucket vs Leaky Bucket | https://blog.cloudflare.com/counting-things-a-lot-of-different-things/ | 15 min |
| API Gateway Pattern | https://microservices.io/patterns/apigateway.html | 15 min |

---

## 🧠 Key Concepts

- **Fixed Window**: Count requests in fixed time window (e.g., 100 req/min). Problem: burst at window boundary.
- **Sliding Window Log**: Track exact timestamps of each request. Accurate but memory-intensive.
- **Sliding Window Counter**: Blend of fixed windows with weighted calculation. Good balance.
- **Token Bucket**: Bucket fills at fixed rate (N tokens/sec). Each request consumes 1 token. Allows bursts.
- **Leaky Bucket**: Requests fill a queue (bucket), processed at fixed rate. Smooth output, no bursts.
- **API Gateway**: Single entry point for all client requests — handles auth, rate limiting, routing, logging
- **Distributed Rate Limiting**: Use Redis atomic counters (INCR) so limits apply across all app servers

---

## 🔢 Algorithm Comparison

```
Fixed Window:
  ✅ Simple to implement
  ❌ Allows 2x burst at window boundaries

Token Bucket:
  ✅ Allows controlled bursts
  ✅ Common in practice (AWS, Stripe use this)
  ❌ Slightly harder to implement distributed

Leaky Bucket:
  ✅ Smooth, predictable output rate
  ❌ Requests may wait in queue
  ❌ Doesn't allow any bursting

Sliding Window Log:
  ✅ Most accurate
  ❌ Stores all request timestamps — memory heavy
```

---

## 🧩 Problem Statement to Solve

> **Design a Public API Rate Limiter**
> - Free users: 100 requests/hour
> - Basic paid users: 1,000 requests/hour
> - Enterprise users: 10,000 requests/hour
> - Rate limits must work across 50 distributed API servers (not per-server)
> - Return HTTP 429 with `Retry-After` header when limit exceeded
> - Support per-user AND per-IP rate limiting
> - Rate limit data must reset every hour exactly

**Rate Limiter Design Task:**
1. Where is the rate limiter enforced? (API Gateway, app middleware, or dedicated service?)
2. How does Redis enable distributed rate limiting? (INCR + EXPIRE commands)
3. Design the Redis key structure: `rate_limit:{user_id}:{window}` → counter
4. How do you return a helpful 429 response? What headers?
5. How to handle the case where Redis is down? (fail open vs fail closed)

**Architecture Questions to Answer:**
- What algorithm would you choose and why?
- How to rate limit unauthenticated users (by IP)?
- How to allow burst for enterprise users (Token Bucket)?

---

## 💬 Interview Q&A

### Q1: What is the difference between rate limiting and throttling?
**Ans:** Rate limiting sets a maximum number of requests in a time window — requests over the limit are rejected (429). Throttling slows requests down by adding delays instead of rejecting them — the client waits. Rate limiting is more common for APIs.

### Q2: How do you implement distributed rate limiting across 10 app servers?
**Ans:** Use Redis as the centralized counter store. Each server, on each request, calls Redis `INCR user:123:minute:2024` and checks if value exceeds limit. Since Redis is single-threaded, INCR is atomic — no race conditions. Set TTL with EXPIRE to auto-reset the counter.

### Q3: What happens if the rate limiter service (Redis) goes down?
**Ans:** Two strategies: **Fail Open** — allow all requests through (risk of abuse but service stays up). **Fail Closed** — reject all requests (safe from abuse but service is inaccessible). Typically fail open with alerting, since rate limiting failure is better than service downtime.

### Q4: What is an API Gateway and what does it do?
**Ans:** API Gateway is a single entry point that handles cross-cutting concerns: authentication, authorization, rate limiting, request routing to microservices, SSL termination, logging, and response caching. Examples: AWS API Gateway, Kong, NGINX. Centralizes these concerns so individual services don't need to implement them.

---

## 🗒️ Quick Cheat Sheet
- **HTTP 429**: Too Many Requests
- **Retry-After header**: Tells client when to retry (seconds or date)
- **X-RateLimit-Limit**: Total allowed requests
- **X-RateLimit-Remaining**: Requests left in current window
- **X-RateLimit-Reset**: Unix timestamp when window resets
- **Redis INCR**: Atomic increment — safe for distributed counting

---

## 📓 Your Notes
*Design the rate limiter architecture. Write the Redis key scheme and the algorithm logic.*
