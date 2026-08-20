# Day 27 — Design Google Search
## Topic: Full System Design — Searching 100 Billion Web Pages

---

## 🎯 Today's Goal

Design Google Search end-to-end. Set a 45-minute timer and design before reading.

---

## 📐 Requirements

**Functional:**
- Crawl the web: discover and index 100 billion web pages
- Return ranked search results for any query in < 200ms
- Support autocomplete: suggestions while user types
- Fresh content: new pages indexed within hours; updated pages within days
- Support features: knowledge graph, image search, news

**Non-Functional:**
- 8.5 billion searches per day (100K searches/second)
- Sub-200ms query response globally
- Index must be updated continuously (not just batch)
- Crawler must be "polite" (respect robots.txt, rate limits)

---

## 🧠 Key Components

### 1. Web Crawler
- **URL Frontier**: Priority queue of URLs to crawl. Seeded with known URLs.
- **Fetcher**: Downloads HTML for each URL (respects crawl-delay in robots.txt)
- **Parser**: Extracts text + outgoing links from HTML
- **Deduplication**: Bloom filter/fingerprinting to avoid re-crawling same content
- **DNS Cache**: Cache DNS resolutions to speed up fetching
- **Politeness**: Max 1 req/sec per domain; honor robots.txt

### 2. Inverted Index
- Tokenize text → remove stopwords → stem → build `word → [doc_id, position_list]` map
- Stored in distributed file system (GFS/Colossus)
- Forward index: doc_id → word_list (for document retrieval)
- Sharded by hash of word. Queries distributed to all shards (scatter-gather).

### 3. Ranking (PageRank + ML)
- **PageRank**: Iterative algorithm — page's rank = sum of ranks of pages linking to it / out-link count
- **ML Ranking (RankBrain, BERT)**: Hundreds of signals: content relevance, freshness, user engagement, site quality
- Offline: compute PageRank for entire web graph periodically
- Online: combine inverted index matches with PageRank + ML scores at query time

### 4. Query Processing
```
User types query
  ↓ Spell correction, query expansion
Scatter to all index shards (in parallel)
  ↓ each shard returns top-K results
Gather, merge, re-rank by ML model
  ↓ de-duplicate, apply safe search
Return top 10 results in < 200ms
```

### 5. Autocomplete
- **Trie**: Store popular search terms. Prefix lookup returns top-K suggestions by frequency.
- At Google scale: Precompute suggestions per prefix in offline job. Cache top-20 suggestions per prefix in Redis.
- Personalized suggestions: blend global popularity + user's own search history.

---

## 📊 Scale Estimates

```
Web pages: 100 billion
Average page size: 100KB → 10 PB raw HTML
Index size: ~10% of raw = 1 PB
Queries: 100,000/sec → need massive read throughput

Crawler:
  10,000 URLs/second crawl rate
  Re-crawl popular pages daily, rare pages monthly
```

---

## 💬 Interview Q&A

### Q1: How does a web crawler avoid crawling the same page twice?
**Ans:** (1) URL deduplication: store all seen URLs in a distributed hash set or Bloom filter. Before adding URL to frontier, check if seen. (2) Content deduplication: hash the page content (SimHash for near-duplicate detection). If content hash exists, skip storing (different URL, same content). (3) robots.txt: some sites disallow crawling certain paths.

### Q2: How does Google compute PageRank for 100 billion pages?
**Ans:** PageRank is computed iteratively using distributed graph processing (MapReduce or Pregel). Initialize all pages with rank 1/N. Each iteration: each page distributes its rank equally to its outgoing links. Repeat until convergence (~50-100 iterations). The web graph is stored in distributed storage. This runs as a batch job periodically (not real-time).

### Q3: How does Google return results in < 200ms for a query?
**Ans:** The inverted index is sharded across thousands of machines. For a query, it's sent in parallel to ALL shards simultaneously (scatter). Each shard returns its top-K documents in 10-20ms. A central aggregator collects results from all shards (gather), merges, and re-ranks. This parallel scatter-gather approach + pre-computed PageRank + cached results achieves sub-200ms globally.

### Q4: How does autocomplete work at Google's scale?
**Ans:** Offline job analyzes query logs → compute popularity of each prefix → store top-20 completions per prefix in a trie and distributed cache. When user types "sys", cached entry for "sys" returns ["system design", "system properties"...]. For personalization, blend global popularity with user's session/history. The infrastructure must handle 100K QPS with < 50ms latency (Redis cluster).

---

## 🗒️ Quick Cheat Sheet
- **Freshness**: News pages crawled every hour. Popular pages daily. Rare pages monthly.
- **Sitemaps**: Website provides sitemap.xml listing all pages → crawler can be efficient
- **DNS Resolver Cache**: Crawler maintains local DNS cache to avoid DNS lookup per URL
- **BERT**: ML model that understands query intent (semantic meaning, not just keywords)
- **Knowledge Graph**: Structured data about entities (people, places) shown as cards in results

---

## 📓 Your Notes — Self Assessment
Rate yourself: 🔴 Missed key components | 🟡 Partial design | 🟢 Complete design

*Which component was hardest to design? (Usually the crawler deduplication or ranking.)*
