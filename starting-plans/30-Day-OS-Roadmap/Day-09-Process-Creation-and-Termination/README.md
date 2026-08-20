# Day 09 Process Creation and Termination
## Topic: Fork, Exec, Wait and Zombie Processes

---

## 🧠 Key Concepts
- fork() mechanism
- exec() family
- wait() and exit()
- Zombie Process
- Orphan Process

---

## 💬 Interview Q&A
### Q1: What does fork() return?
**Ans:** Returns 0 to the child process and the PID of the child to the parent process.

### Q2: What is a Zombie Process?
**Ans:** A process that has completed execution but still has an entry in the process table because the parent hasn't read its exit status.

### Q3: What is an Orphan Process?
**Ans:** A process whose parent has terminated, but the child is still running. It is usually adopted by the 'init' process (PID 1).


---

## 🗒️ Quick Cheat Sheet
- **Important Term 1**: Definition or tip.
- **Important Term 2**: Definition or tip.

---

## 📓 Your Notes
*Add your observations, additional questions, or diagrams here.*
