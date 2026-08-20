# Day 25 — Design WhatsApp at Scale
## Topic: Full System Design — Real-World Messaging at 3 Billion Users

---

## 🎯 Today's Goal

Design WhatsApp end-to-end in 45 minutes (timed practice). After designing, verify against the reference below.

**⏱️ Set a 45-minute timer NOW before reading further.**

---

## 📐 Requirements

**Functional:**
- 1:1 messaging and group chats (up to 1,024 members)
- Media sharing: photos, videos, documents, audio
- End-to-end encryption (E2EE)
- Message delivery receipts: sent ✓, delivered ✓✓, read ✓✓ (blue)
- Online presence and "Last seen" status
- Message history synced across devices

**Non-Functional:**
- 3 billion users, 100 billion messages per day
- Message delivered in < 1 second (P99)
- 99.99% availability
- E2EE: WhatsApp servers cannot read message content

---

## 🧠 Key Design Decisions

### Transport Layer
- **WebSockets** for active users (persistent bidirectional connection)
- **APNS/FCM Push Notifications** for offline users (wake app, fetch messages)

### Message Storage
- **Cassandra**: shard by `conversation_id`. Store: message_id, sender_id, content (encrypted), timestamp, media_url, status
- Messages stored on server only until delivered to all recipients (then optionally in user's device backup)

### Media Sharing
- Upload media to **S3** via pre-signed URL (bypasses chat server — direct upload)
- Send only the media URL + thumbnail in the message
- CDN serves media downloads (cached at edge)

### End-to-End Encryption (Signal Protocol)
- Each device has a public/private key pair
- Sender encrypts message with recipient's public key
- Only recipient's private key can decrypt (server never sees plaintext)
- Key exchange via WhatsApp's key server

### Delivery Receipts
- Sent ✓: message stored in server DB
- Delivered ✓✓: recipient device ACKs receipt → server updates status
- Read ✓✓ blue: recipient app marks as read → server notified → sender receives status update

### Group Messaging
- Groups with 1,024 members: use fan-out — server delivers to each member's message queue
- For large groups, fan-out handled async via queue (Kafka) to avoid timeout

### Presence System
- User connects → set `user:123:online = true` in Redis (TTL 35s)
- Heartbeat every 30s refreshes TTL
- On disconnect: TTL expires → user marked offline
- "Last seen" timestamp stored in DB on disconnect

---

## 📊 Data Model

```sql
-- Messages Table (Cassandra)
CREATE TABLE messages (
  conversation_id UUID,
  message_id     TIMEUUID,  -- includes timestamp
  sender_id      UUID,
  content        BLOB,      -- encrypted ciphertext
  media_url      TEXT,
  msg_type       TEXT,      -- text, image, video, audio
  status         TEXT,      -- sent, delivered, read
  PRIMARY KEY (conversation_id, message_id)
) WITH CLUSTERING ORDER BY (message_id DESC);

-- Users Table (PostgreSQL)
CREATE TABLE users (
  user_id    UUID PRIMARY KEY,
  phone      TEXT UNIQUE,
  name       TEXT,
  avatar_url TEXT,
  last_seen  TIMESTAMP
);
```

---

## 🏗️ Architecture Diagram

```
Mobile App
  ↓ WebSocket (active user) / Push notification (offline)
Load Balancer
  ↓
Chat Server (stateful — WebSocket connections pinned)
  ↓ publish message           ↓ lookup recipient connection
Message Queue (Kafka)     Connection Router (Redis: user→server mapping)
  ↓
Message Worker → Cassandra (store message)
             → Push Notification Service (if recipient offline)
             → Media Service (if media attachment)

Media Upload Flow:
  App → API Server → Pre-signed S3 URL → App uploads directly to S3
  App sends message with S3 URL → Chat Server → Recipients
```

---

## 💬 Interview Q&A

### Q1: How does E2EE work in WhatsApp?
**Ans:** Signal Protocol: each device generates a public/private key pair. Public keys registered on WhatsApp key server. When Alice sends to Bob: Alice fetches Bob's public key, encrypts message, sends ciphertext. WhatsApp server stores and relays ciphertext. Bob's device decrypts with his private key. Server only sees encrypted blobs — mathematically cannot decrypt.

### Q2: How does WhatsApp handle offline message delivery?
**Ans:** If recipient is offline, message stored in Cassandra. When recipient comes online, they connect via WebSocket and pull pending messages. If recipient is offline and has push notifications enabled: WhatsApp sends a push notification via APNS (iOS) or FCM (Android) → app wakes up → establishes WebSocket → fetches messages.

### Q3: How do you scale WebSocket connections for 3B users?
**Ans:** Horizontal scaling of chat servers. Each server handles ~100K concurrent WebSocket connections. Redis stores mapping: `user_id → chat_server_id`. Load balancer does sticky sessions (IP hash) to route user's connections to same server. When Server A receives a message for user B (on Server C): publish to Kafka → Server C's consumer pushes to user B's WebSocket.

---

## 🗒️ Quick Cheat Sheet
- **100B messages/day** → 1.15M messages/second
- **1M messages/sec** × 1KB avg → 1 GB/sec write throughput to Cassandra
- **Cassandra**: ideal (high write throughput, replication, time-ordered retrieval)
- **Group messages**: fan-out handled async to avoid blocking sender

---

## 📓 Your Notes — Self Assessment
Rate yourself: 🔴 Missed key components | 🟡 Had partial design | 🟢 Complete design

*What did you get right? What did you miss? Document gaps here.*
