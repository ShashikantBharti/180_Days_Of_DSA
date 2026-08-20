# Day 03 — Databases: SQL vs NoSQL
## Topic: Choosing the Right Database for the Right Problem

---

## 🎯 What to Learn Today

- Relational (SQL) databases: structure, ACID properties
- NoSQL types: Key-Value, Document, Column-Family, Graph
- When to use SQL vs NoSQL
- Indexing: how it speeds up queries
- Database normalization (1NF, 2NF, 3NF)
- ACID vs BASE consistency models

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| SQL vs NoSQL — ByteByteGo | https://www.youtube.com/@ByteByteGo | 20 min |
| ACID Properties Explained | https://www.databricks.com/glossary/acid-transactions | 15 min |
| CAP Theorem Visual | https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/ | 20 min |
| Indexing Explained Simply | https://use-the-index-luke.com/ | 20 min |

---

## 🧠 Key Concepts

- **SQL (Relational)**: Tables with rows/columns, strict schema, JOINs, ACID (PostgreSQL, MySQL)
- **Key-Value Store**: Simple map of key → value, ultra-fast reads (Redis, DynamoDB)
- **Document DB**: Flexible JSON-like documents, no fixed schema (MongoDB, CouchDB)
- **Column-Family**: Stores data by column groups, great for analytics (Cassandra, HBase)
- **Graph DB**: Nodes and edges for relationship-heavy data (Neo4j)
- **ACID**: Atomicity, Consistency, Isolation, Durability
- **BASE**: Basically Available, Soft state, Eventually consistent (NoSQL tradeoff)
- **Index**: Data structure (B-Tree) that speeds up SELECT queries at cost of slower writes

---

## 🔢 When to Use What

```
Use SQL when:
  ✅ Data is structured and schema is stable
  ✅ Need complex JOINs across multiple tables
  ✅ ACID transactions required (banking, e-commerce)
  ✅ Reporting and analytics queries

Use NoSQL when:
  ✅ Need horizontal scaling (massive write throughput)
  ✅ Flexible/evolving schema
  ✅ High read speed with simple key lookups
  ✅ Storing user sessions, shopping carts, logs, events
```

---

## 🧩 Problem Statement to Solve

> **Design a User Profile System**
> - Store user data: name, email, bio, preferences, avatar URL
> - 500 million registered users
> - Fast reads in < 5ms for profile lookup by user ID
> - Support flexible schema (different profile fields per user type: individual vs business)
> - Need to support searching by email (exact match)

**Database Design Task:**
1. Design the SQL schema: What tables? What columns? What indexes?
2. Argue: Why might MongoDB (Document DB) be better here than PostgreSQL?
3. What index would you create to make `SELECT * WHERE email = ?` fast?
4. How would you store user preferences (key-value pairs per user)?

**Architecture Questions to Answer:**
- Single database or sharded? At 500M users?
- Read replicas to handle read traffic?
- Cache layer (Redis) in front of DB?

---

## 💬 Interview Q&A

### Q1: What are the ACID properties?
**Ans:** **A**tomicity (all or nothing — transaction fully completes or fully rolls back), **C**onsistency (DB always in valid state), **I**solation (concurrent transactions don't interfere), **D**urability (committed data survives crashes).

### Q2: What is the CAP Theorem?
**Ans:** A distributed system can only guarantee 2 of 3: **C**onsistency (all nodes see same data), **A**vailability (system always responds), **P**artition Tolerance (works despite network splits). Since network partitions happen, you must choose CP or AP.

### Q3: What is database normalization?
**Ans:** Organizing tables to reduce data redundancy. 1NF: atomic columns. 2NF: no partial dependencies. 3NF: no transitive dependencies. Normalization reduces duplication but can require more JOINs.

### Q4: When would you denormalize a database?
**Ans:** When read performance is critical and you want to avoid expensive JOINs. Denormalization duplicates data to make reads faster — common in analytics/reporting databases.

### Q5: What is an index and how does it work?
**Ans:** An index is a separate data structure (usually B-Tree) that maps column values to row locations. Instead of full table scan (O(n)), indexed lookup is O(log n). Speeds up reads, slows down writes.

---

## 🗒️ Quick Cheat Sheet
- **Primary Key**: Unique identifier per row
- **Foreign Key**: Reference to another table's primary key
- **B-Tree Index**: Default index type, great for range queries
- **Hash Index**: Great for exact match, not range queries
- **Composite Index**: Index on multiple columns — order matters!

---

## 📓 Your Notes
*Design the user profile schema here. Draw an ER diagram.*
