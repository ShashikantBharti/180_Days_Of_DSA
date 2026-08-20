# Day 20 — Time-Series & Analytics Systems
## Topic: Processing and Querying Billions of Time-Stamped Data Points

---

## 🎯 What to Learn Today

- What is time-series data and why it needs special databases
- Time-series databases: InfluxDB, TimescaleDB, Prometheus
- Data partitioning by time (time-based sharding)
- Aggregations and downsampling (reducing data volume over time)
- OLTP vs OLAP — the fundamental difference
- Apache Druid, ClickHouse for real-time analytics at scale

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| InfluxDB Concepts | https://docs.influxdata.com/influxdb/v2/reference/key-concepts/ | 15 min |
| ClickHouse — Why It's Fast | https://clickhouse.com/docs/en/intro | 15 min |
| OLAP vs OLTP — ByteByteGo | https://www.youtube.com/@ByteByteGo | 15 min |
| Apache Druid Architecture | https://druid.apache.org/docs/latest/design/ | 20 min |

---

## 🧠 Key Concepts

- **Time-Series Data**: Data points indexed by timestamp. Append-only, high write throughput.
- **Time-Based Partitioning**: Store data in time buckets (hourly, daily partitions). Old data archived/compressed.
- **Downsampling**: Aggregate raw data into lower resolution over time (raw 1-sec → 1-min avg after 1 day → 1-hr avg after 30 days). Saves storage.
- **OLTP (Online Transaction Processing)**: Optimized for individual record reads/writes. Normalized tables. Row-store. (PostgreSQL, MySQL)
- **OLAP (Online Analytical Processing)**: Optimized for aggregate queries over many rows. Columnar storage. (BigQuery, Redshift, ClickHouse)
- **Columnar Storage**: Stores each column separately → read only needed columns. Highly compressed. 10-100x faster for analytics.
- **Pre-aggregation**: Compute common aggregations in advance (materialized views). Trade storage for query speed.

---

## 🔢 OLTP vs OLAP

```
OLTP (PostgreSQL, MySQL):
  Query: SELECT * FROM orders WHERE id = 123
  Reads: few rows, all columns
  Writes: frequent, individual records
  Storage: row-oriented (great for point lookups)
  Optimization: indexes on primary keys

OLAP (ClickHouse, BigQuery):
  Query: SELECT country, SUM(revenue) FROM orders
         WHERE date > '2024-01-01' GROUP BY country
  Reads: millions of rows, 2-3 columns
  Writes: batch, append-only
  Storage: column-oriented (great for aggregations)
  Optimization: sorted by date, compressed per column
```

---

## 🧩 Problem Statement to Solve

> **Design a Real-Time Analytics Dashboard (like Mixpanel)**
> - Track user events across 10,000 apps: page views, clicks, purchases, etc.
> - 1 trillion events per month ingested (≈ 380M events/hour)
> - Query: "Show DAU for last 90 days filtered by country" — must return in < 2 seconds
> - Support: funnel analysis, retention cohorts, A/B test results
> - Data freshness: events visible in dashboard within 30 seconds of occurring

**Analytics System Design Task:**
1. Draw the ingestion pipeline: SDK → API → Kafka → Stream Processor → ClickHouse
2. How does columnar storage make "SUM(revenue) by country" 100x faster than row storage?
3. Pre-aggregation: pre-compute DAU per app per day → how much storage does this save?
4. A customer queries "all users who did A then B within 48 hours" — how do you design this (funnel query)?
5. Old data (>1 year): keep raw events or only aggregated summaries?

**Architecture Questions to Answer:**
- Why Kafka between SDK and analytics DB? (absorb write spikes, decouple ingestion from storage)
- How does time-based partitioning help query performance? (only scan today's partition for today's data)
- How do you backfill historical data when schema changes?

---

## 💬 Interview Q&A

### Q1: Why are columnar databases better for analytics than row databases?
**Ans:** Analytics queries read few columns but many rows (e.g., SELECT country, SUM(revenue)). Columnar storage stores all values of `country` together and all values of `revenue` together. Reading only those 2 columns skips 98% of data. Also compresses much better (same column = similar values). ClickHouse can scan 1 billion rows/second.

### Q2: What is downsampling in time-series databases?
**Ans:** Raw data is stored at high resolution (1-second intervals). As data ages, aggregate it into lower resolution: after 1 day, keep 1-minute averages. After 30 days, keep 1-hour averages. After 1 year, keep daily averages. Original data is deleted. Reduces storage from terabytes to gigabytes while preserving trend visibility.

### Q3: What is the difference between a streaming and batch analytics pipeline?
**Ans:** Batch: process data in large chunks periodically (hourly/daily jobs). Simple, cheap, but data can be hours old. Streaming: process events in real-time as they arrive (Kafka Streams, Flink). Data visible in seconds. More complex, more expensive. Use lambda architecture: streaming for real-time metrics, batch for accurate historical reports.

### Q4: What is a materialized view and how does it help?
**Ans:** A pre-computed query result stored as a table and updated automatically. Example: pre-compute DAU (distinct users per day per app). Instead of counting 1B events at query time, read from 10,000-row materialized view. Query time: 100ms instead of 2 minutes. Trade-off: more storage, slight data staleness.

---

## 🗒️ Quick Cheat Sheet
- **Write path**: Kafka → Flink/Spark Streaming → ClickHouse / Druid
- **ClickHouse**: Fastest OLAP DB. Queries 100B rows in seconds. Used by Cloudflare, Yandex.
- **Druid**: Real-time OLAP with sub-second queries. Good for interactive dashboards.
- **BigQuery**: Serverless OLAP by Google. Pay per query. Petabyte scale.
- **Partition pruning**: DB skips partitions that can't contain query data (date filter → skip old partitions)

---

## 📓 Your Notes
*Design the Mixpanel analytics pipeline. Show data flow from SDK event to dashboard query.*
