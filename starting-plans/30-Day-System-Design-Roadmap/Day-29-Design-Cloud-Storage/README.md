# Day 29 — Design Cloud Storage (like AWS S3)
## Topic: Full System Design — Storing Exabytes with 11 Nines Durability

---

## 🎯 Today's Goal

Design AWS S3 (simplified). Set a 45-minute timer and design before reading.

---

## 📐 Requirements

**Functional:**
- Store objects (files) of any type up to 5TB each
- Create/delete/list buckets
- Upload, download, delete objects
- Object versioning: keep multiple versions of same key
- Lifecycle policies: auto-archive to cold storage after N days
- Access control: public/private, bucket policies, IAM roles

**Non-Functional:**
- 99.999999999% (11 nines) durability — 0.000000001% chance of data loss per year
- 99.99% availability
- Store exabytes of total data across billions of objects
- Single-digit millisecond latency for metadata operations (list, head)
- High throughput for large object transfers

---

## 🧠 Key Design Decisions

### Two Planes: Metadata vs Data

**Metadata Service (Control Plane):**
- Stores: bucket → object key → [location, size, etag, version_id, storage_class, acl]
- Strongly consistent (must always return accurate metadata)
- Database: distributed KV store (similar to DynamoDB or custom)
- Handles: authentication, authorization, ACL checks, object location lookup

**Data Service (Data Plane):**
- Actual object bytes stored on data nodes
- Object split into 64MB chunks → stored across multiple data nodes
- Erasure coding: 8 data chunks + 4 parity = store on 12 nodes. Survive 4 failures.
- No strong consistency needed — once written, immutable.

### Durability via Erasure Coding
```
Object uploaded → split into 8 data chunks + 4 parity chunks (Reed-Solomon)
→ Each chunk stored on different physical node in different rack
→ To reconstruct: need any 8 of 12 chunks
→ Survives: 4 simultaneous node/rack failures

vs Replication:
  3x replication: 3 full copies → survives 2 failures, 3x storage overhead
  Erasure coding: 1.5x storage overhead, survives 4 failures
```

### Multipart Upload (for large files)
- Files > 100MB: client splits into chunks, uploads in parallel
- Server assembles chunks on completion
- Tolerates network interruptions (resume from last successful part)

### Object Versioning
- Each PUT creates new version_id (UUID or timestamp)
- DELETE creates a delete marker (latest version = marker → object appears deleted)
- Restore: simply delete the delete marker
- Old versions stored until lifecycle policy expires them

### Write Path
```
Client PUT /bucket/key?
  ↓ API Gateway (auth + rate limit)
  ↓ Metadata Service: check permissions, allocate version_id
  ↓ Data Service: assign data nodes, start transfer
  ↓ Client streams data → Data Nodes (with erasure coding)
  ↓ Data commit confirmed
  ↓ Metadata Service: commit object metadata → return 200 OK + ETag
```

### Read Path
```
Client GET /bucket/key
  ↓ API Gateway (auth check)
  ↓ Metadata Service: lookup object location, verify ACL
  ↓ Data Service: fetch chunks from data nodes, reconstruct if needed
  ↓ Return object bytes to client (streaming)
```

---

## 📊 Data Model

```
Metadata Store (distributed KV):
  Key: "{bucket_id}:{object_key}:{version_id}"
  Value: {
    size: 10485760,
    etag: "md5-hash",
    content_type: "image/jpeg",
    storage_class: "STANDARD",
    chunks: [
      { chunk_id: "c001", nodes: ["node1", "node5", "node9", ...] },
      ...
    ],
    acl: "private",
    created_at: "2024-01-15T10:30:00Z"
  }
```

---

## 💬 Interview Q&A

### Q1: How does S3 achieve 11 nines of durability?
**Ans:** Multiple layers: (1) Erasure coding: data survives any 4 of 12 chunk failures, (2) Data stored across multiple AZs (independent power, network, cooling), (3) Continuous data integrity checks (checksums verified periodically — corrupt data auto-repaired), (4) Redundant network paths, (5) Geographic replication option (cross-region). The math: probability of losing all redundant copies before repair occurs is infinitesimally small.

### Q2: What is the difference between eventual and strong consistency in S3?
**Ans:** S3 historically offered eventual consistency for overwrite PUTs and DELETEs (reading after write might return stale data for a brief window). In 2021, AWS upgraded S3 to provide strong read-after-write consistency for all operations — reads always reflect the latest write. This was achieved by the metadata service maintaining a strongly consistent view.

### Q3: How does multipart upload improve reliability for large files?
**Ans:** Uploading a 5TB file as one stream: if connection drops after 4.9TB, entire upload must restart. Multipart upload splits file into chunks (5MB-5GB each) uploaded independently. If one part fails, retry only that part. Parts uploaded in parallel (10x faster on fat pipe). Server stores parts temporarily until complete MPU API call assembles them.

### Q4: How does S3 handle a hot object (millions of requests/second for same key)?
**Ans:** S3 automatically scales to handle hot objects — underlying data nodes are abstracted. Object's data chunks are replicated to more nodes when access patterns are high. Bucket names with random prefixes (not date-based) improve distribution across S3's internal partitions. For extreme cases: CloudFront CDN in front of S3 absorbs read traffic at edge.

---

## 🗒️ Quick Cheat Sheet
- **ETag**: MD5 hash of object content (for integrity verification)
- **Presigned URL**: Time-limited URL granting access to private object (no auth required)
- **S3 Glacier**: Cold storage tier. Cents per GB/month. Retrieval: minutes to hours.
- **Object Lock (WORM)**: Write Once Read Many — prevent modification/deletion (compliance)
- **Intelligent Tiering**: S3 auto-moves objects between tiers based on access patterns

---

## 📓 Your Notes — Self Assessment
Rate yourself: 🔴 Missed key components | 🟡 Partial design | 🟢 Complete design

*Did you cover both the metadata service and data service separation?*
