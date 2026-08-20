# Day 22 — Machine Learning Systems Design
## Topic: Designing Systems That Learn and Predict at Scale

---

## 🎯 What to Learn Today

- Components of an ML system: training pipeline, model serving, feature store
- Online (real-time) vs batch prediction
- Feature Store: what it is and why you need it
- Model versioning and A/B testing ML models
- Dealing with training/serving skew
- Cold start problem and how to solve it

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| ML System Design — Chip Huyen | https://huyenchip.com/machine-learning-systems-design/ | 25 min |
| Feature Store Explained | https://www.tecton.ai/blog/what-is-a-feature-store/ | 15 min |
| Netflix Recommendation System | https://netflixtechblog.com/ | 20 min |
| ML A/B Testing | https://medium.com/system-design-blog | 15 min |

---

## 🧠 Key Concepts

- **Feature**: Input variable used by ML model (user_age, past_purchases, item_popularity)
- **Feature Store**: Central repository for pre-computed features. Shared between training and serving. Ensures training/serving consistency.
- **Training Pipeline**: Collect data → preprocess → train model → evaluate → publish to registry
- **Model Registry**: Versioned storage of trained models with metadata (accuracy, training date)
- **Online (Real-Time) Inference**: Model makes prediction at request time (< 100ms). E.g., fraud detection.
- **Batch Inference**: Run predictions on entire dataset offline (e.g., pre-compute recommendations for all users nightly).
- **Training/Serving Skew**: Model was trained with feature X defined one way, but serving computes it differently → degraded accuracy.
- **Cold Start**: New user (no history) or new item (no interactions) — model has no data to work with.

---

## 🔢 ML System Architecture

```
Offline Path (Training):
  Raw Data → Feature Pipeline → Feature Store → Model Training
  → Model Evaluation → Model Registry

Online Path (Serving):
  User Request → Feature Lookup (Feature Store) → Model Server
  → Prediction → Response → Log (prediction + outcome)

Feedback Loop:
  Logged predictions + actual outcomes → Training data
  → Periodic model retraining → Deploy new model version
```

---

## 🧩 Problem Statement to Solve

> **Design a Recommendation System (like Netflix/Spotify)**
> - 200 million users, recommend from 15,000 movies or 80 million songs
> - Cold start: new user signs up (no history) — what do you recommend?
> - Real-time session context: user just watched a thriller → recommend more thrillers NOW
> - A/B test: new recommendation algorithm vs old one — fair comparison
> - Freshness: new content released today should appear in recommendations today

**ML System Design Task:**
1. Features for recommendation: what features describe the user? What describe the content?
2. Algorithm: Collaborative Filtering vs Content-Based vs Hybrid — what does Netflix use?
3. Cold start: user has no history → how do you recommend? (popularity-based → ask preferences → profile-based)
4. Where does the model inference happen? (batch pre-compute vs real-time)
5. How do you A/B test recommendation algorithms fairly? (user-level split, not request-level)

**Architecture Questions to Answer:**
- How does the Feature Store prevent training/serving skew?
- How does embedding similarity work for content-based recommendation?
- How do you measure if the new recommendation model is "better"? (CTR, watch time, user retention)

---

## 💬 Interview Q&A

### Q1: What is a Feature Store and why do you need one?
**Ans:** A Feature Store is a central system that stores pre-computed features (both historical for training and real-time for serving). Without it: training team computes feature X one way; serving team implements it differently → training/serving skew → model performs worse in production. Feature Store ensures the exact same feature computation is used for training and serving.

### Q2: What is the cold start problem and how do you handle it?
**Ans:** Cold start = no data for a new user or new item. Solutions: (1) Popularity-based recommendations (show globally trending items), (2) Ask users to select preferences during onboarding, (3) Use demographic/contextual data (location, device, time), (4) Content-based: for new content, recommend based on metadata (genre, actors) before interaction data accumulates.

### Q3: What is collaborative filtering?
**Ans:** "Users who liked what you liked, also liked X." Two types: User-based (find similar users, recommend what they liked) and Item-based (find items similar to what you liked). Matrix factorization (SVD) is the standard approach — decomposes user-item interaction matrix into latent factor vectors. Netflix uses this extensively.

### Q4: How do you A/B test a new ML model in production?
**Ans:** Split users into control (model A) and treatment (model B) groups — must be consistent per user across sessions. Measure business metrics (CTR, watch time, purchases) over sufficient time period (2 weeks minimum for statistical significance). Use t-test or chi-squared test to check if difference is statistically significant. Only then roll out model B to 100%.

---

## 🗒️ Quick Cheat Sheet
- **Embedding**: Dense vector representation of user or item (256 dimensions). Similar items → similar vectors.
- **ANN search**: Approximate Nearest Neighbor search to find similar items fast (FAISS, ScaNN)
- **NDCG**: Normalized Discounted Cumulative Gain — offline metric for ranking quality
- **Explore vs Exploit**: Show new content (explore) vs proven liked content (exploit) — multi-armed bandit
- **Shadow mode**: Run new model in parallel, log predictions but don't serve them — validate before A/B

---

## 📓 Your Notes
*Design the Netflix recommendation architecture. Show training pipeline and online serving path.*
