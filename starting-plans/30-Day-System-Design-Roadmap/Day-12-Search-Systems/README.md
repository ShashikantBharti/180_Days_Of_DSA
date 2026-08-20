# Day 12 — Search Systems & Elasticsearch
## Topic: Building Fast, Relevant Search at Scale

---

## 🎯 What to Learn Today

- How search engines work: inverted index
- Elasticsearch architecture: nodes, shards, replicas
- Relevance ranking: TF-IDF, BM25
- Types of search: full-text, fuzzy, prefix, semantic/vector
- Syncing your database to Elasticsearch
- Autocomplete system design

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Elasticsearch Docs | https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html | 20 min |
| How Search Works — ByteByteGo | https://www.youtube.com/@ByteByteGo | 15 min |
| Inverted Index Explained | https://medium.com/system-design-blog | 15 min |
| Autocomplete Design | https://github.com/donnemartin/system-design-primer | 15 min |

---

## 🧠 Key Concepts

- **Inverted Index**: Maps each word → list of document IDs containing that word. Core of all search engines.
- **TF-IDF**: Term Frequency × Inverse Document Frequency — measures how relevant a word is to a document vs corpus.
- **BM25**: Improved TF-IDF used by Elasticsearch by default — handles term saturation better.
- **Tokenization**: Breaking text into searchable tokens (e.g., "Running shoes" → ["running", "shoes"])
- **Stemming**: Reduce words to root form (runs → run, running → run)
- **Fuzzy Search**: Find results even with typos (Levenshtein distance)
- **Sharding in ES**: An index is split into primary shards (for scale) + replica shards (for redundancy/read scale)
- **Sync Strategies**: DB → Kafka → Elasticsearch consumer (async, eventual consistency)

---

## 🔢 Inverted Index Example

```
Documents:
  Doc1: "the quick brown fox"
  Doc2: "the fox jumps high"
  Doc3: "quick brown rabbit"

Inverted Index:
  "quick"  → [Doc1, Doc3]
  "fox"    → [Doc1, Doc2]
  "brown"  → [Doc1, Doc3]
  "jumps"  → [Doc2]

Query "quick fox":
  quick → [Doc1, Doc3]
  fox   → [Doc1, Doc2]
  Intersection or union + ranking → [Doc1 (highest), Doc2, Doc3]
```

---

## 🧩 Problem Statement to Solve

> **Design a Search Feature for an E-Commerce Platform**
> - 100 million products in catalog
> - 1 billion search queries per day
> - Sub-100ms response time for any query
> - Support: typo tolerance, filters (price range, category, brand, rating), sort by (relevance, price, newest)
> - Personalized results: boost products user previously bought or viewed
> - Autocomplete suggestions while user types

**Search System Design Task:**
1. How do you index 100M products in Elasticsearch? (batch indexing + incremental sync)
2. How does filtering by `price: [10 TO 100]` work in Elasticsearch?
3. How do you boost personalized results? (re-rank based on user history score)
4. For autocomplete, what data structure is most efficient? (Trie vs Elasticsearch prefix)
5. A product's price changes. How does Elasticsearch get updated? (Kafka CDC pipeline)

**Architecture Questions to Answer:**
- How many Elasticsearch shards for 100M products? (1 shard ≈ 50GB, so ~5 shards + replicas)
- How do you keep ES in sync with the product database?
- How do you handle search for queries with zero results? (fallback to fuzzy/broader search)

---

## 💬 Interview Q&A

### Q1: What is an inverted index and how does it enable fast search?
**Ans:** An inverted index maps each unique term to the list of documents containing it. Instead of scanning all documents for a query term (O(n)), search becomes a lookup in the index (O(1) or O(log n)). This is how Google, Elasticsearch, and Solr all work at their core.

### Q2: What is the difference between full-text search and exact match search?
**Ans:** Exact match (SQL `WHERE name = 'iPhone'`) requires exact string match — fails on "iphone" or "iPhone 14". Full-text search tokenizes, stems, and ranks by relevance — finds "iPhones" for query "iphone" and ranks most relevant documents first using BM25 scoring.

### Q3: How do you keep Elasticsearch in sync with your main database?
**Ans:** Common pattern: (1) Use Change Data Capture (CDC) — Debezium reads DB binlog and publishes to Kafka, (2) Elasticsearch consumer subscribes to Kafka and indexes documents. Alternative: dual-write from application (risky — can get out of sync). Async sync means ES has eventual consistency with DB.

### Q4: How does autocomplete work at scale?
**Ans:** Two approaches: (1) **Trie (prefix tree)**: Store all search terms in a trie, traverse to prefix for suggestions — fast but memory intensive. (2) **Elasticsearch prefix/completion suggester**: Store completion data type, use prefix query. At Google scale: pre-compute suggestions per prefix in offline jobs, cache top-K in Redis.

---

## 🗒️ Quick Cheat Sheet
- **Primary Shard**: Where documents are written (set at index creation, immutable)
- **Replica Shard**: Copy of primary (can add/remove anytime) — read scaling + redundancy
- **Match Query**: Full-text search with relevance scoring
- **Term Query**: Exact match (for IDs, status fields — not analyzed)
- **Bool Query**: Combine must/should/must_not/filter clauses
- **Index Alias**: Point one alias to multiple indices — enables zero-downtime re-indexing

---

## 📓 Your Notes
*Design the e-commerce search architecture. Draw the indexing pipeline from DB → ES.*
