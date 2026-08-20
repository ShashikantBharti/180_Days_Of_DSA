# Day 06 — Content Delivery Networks (CDN)
## Topic: Serving Content Globally at Lightning Speed

---

## 🎯 What to Learn Today

- What is a CDN and how it works (edge nodes, PoPs)
- Push CDN vs Pull CDN — differences and use cases
- Cache-Control headers and CDN caching rules
- CDN for static vs dynamic content
- Cache invalidation across CDN nodes
- Real-world CDNs: Cloudflare, AWS CloudFront, Akamai, Fastly

---

## 📚 Resources

| Resource | Link | Time |
|---|---|---|
| How CDNs Work — ByteByteGo | https://www.youtube.com/@ByteByteGo | 20 min |
| Cloudflare Learning Center | https://www.cloudflare.com/learning/cdn/what-is-a-cdn/ | 20 min |
| AWS CloudFront Docs | https://docs.aws.amazon.com/cloudfront/ | 15 min |
| CDN Cache Invalidation | https://www.fastly.com/blog/cache-invalidation-strategies | 15 min |

---

## 🧠 Key Concepts

- **CDN (Content Delivery Network)**: Globally distributed servers (edge nodes) that cache content close to users
- **PoP (Point of Presence)**: A CDN edge location (e.g., Mumbai, London, New York)
- **Pull CDN**: Content is pulled from origin on first request, cached at edge. Less storage, possible slow first request.
- **Push CDN**: You proactively upload content to CDN before users request it. Fast first request, more management.
- **Cache-Control: max-age=3600**: Browser/CDN caches response for 1 hour
- **CDN Invalidation**: Explicitly tell CDN to purge/refresh cached content before TTL expires
- **Edge Caching**: User gets response from nearest PoP instead of origin server

---

## 🔢 CDN Use Cases

```
Static Content (Always cache):
  - Images, videos, CSS, JavaScript files
  - Fonts, icons, PDFs
  - Use long TTL (days or weeks)

Dynamic Content (Selective caching):
  - API responses that change infrequently
  - Personalized content → DON'T cache (or use Vary header)
  - Authenticated content → DON'T cache at CDN

Video Streaming:
  - Cache video chunks (HLS segments) at edge
  - Cache popular videos longer, rare videos shorter
```

---

## 🧩 Problem Statement to Solve

> **Design a Video Streaming Platform (like YouTube)**
> - 500 million users, 1 billion video views per day
> - Users upload videos → transcode to 360p, 720p, 1080p, 4K
> - Playback must start in < 2 seconds globally
> - Support adaptive bitrate streaming (auto-switch quality based on internet speed)
> - Support live streaming events

**CDN Design Task:**
1. What content gets cached at CDN edges? (video segments, thumbnails)
2. How are video chunks split for CDN caching? (HLS = .m3u8 playlist + .ts segments)
3. When a video goes viral, how does the CDN absorb the traffic spike?
4. How do you invalidate a video thumbnail that was updated?

**Architecture Questions to Answer:**
- How does adaptive bitrate streaming (ABR) work with CDN?
- Where does video transcoding happen? (before or after CDN?)
- For live streaming, how does CDN handle content that changes every 2 seconds?

---

## 💬 Interview Q&A

### Q1: What is the difference between Push and Pull CDN?
**Ans:** Pull CDN: content is fetched from origin only when first user requests it — CDN auto-caches on first miss. Simple to manage, storage efficient. Pull is default for most use cases. Push CDN: you upload content to CDN proactively — great for content you know will be popular (product launch, sports event).

### Q2: How does CDN reduce origin server load?
**Ans:** Once a resource is cached at a CDN edge node, all subsequent requests for that resource from nearby users are served directly from the edge — never touching your origin server. A 99% cache hit ratio means only 1% of requests reach origin.

### Q3: How do you handle cache invalidation across CDN?
**Ans:** (1) TTL expiry — wait for TTL to expire (simple but slow), (2) Versioned URLs — `style.v2.css` instead of `style.css` — never need to invalidate (best practice), (3) Explicit purge API — call CDN API to immediately remove specific URLs from all edges (costs money and takes ~10 seconds to propagate).

### Q4: Can you cache API responses at CDN?
**Ans:** Yes, but carefully. Cache only public, non-personalized responses (e.g., trending videos list). Use short TTL (30-60 seconds). Never cache authenticated or user-specific responses. Use `Cache-Control: public, max-age=60` header.

---

## 🗒️ Quick Cheat Sheet
- **Cache-Control: no-store** = Never cache (private user data)
- **Cache-Control: public, max-age=86400** = Cache for 1 day everywhere
- **ETag**: Hash of content — browser revalidates without downloading if unchanged
- **HLS**: HTTP Live Streaming — video split into 2-10 second .ts chunks
- **Anycast**: CDN routes users to nearest PoP automatically via routing protocol

---

## 📓 Your Notes
*Draw the video streaming architecture. Show where CDN sits between origin and user.*
