# Day 02 — Client-Server Architecture, HTTP & API Design
## Topic: How the Web Works + Designing Clean APIs

---

## 🎯 What to Learn Today

- Client-Server model and how requests flow
- HTTP/HTTPS methods, status codes, headers
- REST vs GraphQL vs gRPC — when to use which
- How to design a clean, scalable API
- Stateless vs Stateful services
- DNS resolution flow (what happens when you type google.com)

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| How DNS Works (comic) | https://howdns.works/ | 15 min |
| REST API Design Best Practices | https://restfulapi.net/ | 20 min |
| gRPC vs REST vs GraphQL | https://www.youtube.com/@ByteByteGo | 20 min |
| HTTP Status Codes Reference | https://developer.mozilla.org/en-US/docs/Web/HTTP/Status | 10 min |

---

## 🧠 Key Concepts

- **REST**: Stateless, resource-based, uses HTTP methods (GET, POST, PUT, DELETE)
- **GraphQL**: Client specifies exact data shape, single endpoint, avoids over/under-fetching
- **gRPC**: Binary protocol (Protobuf), strongly typed, ideal for internal microservices
- **Stateless**: Server does NOT store client session; each request is self-contained (scales easily)
- **Stateful**: Server remembers client state between requests (harder to scale)
- **DNS**: Domain Name System — translates `google.com` → IP address

---

## 🔢 HTTP Status Codes Cheat Sheet

```
2xx = Success
  200 OK, 201 Created, 204 No Content

3xx = Redirect
  301 Moved Permanently, 302 Found, 304 Not Modified

4xx = Client Error
  400 Bad Request, 401 Unauthorized, 403 Forbidden
  404 Not Found, 429 Too Many Requests

5xx = Server Error
  500 Internal Server Error, 502 Bad Gateway, 503 Service Unavailable
```

---

## 🧩 Problem Statement to Solve

> **Design a Simple Chat Messaging API**
> - Users can send and receive text messages
> - 1 million active users, 50 million messages/day
> - Messages delivered within 1 second
> - Support fetching message history (last 30 days)
> - API must be RESTful and versioned

**API Design Task — Define these endpoints:**
1. Send a message: What HTTP method? What request body?
2. Get conversation history: How to paginate?
3. Mark message as read: What HTTP method and status code?
4. Get list of conversations for a user

**Architecture Questions to Answer:**
- Should this be REST or WebSockets for real-time delivery?
- How to handle API versioning (URL path vs header)?
- Where to add authentication (JWT in header)?

---

## 💬 Interview Q&A

### Q1: What is the difference between REST and GraphQL?
**Ans:** REST uses multiple endpoints, each returning fixed data shapes. GraphQL uses one endpoint where clients specify exactly what data they need — avoids over-fetching (too much data) and under-fetching (too little data).

### Q2: When would you choose gRPC over REST?
**Ans:** gRPC is ideal for internal microservice communication where performance matters. It uses binary serialization (Protobuf) which is 5-10x faster than JSON, supports streaming, and enforces strict contracts via schema.

### Q3: What does "stateless" mean in REST?
**Ans:** Each HTTP request must contain all the information needed to process it. The server doesn't store any session state between requests. This makes horizontal scaling easy — any server can handle any request.

### Q4: What happens when you type google.com in a browser?
**Ans:** (1) Browser checks local DNS cache → (2) OS checks hosts file → (3) Query to DNS resolver → (4) Recursive DNS lookup (Root → TLD → Authoritative) → (5) IP returned → (6) TCP connection → (7) TLS handshake → (8) HTTP request sent → (9) Response rendered.

---

## 🗒️ Quick Cheat Sheet
- **GET** = Read, safe, idempotent
- **POST** = Create, NOT idempotent
- **PUT** = Full update, idempotent
- **PATCH** = Partial update
- **DELETE** = Remove, idempotent
- **Idempotent** = Calling it N times = same result as calling once

---

## 📓 Your Notes
*Draw the DNS resolution flow. Design the chat API endpoints here.*
