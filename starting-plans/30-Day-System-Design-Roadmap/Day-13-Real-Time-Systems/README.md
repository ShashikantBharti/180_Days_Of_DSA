# Day 13 — Real-Time Systems & WebSockets
## Topic: Building Systems with Live, Instant Updates

---

## 🎯 What to Learn Today

- Polling vs Long Polling vs Server-Sent Events vs WebSockets
- WebSocket connection lifecycle (handshake, frames, close)
- Pub/Sub pattern for real-time delivery
- Scaling WebSocket servers (horizontal scaling challenges)
- Presence detection: is user online?
- Operational Transformation (OT) vs CRDTs for conflict resolution

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| WebSockets Explained — ByteByteGo | https://www.youtube.com/@ByteByteGo | 15 min |
| WebSocket Protocol (RFC 6455) Overview | https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API | 15 min |
| CRDTs Explained Simply | https://crdt.tech/ | 20 min |
| Google Docs Architecture | https://drive.googleblog.com/ | 15 min |

---

## 🧠 Key Concepts

- **Short Polling**: Client requests every N seconds. Simple but wasteful (mostly empty responses).
- **Long Polling**: Client requests, server holds connection until data available, then responds. Client immediately requests again. Better than polling but still HTTP overhead.
- **SSE (Server-Sent Events)**: Server pushes events to client over persistent HTTP connection. One-directional (server → client only). Simpler than WebSocket.
- **WebSocket**: Full-duplex, bidirectional persistent TCP connection. Perfect for chat, gaming, live collaboration.
- **Pub/Sub**: Publishers send to topic; all subscribers receive it. Redis Pub/Sub or Kafka for distribution.
- **CRDT (Conflict-free Replicated Data Type)**: Data structure that can be merged without conflicts — perfect for offline collaborative editing.
- **OT (Operational Transformation)**: Transform operations based on concurrent edits — used in Google Docs.

---

## 🔢 Comparison Table

```
                | Polling | Long Poll | SSE    | WebSocket
----------------|---------|-----------|--------|----------
Direction       | C→S     | C→S→C     | S→C    | Both
Connection      | New/req | New/req   | Persist| Persist
Server load     | High    | Medium    | Low    | Low
Real-time       | Poor    | OK        | Good   | Best
Use case        | Status  | Notif.    | Feeds  | Chat/Game
HTTP compatible | Yes     | Yes       | Yes    | Upgrade
```

---

## 🧩 Problem Statement to Solve

> **Design a Real-Time Collaborative Document Editor (like Google Docs)**
> - Multiple users editing the same document simultaneously
> - Changes propagate to all editors within < 100ms
> - Handle conflicts: 2 users edit the same line at the same time
> - Support offline editing: user goes offline, makes changes, comes back online and syncs
> - Scale to 1 million concurrent collaborative sessions

**Real-Time System Design Task:**
1. What transport protocol? (WebSockets — bidirectional needed)
2. User A and User B both insert a character at position 50 simultaneously. What happens?
3. Draw the flow: User A types → WebSocket → Server → Pub/Sub → WebSocket → User B
4. How do you scale WebSocket servers horizontally? (sticky sessions + Redis pub/sub)
5. User goes offline for 10 minutes. On reconnect, how does the document sync?

**Architecture Questions to Answer:**
- How does Redis Pub/Sub connect WebSocket servers across multiple nodes?
- For conflict resolution: choose OT or CRDT and justify.
- How do you persist document changes durably? (event log → snapshots)

---

## 💬 Interview Q&A

### Q1: Why use WebSockets instead of HTTP polling for chat?
**Ans:** Polling wastes bandwidth (mostly empty responses), adds latency (wait for next poll interval), and creates server load. WebSockets maintain a persistent bidirectional connection — messages are pushed instantly in either direction with minimal overhead. No repeated handshakes.

### Q2: How do you scale WebSocket connections horizontally?
**Ans:** Problem: if User A is connected to Server 1 and User B to Server 2, Server 1 can't push to User B directly. Solution: Use Redis Pub/Sub as message broker — Server 1 publishes to Redis channel, Server 2 (subscribed to same channel) receives it and pushes to User B. Each user's connection is pinned to one server (sticky session at LB).

### Q3: What is the difference between OT and CRDT?
**Ans:** OT (Operational Transformation): transforms operations to account for concurrent edits — server serializes all ops. Complex to implement correctly. Used by Google Docs. CRDT (Conflict-free Replicated Data Type): mathematical data structure that can be merged in any order without conflicts — no server coordination needed. Used by Figma, newer collaborative tools. CRDTs work better for offline-first.

### Q4: What is presence detection and how do you implement it?
**Ans:** Knowing if a user is online. Implementation: When WebSocket connects, set `user:123:online = true` in Redis with 30s TTL. Send heartbeat every 25 seconds to refresh TTL. On disconnect or TTL expiry, user is offline. Subscribe to Redis key expiry events to notify other users of offline status.

---

## 🗒️ Quick Cheat Sheet
- **WebSocket handshake**: HTTP Upgrade request → 101 Switching Protocols
- **WS frame types**: Text, Binary, Ping, Pong, Close
- **Redis Pub/Sub**: Not persistent — messages lost if no subscriber. Use for real-time only.
- **Sticky sessions**: Same user always goes to same WebSocket server (IP Hash LB)
- **Heartbeat**: Client sends ping every 25s to keep connection alive through firewalls

---

## 📓 Your Notes
*Draw the collaborative editor architecture. Show how two users' edits are synchronized.*
