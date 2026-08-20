# Day 17 Semaphores and Mutex
## Topic: Synchronization Tools: Mutex vs Semaphores

---

## 🧠 Key Concepts
- Binary Semaphore (Mutex)
- Counting Semaphore
- Wait (P) and Signal (V) operations
- Binary vs Counting Comparison

---

## 💬 Interview Q&A
### Q1: Mutex vs Binary Semaphore?
**Ans:** A Mutex has an ownership concept (the thread that locks it must unlock it). A binary semaphore can be signaled/cleared by any thread.

### Q2: What are P and V operations?
**Ans:** P (Wait) decrements the semaphore; if negative, the process blocks. V (Signal) increments it; if there were blocked processes, one is woken up.


---

## 🗒️ Quick Cheat Sheet
- **Important Term 1**: Definition or tip.
- **Important Term 2**: Definition or tip.

---

## 📓 Your Notes
*Add your observations, additional questions, or diagrams here.*
