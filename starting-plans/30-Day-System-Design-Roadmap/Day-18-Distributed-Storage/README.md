# Day 18 — Distributed Storage Systems
## Topic: Storing Petabytes of Data Reliably

---

## 🎯 What to Learn Today

- Object storage vs Block storage vs File storage
- How Amazon S3 works internally
- HDFS (Hadoop Distributed File System) architecture
- Erasure coding vs Replication for data durability
- Data lake vs Data warehouse
- Metadata management at scale

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| How S3 Works — AWS Blog | https://aws.amazon.com/s3/ | 15 min |
| HDFS Architecture | https://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-hdfs/HdfsDesign.html | 20 min |
| Erasure Coding Explained | https://www.backblaze.com/blog/reed-solomon/ | 15 min |
| Data Lake vs Warehouse | https://www.databricks.com/discover/data-lakes | 15 min |

---

## 🧠 Key Concepts

- **Object Storage (S3)**: Flat namespace, access by unique key. Ideal for unstructured data (images, videos, backups). Massively scalable. No filesystem hierarchy.
- **Block Storage (EBS)**: Raw storage volumes, OS-level filesystem. Low latency. Use for databases, VMs.
- **File Storage (EFS/NFS)**: Shared filesystem accessed by multiple servers. For shared config files, workloads needing filesystem semantics.
- **Replication**: Store N copies on N nodes. Simple but uses 3x storage.
- **Erasure Coding**: Split object into K data chunks + M parity chunks. Can reconstruct from any K chunks. Uses only 1.5x storage for same durability as 3x replication.
- **HDFS**: Namenode (metadata), Datanodes (data blocks). 128MB blocks. Replication factor 3.
- **Data Lake**: Store raw data in object storage (S3). Schema-on-read.
- **Data Warehouse**: Processed, structured data optimized for analytics queries (Redshift, BigQuery).

---

## 🔢 Durability Comparison

```
3x Replication:
  - Data on 3 nodes. Survive 2 simultaneous failures.
  - Storage overhead: 3x (1TB data = 3TB storage)
  - Simple to implement

Erasure Coding (8+4):
  - 8 data chunks + 4 parity chunks = 12 chunks
  - Any 8 of 12 needed to reconstruct
  - Survive 4 simultaneous failures
  - Storage overhead: 1.5x (1TB data = 1.5TB storage)
  - More CPU overhead for reconstruction
  - Used by Facebook, Google for cold storage
```

---

## 🧩 Problem Statement to Solve

> **Design Google Drive / Dropbox**
> - 1 billion users, store files of any type and size (up to 10GB per file)
> - Sync files across all devices automatically when file changes
> - Support collaborative file sharing with permission controls (owner, editor, viewer)
> - Version history: keep last 30 versions of every file
> - Storage capacity: 15GB free per user

**Storage System Design Task:**
1. How do you store a 5GB video file? (chunk into 4MB blocks, store chunks separately)
2. User changes one line in a 100MB document. Do you upload the whole file again? (delta sync — only upload changed chunks)
3. Two devices both modify the same file while offline. How do you handle the conflict?
4. How do you deduplicate storage? (if 1M users upload same file → store once, reference many)
5. How does version history work? (store block-level diffs, not full copies)

**Architecture Questions to Answer:**
- How does the metadata service differ from the blob storage service?
- How does Dropbox's client detect file changes? (filesystem watcher)
- How do you sync 1 billion users' files efficiently?

---

## 💬 Interview Q&A

### Q1: What is the difference between object, block, and file storage?
**Ans:** Block storage: raw storage volumes with no filesystem — OS formats it. Fast, low latency. Use for databases. File storage: shared filesystem mounted by multiple servers. Object storage: flat namespace, store any binary data with metadata, accessed via HTTP API. Infinitely scalable, cheap. Use for images, videos, backups.

### Q2: How does Amazon S3 achieve 11 nines of durability?
**Ans:** S3 uses erasure coding to spread data across multiple storage nodes within and across availability zones. Automated integrity checking detects and repairs corrupted data. Redundant infrastructure (power, network, cooling) per AZ. The probability of losing all copies before they can be repaired is astronomically small.

### Q3: How does file deduplication work in cloud storage?
**Ans:** Content-addressed storage: hash each block of data (SHA-256). If the hash already exists in the store, don't store the block again — just create a reference. One copy of the block serves many users. Especially effective for common file types (OS images, software packages). Reduces storage costs significantly.

### Q4: What is the difference between a Data Lake and Data Warehouse?
**Ans:** Data Lake: stores raw, unprocessed data in any format (CSV, JSON, Parquet, images) in object storage (S3). Schema is applied at query time (schema-on-read). Cheap storage, flexible. Data Warehouse: stores structured, processed, curated data in columnar format (Redshift, BigQuery). Schema enforced at write time. Fast analytical queries. Use both together: Data Lake for raw → ETL → Data Warehouse for analytics.

---

## 🗒️ Quick Cheat Sheet
- **Namenode (HDFS)**: Stores filesystem metadata (which blocks on which datanodes). Single point of failure — use HA mode with standby.
- **Chunk size**: 64-128MB in HDFS, 4MB in Dropbox
- **Content Addressing**: hash(data) = storage key. Same content → same key → automatic dedup.
- **Cold storage**: Rarely accessed data stored cheaply (S3 Glacier, tape). Retrieval takes hours.
- **Multipart upload**: Upload large files in parallel chunks → assemble on server

---

## 📓 Your Notes
*Design the Google Drive architecture. Show upload flow, sync flow, and version history.*
