# Day 16 — Observability: Logging, Metrics & Tracing
## Topic: Understanding What Your System is Doing in Production

---

## 🎯 What to Learn Today

- The 3 pillars of observability: Logs, Metrics, Traces
- Structured logging (JSON logs) and log aggregation (ELK Stack)
- Metrics: Prometheus + Grafana setup
- Distributed tracing: Jaeger, Zipkin, OpenTelemetry
- SLI, SLO, SLA — what they mean and how to set them
- Alerting best practices and on-call strategies

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| Observability vs Monitoring | https://www.honeycomb.io/what-is-observability/ | 15 min |
| Prometheus + Grafana Getting Started | https://prometheus.io/docs/introduction/overview/ | 20 min |
| OpenTelemetry Docs | https://opentelemetry.io/docs/ | 15 min |
| Google SRE Book (SLI/SLO) | https://sre.google/sre-book/service-level-objectives/ | 20 min |

---

## 🧠 Key Concepts

- **Logs**: Timestamped records of events ("User 123 logged in at 10:34:22")
- **Metrics**: Numeric measurements over time (request_count, error_rate, p99_latency)
- **Traces**: End-to-end record of a request across multiple services (with span timings)
- **ELK Stack**: Elasticsearch (storage) + Logstash (processing) + Kibana (visualization)
- **Prometheus**: Pull-based metrics collection. Services expose `/metrics` endpoint.
- **Grafana**: Dashboard visualization on top of Prometheus, Loki, etc.
- **Jaeger/Zipkin**: Distributed tracing systems — visualize request flow across services
- **SLI (Service Level Indicator)**: Metric that measures service behavior (e.g., error rate)
- **SLO (Service Level Objective)**: Target for SLI (e.g., error rate < 0.1%)
- **SLA (Service Level Agreement)**: Contractual commitment to customer based on SLO
- **Error Budget**: How much you can violate the SLO (100% - SLO%). Burn it responsibly.

---

## 🔢 SLI/SLO/SLA Example

```
SLI: 99th percentile latency measured over 30 days
SLO: p99 latency < 200ms for 99.9% of requests
SLA: If SLO is violated, customer gets 10% credit

Error Budget: 0.1% of requests can be > 200ms
Monthly error budget: 0.1% × 30 days × 86,400 s = 2,592 seconds
If 3,000 seconds exceeded → SLO breach → review needed
```

---

## 🧩 Problem Statement to Solve

> **Design a Monitoring System for a Microservices Platform**
> - 500 microservices, 10,000 containers across 3 regions
> - Detect anomalies (latency spike, error rate rise) within 30 seconds
> - Trace a single API request through 15+ services
> - Alert on-call engineers with context (not just "server down — which server? which service?)
> - Store logs for 30 days for compliance

**Observability Design Task:**
1. Draw the logging pipeline: App → Fluentd/Filebeat → Kafka → Logstash → Elasticsearch → Kibana
2. What SLIs would you track for a payment service? (error rate, latency p99, throughput)
3. A user reports "checkout was slow at 3pm". How do you find the root cause? (distributed trace)
4. How do you alert on "error rate > 1% for last 5 minutes" without too many false positives?
5. How much storage for logs? (500 services × 10MB/min × 60min × 24hr × 30days = ?)

**Architecture Questions to Answer:**
- How does sampling work for distributed traces? (100% sampling is too expensive)
- What is the difference between a metric alert and a log-based alert?
- How does OpenTelemetry enable vendor-neutral observability?

---

## 💬 Interview Q&A

### Q1: What is the difference between monitoring and observability?
**Ans:** Monitoring tells you WHEN something is wrong (alert fires). Observability tells you WHY something is wrong — you can ask arbitrary questions about internal state from external outputs (logs, metrics, traces). With good observability, you can debug novel failures you've never seen before without deploying new code.

### Q2: What is distributed tracing and why is it needed?
**Ans:** When a user request flows through 15 microservices, understanding which service caused a 2-second delay is impossible from logs alone. Distributed tracing assigns a unique trace_id to each request and records spans (timing) at each service. Jaeger/Zipkin visualizes the full call tree with timings — you can instantly see that the DB query in Service 9 took 1.8 seconds.

### Q3: What is the difference between SLI, SLO, and SLA?
**Ans:** SLI is what you measure (actual error rate = 0.05%). SLO is your target (error rate < 0.1%). SLA is the promise you make to customers (if error rate > 0.1% for a month, they get credits). SLO should be stricter than SLA to give you room to respond before breaching the contract.

### Q4: How do you prevent alert fatigue in on-call systems?
**Ans:** (1) Alert on symptoms (user-facing SLOs) not causes (CPU > 80%), (2) Page only for actionable alerts, (3) Use alert thresholds with minimum duration (avoid single spike alerts), (4) Implement alert grouping (don't send 100 alerts for same root cause), (5) Have runbooks for each alert — engineer knows exactly what to do.

---

## 🗒️ Quick Cheat Sheet
- **p50 latency**: 50% of requests are faster than this (median)
- **p99 latency**: 99% of requests are faster than this (worst 1% experience)
- **Error budget**: 30 days × (1 - SLO%). Spend it on risky deployments.
- **Trace ID**: Unique ID propagated through all service calls via HTTP header
- **Pull metrics**: Prometheus scrapes services on schedule (vs push: services send to collector)

---

## 📓 Your Notes
*Define SLIs and SLOs for a checkout service. Draw the observability stack architecture.*
