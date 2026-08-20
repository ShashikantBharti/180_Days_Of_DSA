# Day 15 — Microservices Architecture
## Topic: Decomposing Monoliths into Independent Services

---

## 🎯 What to Learn Today

- Monolith vs Microservices — real tradeoffs
- How to decompose a monolith (Domain-Driven Design)
- Service discovery and registration
- Circuit Breaker pattern — preventing cascade failures
- API Gateway vs Service Mesh (Istio, Linkerd)
- Container orchestration with Kubernetes (brief overview)

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Microservices — Martin Fowler | https://martinfowler.com/articles/microservices.html | 25 min |
| Circuit Breaker Pattern | https://microservices.io/patterns/reliability/circuit-breaker.html | 15 min |
| Service Mesh Explained | https://www.youtube.com/@ByteByteGo | 15 min |
| Kubernetes Basics | https://kubernetes.io/docs/concepts/overview/ | 15 min |

---

## 🧠 Key Concepts

- **Monolith**: All features in one deployable unit. Simple to develop/debug; hard to scale/maintain at size.
- **Microservices**: Each service owns one domain (User, Order, Payment, Inventory). Independent deploy, scale, and failure.
- **Service Discovery**: How services find each other (Consul, Kubernetes DNS, AWS Cloud Map)
- **Circuit Breaker**: If a downstream service fails repeatedly, "open" the circuit — stop calling it, return fallback response. Prevents cascade failure.
- **API Gateway**: Single entry point for external clients. Routes to correct microservice.
- **Service Mesh**: Infrastructure layer (Istio sidecar proxy) that handles service-to-service auth, load balancing, tracing, retries — without code changes.
- **Bounded Context (DDD)**: A microservice boundary defined by a domain model that is internally consistent.

---

## 🔢 Monolith vs Microservices

```
Monolith:
  ✅ Simple to develop and debug
  ✅ Low latency (in-process calls)
  ✅ Simple deployment (one artifact)
  ❌ Hard to scale individual components
  ❌ One bug can crash everything
  ❌ Tech stack locked

Microservices:
  ✅ Independent scaling per service
  ✅ Independent deployment
  ✅ Fault isolation
  ✅ Team autonomy (each team owns a service)
  ❌ Network overhead between services
  ❌ Distributed system complexity
  ❌ Hard to debug across service boundaries
  ❌ Need DevOps maturity (CI/CD, Kubernetes)
```

---

## 🧩 Problem Statement to Solve

> **Design a Ride-Hailing System (like Uber)**
> - Match riders with nearby drivers in real-time
> - Track driver location every 4 seconds (10 million drivers online)
> - Surge pricing based on real-time supply/demand ratio
> - Trip lifecycle: requested → driver matched → in-progress → completed → rated
> - 10 million trips per day globally

**Microservices Design Task:**
1. Decompose Uber into microservices: list at least 8 distinct services.
2. For "rider requests a trip" flow: which services are called? In what order?
3. Location service gets 10M location updates every 4s = 2.5M/sec writes. How do you scale it?
4. If Driver Matching Service is down, how does Circuit Breaker protect the system?
5. How does the API Gateway route requests from mobile app to correct microservices?

**Architecture Questions to Answer:**
- How are driver locations stored? (Redis GeoSpatial data type)
- How is surge pricing calculated across services?
- What does the service mesh (Istio) handle automatically in this architecture?

---

## 💬 Interview Q&A

### Q1: When should you NOT use microservices?
**Ans:** Small team (< 10 engineers), early-stage product (requirements changing rapidly), team lacks DevOps maturity. Start with a well-structured monolith. Microservices add massive operational overhead — you need CI/CD, container orchestration, distributed tracing, service mesh. Only worth it when you need independent scaling or team autonomy at scale.

### Q2: What is the Circuit Breaker pattern?
**Ans:** Like an electrical circuit breaker — if a service fails repeatedly (e.g., 50% error rate), the breaker "opens" and immediately returns a fallback (cached response or error) without attempting the actual call. After a timeout, it "half-opens" (tries one request). If it succeeds, the breaker closes. Prevents one failing service from causing cascade failure across the system.

### Q3: What is the difference between an API Gateway and a Service Mesh?
**Ans:** API Gateway handles north-south traffic (client → services): authentication, rate limiting, routing. It's the front door. Service Mesh handles east-west traffic (service → service): mutual TLS, load balancing, retries, circuit breaking, distributed tracing. They are complementary — use both together.

### Q4: How does service discovery work in microservices?
**Ans:** Services register themselves (IP, port, health) in a service registry (Consul, Kubernetes etcd). When Service A needs to call Service B, it queries the registry for B's location. In Kubernetes, each service gets a DNS name that auto-resolves to current healthy pods. No hardcoded IPs needed.

---

## 🗒️ Quick Cheat Sheet
- **States**: Circuit CLOSED (normal), OPEN (failing, return fallback), HALF-OPEN (testing recovery)
- **Sidecar proxy**: Container alongside each service pod that handles all network traffic (Istio Envoy)
- **Kubernetes Service**: Stable DNS endpoint that load balances across healthy pods
- **Bounded Context**: Microservice boundary — User service owns everything about users; Order service owns orders
- **API Gateway examples**: AWS API Gateway, Kong, NGINX, Traefik

---

## 📓 Your Notes
*List all Uber microservices. Draw the trip request flow showing which services communicate.*
