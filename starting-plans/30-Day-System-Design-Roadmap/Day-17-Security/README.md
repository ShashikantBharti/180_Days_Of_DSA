# Day 17 — Security in System Design
## Topic: Building Systems That Are Safe by Design

---

## 🎯 What to Learn Today

- Authentication (who are you?) vs Authorization (what can you do?)
- JWT: structure, signing, validation, refresh tokens
- OAuth 2.0 flow (Authorization Code, Client Credentials)
- Encryption: in-transit (TLS) and at-rest (AES-256)
- Common vulnerabilities: SQLi, XSS, CSRF, DDoS
- Security best practices: HTTPS everywhere, secrets management, OWASP Top 10

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| OAuth 2.0 Explained Simply | https://www.youtube.com/watch?v=CPbvxxslDTU | 15 min |
| JWT.io (interactive) | https://jwt.io/ | 10 min |
| OWASP Top 10 | https://owasp.org/www-project-top-ten/ | 20 min |
| TLS Handshake Explained | https://www.cloudflare.com/learning/ssl/what-happens-in-a-tls-handshake/ | 15 min |

---

## 🧠 Key Concepts

- **JWT (JSON Web Token)**: Base64(header) + Base64(payload) + Signature. Stateless auth token. Verify with secret key.
- **OAuth 2.0**: Authorization framework — allows third-party apps to access user resources without sharing password.
- **RBAC (Role-Based Access Control)**: Permissions assigned to roles (admin, editor, viewer), users assigned to roles.
- **ABAC (Attribute-Based Access Control)**: Access based on attributes (user.department = "finance" AND resource.classification = "financial")
- **TLS (Transport Layer Security)**: Encrypts data in transit. Prevent MITM attacks.
- **SQL Injection**: Malicious SQL in input fields. Prevention: parameterized queries.
- **XSS (Cross-Site Scripting)**: Injecting JavaScript into pages. Prevention: escape output, CSP headers.
- **CSRF (Cross-Site Request Forgery)**: Tricks user into making unintended requests. Prevention: CSRF tokens, SameSite cookie.

---

## 🔢 JWT Structure

```
Header:    { "alg": "HS256", "typ": "JWT" }
Payload:   { "user_id": 123, "role": "admin", "exp": 1721000000 }
Signature: HMACSHA256(base64(header) + "." + base64(payload), secret)

Full token: eyJhbGc... . eyJ1c2Vy... . signature

Verify:
  1. Check signature (token not tampered)
  2. Check exp (not expired)
  3. Check iss/aud if needed
```

---

## 🧩 Problem Statement to Solve

> **Design a Secure Authentication System with SSO**
> - Single Sign-On across multiple products (Gmail, Drive, YouTube all use one Google login)
> - 50 million users; support email/password + Google/GitHub OAuth
> - MFA support: TOTP (Google Authenticator) and SMS OTP
> - Detect suspicious logins: new device, unusual country, bulk login attempts
> - Session management: access token (15 min) + refresh token (30 days)

**Security Design Task:**
1. OAuth 2.0 flow: user clicks "Login with Google" → what happens step by step?
2. Why should access tokens be short-lived (15 min) and refresh tokens long-lived (30 days)?
3. Where do you store tokens: localStorage, sessionStorage, or httpOnly cookie? Why?
4. How does TOTP (Time-based OTP) work? What is the secret stored on server?
5. How do you detect a login from a new device in a different country?

**Architecture Questions to Answer:**
- How does SSO work across multiple domains?
- How do you revoke a compromised refresh token?
- How do you protect passwords at rest? (bcrypt, never plain SHA-1!)

---

## 💬 Interview Q&A

### Q1: What is the difference between authentication and authorization?
**Ans:** Authentication = verifying identity ("are you who you claim to be?" — login). Authorization = verifying permissions ("are you allowed to do this?" — RBAC check). Authentication happens first; authorization follows. You can be authenticated but not authorized for a specific resource.

### Q2: What are the risks of storing JWT in localStorage vs httpOnly cookies?
**Ans:** localStorage is accessible to JavaScript — vulnerable to XSS attacks (malicious script can steal the token). httpOnly cookies cannot be accessed by JavaScript — immune to XSS. But cookies are vulnerable to CSRF unless protected with SameSite=Strict or CSRF tokens. Best practice: httpOnly cookies + CSRF token.

### Q3: How does OAuth 2.0 Authorization Code flow work?
**Ans:** (1) User clicks "Login with Google", (2) App redirects to Google's authorization endpoint, (3) User consents on Google's page, (4) Google redirects back to app with authorization code, (5) App exchanges code for access+refresh tokens via server-to-server call (code never exposed to browser), (6) App uses access token to call Google APIs.

### Q4: How do you prevent SQL injection?
**Ans:** Use parameterized queries (prepared statements) — never concatenate user input into SQL strings. Example: `SELECT * FROM users WHERE email = ?` with `[user_input]` as parameter. The DB treats the parameter as data, never as SQL code. ORMs like Hibernate/SQLAlchemy use parameterized queries by default.

---

## 🗒️ Quick Cheat Sheet
- **bcrypt**: Password hashing with built-in salt + work factor. Use for passwords.
- **AES-256-GCM**: Symmetric encryption for data at rest
- **TLS 1.3**: Current standard. TLS 1.0/1.1 deprecated.
- **CORS**: Browser security — only allow cross-origin requests from trusted domains
- **Secrets management**: Use Vault, AWS Secrets Manager — NEVER hardcode secrets in code
- **Rate limit login**: Max 5 failed attempts → lock account for 15 minutes

---

## 📓 Your Notes
*Draw the OAuth 2.0 flow for Google login. Design the token refresh architecture.*
