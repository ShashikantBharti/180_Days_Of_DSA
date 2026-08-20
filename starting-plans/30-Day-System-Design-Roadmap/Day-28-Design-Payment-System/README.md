# Day 28 — Design a Payment System (like Stripe)
## Topic: Full System Design — Processing Payments Safely at Scale

---

## 🎯 Today's Goal

Design a payment system like Stripe. Set a 45-minute timer and design before reading.

---

## 📐 Requirements

**Functional:**
- Accept payments via credit/debit card, bank transfer (ACH), digital wallets
- Process 1 million transactions per day
- Support payouts to merchants
- Fraud detection: real-time decision in < 100ms per transaction
- PCI-DSS compliance (card data security)
- Reconciliation: verify every transaction matched between systems

**Non-Functional:**
- Exactly-once transaction processing (never charge twice)
- 99.99% availability
- All money movements auditable — full transaction history
- Latency: payment authorization < 2 seconds end-to-end

---

## 🧠 Key Design Decisions

### Idempotency
**MOST CRITICAL**: Network failures cause retries. Without idempotency, retries = double charges.
- Client generates `idempotency_key = UUID()` for each payment attempt
- Server stores `idempotency_key` → `payment_result` in database
- On retry: if key exists → return stored result (don't re-process)
- Key stored for 24 hours minimum

### Double-Entry Bookkeeping
- Every money movement: Debit one account, Credit another. Sum always = 0.
- Journal: immutable append-only ledger of all entries
- Balance = sum of all credit entries - sum of all debit entries

### Payment State Machine
```
INITIATED → FRAUD_CHECK → AUTHORIZED → CAPTURED → SETTLED
      ↓           ↓            ↓
   FAILED      DECLINED     REFUNDED
```

### Card Data Security (PCI-DSS)
- Raw card numbers NEVER stored on your servers
- Use **tokenization**: card number → opaque token stored by certified vault (Stripe's own or third-party)
- TLS 1.3 for all card data in transit
- Isolated card data environment (separate network, separate DB, limited access)

### Fraud Detection
- Real-time ML scoring: velocity checks, device fingerprint, IP reputation, behavior analysis
- Rule engine: hardcoded rules (block all transactions from specific country at midnight)
- Decision: APPROVE / DECLINE / 3DS_CHALLENGE (step-up authentication)
- Must complete in < 100ms (part of authorization flow)

### Reconciliation
- Nightly batch job: compare internal transaction log vs bank/card network statements
- Any discrepancy flagged for manual review
- "Three-way match": Stripe ledger ↔ Card Network settlement file ↔ Bank statement

---

## 📊 Data Model

```sql
-- Payments (PostgreSQL)
CREATE TABLE payments (
  payment_id       UUID PRIMARY KEY,
  idempotency_key  TEXT UNIQUE,
  amount           BIGINT,        -- store in cents, avoid floats!
  currency         TEXT,          -- ISO 4217 (USD, EUR, INR)
  status           TEXT,
  customer_id      UUID,
  card_token       TEXT,          -- opaque token, not actual card number
  created_at       TIMESTAMP,
  updated_at       TIMESTAMP
);

-- Ledger (immutable, append-only)
CREATE TABLE ledger_entries (
  entry_id      UUID PRIMARY KEY,
  payment_id    UUID,
  account_id    UUID,
  entry_type    TEXT,     -- DEBIT or CREDIT
  amount        BIGINT,
  currency      TEXT,
  created_at    TIMESTAMP
);
```

---

## 💬 Interview Q&A

### Q1: How do you guarantee exactly-once payment processing?
**Ans:** Idempotency keys. Client generates a UUID before attempting payment. Server stores the key with result after first successful processing. On any retry (network timeout, server crash), server checks if key exists → returns stored result without reprocessing. This makes retries safe. Key is stored for 24+ hours to cover retry window.

### Q2: Why store money amounts as integers (cents) not floats?
**Ans:** Floating point cannot represent many decimal values exactly (e.g., 0.1 + 0.2 = 0.30000000000000004 in IEEE 754). For money, this is catastrophic. Always store in smallest currency unit (cents for USD, paise for INR) as integers. $10.99 → store as 1099. Only convert to decimal for display.

### Q3: What is PCI-DSS and how does it affect architecture?
**Ans:** PCI-DSS (Payment Card Industry Data Security Standard): mandatory standard for systems that handle cardholder data. Requires: never store CVV, encrypt card numbers, limit access to card data, network segmentation, audit logs. In practice: use tokenization — send card to a certified vault (Stripe, Braintree), receive a token. Your servers only touch tokens, never raw card numbers.

### Q4: How does a payment flow from customer to merchant?
**Ans:** Customer submits card → (1) Your server tokenizes card, (2) Fraud check (< 100ms), (3) Authorization request to card network (Visa/Mastercard) via acquiring bank — freezes funds on customer account, (4) Capture — actually moves funds (same day or next), (5) Settlement — funds transferred to merchant's account (usually T+1 or T+2 days), (6) Reconciliation job verifies all steps matched.

---

## 🗒️ Quick Cheat Sheet
- **Authorization**: Reserve funds on customer's card (doesn't move money yet)
- **Capture**: Actually charge the reserved amount
- **Settlement**: Money moves from card network to merchant's bank (1-2 days)
- **Chargeback**: Customer disputes charge — merchant must provide evidence or loses money
- **3DS (3D Secure)**: Additional authentication step (OTP/biometric) for risky transactions
- **ACH**: US bank transfer standard. Cheaper than cards, 1-3 day settlement.

---

## 📓 Your Notes — Self Assessment
Rate yourself: 🔴 Missed key components | 🟡 Partial design | 🟢 Complete design

*Key focus: Did you handle idempotency, double-entry bookkeeping, and fraud detection?*
