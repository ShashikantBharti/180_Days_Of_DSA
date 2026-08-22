# Complete Java Syllabus — From Scratch to Advanced

## Goal

This syllabus is designed to take a learner from **absolute Java beginner** to a strong **Core Java / Backend-ready Software Engineer** foundation.

It is especially suitable for preparation toward:

- Java Developer
- Java Backend Developer
- Software Engineer / SDE
- Spring Boot Developer
- FAANG / MAANG-style interviews

---

# 0. Prerequisites and Learning Strategy

## 0.1 Prerequisites

No prior Java knowledge is required.

Helpful but optional:

- Basic computer knowledge
- Basic programming logic
- Basic mathematics
- Familiarity with Git and terminal

## 0.2 Recommended Java Version

Use a current LTS JDK, preferably:

- Java 21 LTS
- Java 25 LTS

For learning, use one version consistently and understand which features belong to which Java release.

## 0.3 Development Environment

Learn to use:

- JDK
- IntelliJ IDEA
- VS Code optional
- Terminal
- JShell
- Git
- Maven
- Gradle basics

## 0.4 Reference Playlist Integration

This syllabus also incorporates the major topic areas covered by the **Engineering Digest — Complete Java Programming Course 2025** playlist supplied for this roadmap. The playlist is listed publicly as a Java course and is described as covering core Java syntax/control flow/methods, OOP, Collections Framework, Generics, Exception Handling, File Handling, and related topics. citeturn1search0turn1search2

Reference playlist:

- https://www.youtube.com/watch?v=oveyab6lO_E&list=PLA3GkZPtsafY62QhQ030p85HAer0pFDdr

### How the playlist is used in this syllabus

The playlist topics are **not added as a separate duplicate course**. They are mapped into the appropriate phases below so that the syllabus remains a single learning path.

Key playlist-aligned areas include:

- Java introduction and environment
- Java syntax
- Variables and data types
- Operators
- Input/output basics
- Conditional statements
- Loops
- Methods
- Arrays
- Strings
- Object-oriented programming
- Constructors
- Inheritance
- Polymorphism
- Abstraction
- Interfaces
- Packages and access modifiers
- Collections Framework
- Generics
- Exception handling
- File handling
- Modern Java topics

The additional advanced sections in this syllabus go beyond the playlist to prepare you for Java backend development and FAANG/MAANG-style software engineering interviews.

## 0.4 Learning Method

For every topic:

1. Understand the concept.
2. Understand why it exists.
3. Write small examples.
4. Solve coding problems.
5. Debug incorrect programs.
6. Build a small feature.
7. Explain the concept without notes.
8. Revisit it during interview preparation.

---

# Phase 1 — Java Introduction and Environment

## 1.1 Java Overview

Learn:

- What is Java?
- History of Java
- Features of Java
- Java editions
- Java use cases
- Java ecosystem
- Java applications
- Java vs C/C++/Python/JavaScript

## 1.2 JDK, JRE and JVM

Understand:

- JDK
- JRE
- JVM
- JDK vs JRE
- JRE vs JVM
- JVM vs operating system

## 1.3 Java Architecture

Learn:

- Source code
- Compiler
- Bytecode
- Class files
- JVM
- Interpreter
- JIT compiler
- Machine code

### Execution Pipeline

```text
.java
  |
  | javac
  v
.class / Bytecode
  |
  | JVM
  v
Machine Code
  |
  v
CPU
```

## 1.4 JVM Overview

Learn:

- Class Loader
- Runtime Data Areas
- Execution Engine
- JIT
- Garbage Collector
- JNI
- Native libraries

## 1.5 Java Program Structure and Syntax

Learn:

- Class declaration
- main method
- Statements and expressions
- Semicolons
- Blocks `{}`
- Comments
  - Single-line comments
  - Multi-line comments
  - Documentation comments / Javadoc
- Identifiers
- Keywords
- Literals
- Escape sequences
- Command-line arguments

## 1.6 Console Input and Output

Learn:

- `System.out.print()`
- `System.out.println()`
- `System.out.printf()`
- `System.err`
- `Scanner`
- Reading integers
- Reading floating-point values
- Reading strings
- `next()` vs `nextLine()`
- Common Scanner input pitfalls

## 1.7 Installation

Learn:

- Install JDK
- Configure JAVA_HOME
- Configure PATH
- Verify `java -version`
- Verify `javac -version`
- Use JShell
- Configure IntelliJ IDEA

## 1.8 First Programs

Practice:

- Hello World
- Print personal information
- Add two numbers
- Simple calculator
- Temperature converter
- Currency converter

---

# Phase 2 — Variables, Data Types and Operators

## 2.1 Variables

Learn:

- Variable declaration
- Initialization
- Assignment
- Naming conventions
- Scope
- Lifetime

## 2.2 Primitive Data Types

Learn deeply:

- byte
- short
- int
- long
- float
- double
- char
- boolean

Understand:

- Size
- Range
- Default values
- Memory representation

## 2.3 Reference Types

Learn:

- Objects
- Arrays
- Strings
- Classes
- Interfaces
- References
- null

## 2.4 Java Literals and Initialization

Learn:

- Integer literals
- Floating-point literals
- Character literals
- String literals
- Boolean literals
- `null`
- Underscores in numeric literals
- Numeric suffixes such as `L`, `F`, and `D`

## 2.4 Constants

Learn:

- `final`
- Constants
- Naming conventions

## 2.5 Type Conversion

Learn:

- Widening conversion
- Narrowing conversion
- Explicit casting
- Implicit conversion
- Numeric promotion

## 2.6 Operators

Learn:

- Arithmetic
- Assignment
- Relational
- Logical
- Unary
- Ternary
- Bitwise
- Shift
- Compound assignment

## 2.7 Wrapper Classes

Learn:

- Integer
- Long
- Double
- Float
- Short
- Byte
- Character
- Boolean

Understand:

- Autoboxing
- Unboxing
- Parsing
- Wrapper caching basics

## Practice

Build:

- Area calculator
- Simple interest calculator
- Compound interest calculator
- Salary calculator
- BMI calculator
- Electricity bill calculator
- Number swap
- Unit converter

---

# Phase 3 — Control Flow

## 3.1 Conditional Statements

Learn:

- if
- if-else
- else-if
- nested if
- switch

## 3.2 Modern Switch

Learn:

- Arrow syntax
- Switch expressions
- `yield`

## 3.3 Loops

Learn:

- for
- while
- do-while
- enhanced for loop

## 3.4 Control Statements

Learn:

- break
- continue
- return

## 3.5 Nested Loops

Practice:

- Number patterns
- Star patterns
- Matrix traversal

## Coding Practice

Implement:

- Even/odd
- Positive/negative
- Largest of three numbers
- Leap year
- Prime number
- Prime numbers in a range
- Factorial
- Fibonacci
- Palindrome number
- Armstrong number
- Reverse number
- Sum of digits
- Count digits
- GCD
- LCM
- Perfect number
- Strong number
- Multiplication table

---

# Phase 4 — Methods

## 4.1 Methods

Learn:

- Method declaration
- Return types
- Parameters
- Arguments
- Method invocation
- Void methods
- Returning values

## 4.2 Method Parameters

Learn:

- Primitive parameters
- Reference parameters
- Multiple parameters
- Varargs

## 4.3 Method Overloading

Understand:

- Compile-time polymorphism
- Overloading rules
- Return type and overloading
- Primitive widening
- Varargs and overload resolution

## 4.4 Static Methods

Learn:

- Static methods
- Static variables
- Static blocks
- Static members

## 4.5 Java Pass-by-Value

Understand deeply:

- Primitive value passing
- Object reference value passing
- Why Java is pass-by-value
- Mutation vs reassignment

## Practice

Create utility methods for:

- Math operations
- String operations
- Array operations
- Validation
- Conversion

---

# Phase 5 — Arrays

## 5.1 One-Dimensional Arrays

Learn:

- Declaration
- Initialization
- Traversal
- Indexing
- Length
- Default values

## 5.2 Array Operations

Practice:

- Find maximum
- Find minimum
- Sum
- Average
- Search
- Reverse
- Copy
- Merge
- Sort
- Rotate
- Remove duplicates
- Find second largest
- Frequency counting

## 5.3 Two-Dimensional Arrays

Learn:

- Matrix creation
- Row traversal
- Column traversal
- Matrix operations

Practice:

- Matrix addition
- Matrix multiplication
- Transpose
- Diagonal sum
- Spiral traversal

## 5.4 Arrays Utility Class

Learn:

- sort
- binarySearch
- copyOf
- copyOfRange
- equals
- fill
- toString
- deepToString

---

# Phase 6 — Strings

## 6.1 String Fundamentals

Learn:

- String creation
- String literals
- String objects
- String immutability
- String pool
- Heap vs pool

## 6.2 String Methods

Learn:

- length
- charAt
- substring
- indexOf
- lastIndexOf
- contains
- startsWith
- endsWith
- equals
- equalsIgnoreCase
- compareTo
- replace
- split
- trim
- strip
- join

## 6.3 StringBuilder

Learn:

- append
- insert
- delete
- reverse
- replace
- capacity

## 6.4 StringBuffer

Understand:

- StringBuilder vs StringBuffer
- Thread safety
- Performance

## 6.5 String Interview Concepts

Understand:

- Why String is immutable
- Why String is final
- String pool
- `==` vs `equals()`
- String concatenation
- Compile-time constants

## Practice

Implement:

- Reverse string
- Palindrome string
- Anagram
- Character frequency
- First non-repeating character
- Remove duplicate characters
- Count words
- Count vowels/consonants
- String compression
- Longest word
- Reverse words

---

# Phase 7 — Object-Oriented Programming

This is one of the most important Java phases.

## 7.1 Classes and Objects

Learn:

- Class
- Object
- Fields
- Methods
- Constructors
- Object creation
- Object references

## 7.2 Constructors

Learn:

- Default constructor
- Parameterized constructor
- Constructor overloading
- Constructor chaining
- `this()`

## 7.3 Encapsulation

Learn:

- private
- public
- protected
- default
- Getters
- Setters
- Immutable objects

## 7.4 Inheritance

Learn:

- `extends`
- Parent class
- Child class
- `super`
- Constructor chaining
- Method inheritance

## 7.5 Polymorphism

Learn:

- Compile-time polymorphism
- Runtime polymorphism
- Method overloading
- Method overriding
- Dynamic method dispatch
- Upcasting
- Downcasting

## 7.6 Abstraction

Learn:

- Abstract classes
- Abstract methods
- Interfaces
- Default methods
- Static interface methods
- Private interface methods

## 7.7 Composition

Understand:

- Is-a relationship
- Has-a relationship
- Composition
- Aggregation
- Association
- Composition vs inheritance

## 7.8 Important Keywords

Master:

- this
- super
- static
- final
- abstract
- extends
- implements
- instanceof

## 7.9 Object Class

Learn:

- toString()
- equals()
- hashCode()
- getClass()
- clone() concepts

## 7.10 equals() and hashCode()

Understand deeply:

- Object identity
- Logical equality
- Equality contract
- hashCode contract
- Why both must be overridden together
- Collections impact

## OOP Project

### Library Management System

Entities:

- Library
- Book
- Author
- Member
- Librarian
- BorrowRecord

Features:

- Add book
- Remove book
- Search book
- Register member
- Borrow book
- Return book
- Display books
- Track availability

---

# Phase 8 — Packages and Access Control

## 8.1 Packages

Learn:

- Package declaration
- Imports
- Static imports
- Package organization

## 8.2 Access Modifiers

Understand:

- public
- protected
- package-private
- private

Understand access from:

- Same class
- Same package
- Subclass
- Different package

## 8.3 Java Project Structure

Learn how to organize:

```text
src/
  main/
    java/
      com.example/
        model/
        service/
        repository/
        util/
```

---

# Phase 9 — Interfaces and Advanced OOP

Learn:

- Interface design
- Multiple interface implementation
- Interface inheritance
- Default methods
- Static interface methods
- Functional interfaces
- Abstract class vs interface
- Composition over inheritance
- Dependency inversion
- Coupling
- Cohesion

## Design Principles

Learn:

- SOLID
- DRY
- KISS
- YAGNI
- Favor composition

---

# Phase 10 — Enums

Learn:

- Basic enums
- Enum fields
- Enum constructors
- Enum methods
- EnumSet
- EnumMap
- Switch with enums

Practice:

- Order status
- Payment status
- User role
- Product category

---

# Phase 11 — Records

Learn:

- Record syntax
- Canonical constructor
- Compact constructor
- Accessor methods
- equals/hashCode
- toString
- Record limitations
- Records vs normal classes
- Records for DTO-style data

---

# Phase 12 — Nested and Inner Classes

Learn:

- Static nested class
- Inner class
- Local class
- Anonymous class

Understand:

- Outer class access
- Enclosing instance
- Use cases

---

# Phase 13 — Exception Handling

## 13.1 Exception Hierarchy

Learn:

- Throwable
- Error
- Exception
- RuntimeException

## 13.2 Checked vs Unchecked

Understand:

- Checked exceptions
- Unchecked exceptions
- When to use each

## 13.3 Exception Syntax

Learn:

- try
- catch
- finally
- throw
- throws

## 13.4 Advanced Exception Handling

Learn:

- Multiple catch
- Multi-catch
- Nested try
- Try-with-resources
- Suppressed exceptions
- Custom exceptions

## Best Practices

Understand:

- Don't swallow exceptions
- Don't catch overly broad exceptions unnecessarily
- Preserve useful context
- Use domain-specific exceptions
- Don't use exceptions for normal control flow

## Practice

Build:

- Banking exceptions
- Invalid user input exception
- Insufficient balance exception
- Book unavailable exception
- Custom validation framework

---

# Phase 14 — Java I/O and NIO

## 14.1 Classic I/O

Learn:

- File
- InputStream
- OutputStream
- Reader
- Writer
- BufferedReader
- BufferedWriter

## 14.2 NIO

Learn:

- Path
- Paths
- Files
- Channels
- Buffers

## 14.3 File Operations

Practice:

- Create file
- Read file
- Write file
- Append file
- Copy file
- Move file
- Delete file
- Search files
- Read directory
- Process CSV

## Project

### File-based Expense Tracker

Store:

- Expenses
- Categories
- Dates
- Amounts

Use file persistence before moving to databases.

---

# Phase 15 — Date and Time API

Learn:

- LocalDate
- LocalTime
- LocalDateTime
- Instant
- ZonedDateTime
- OffsetDateTime
- Duration
- Period
- ZoneId
- DateTimeFormatter

Practice:

- Age calculator
- Date difference
- Appointment scheduler
- Time-zone converter
- Subscription expiry calculator

Understand legacy APIs:

- Date
- Calendar

Know how to work with legacy code without making it your default approach.

---

# Phase 16 — Generics

## 16.1 Generic Classes

Learn:

```java
class Box<T> {
    private T value;
}
```

## 16.2 Generic Methods

Learn:

```java
<T> T process(T value)
```

## 16.3 Bounded Types

Learn:

- `<T extends Number>`
- Multiple bounds

## 16.4 Wildcards

Learn:

- `?`
- `? extends`
- `? super`

## 16.5 PECS

Master:

> Producer Extends, Consumer Super.

## Practice

Build:

- Generic repository
- Generic stack
- Generic queue
- Generic pair
- Generic response wrapper

---

# Phase 17 — Collections Framework

This is a major interview topic.

## 17.1 Collection Hierarchy

Understand the core interface hierarchy:

```text
                  Iterable
                     |
                 Collection
                     |
      +--------------+--------------+
      |              |              |
    List            Set           Queue
                                    |
                                  Deque
```

And separately, representing key-value pairs:

```text
    Map
```

Understand:
- **Iterable**: Root interface representing elements that can be iterated using a for-each loop. Contains `iterator()`, `forEach()`, and `spliterator()`.
- **Collection**: Extends `Iterable`. The foundation interface defining standard operations like `add()`, `remove()`, `addAll()`, `removeAll()`, `retainAll()`, `clear()`, `size()`, `contains()`, `containsAll()`, `isEmpty()`, and `toArray()`.

## 17.2 List

Learn list implementations that store ordered elements allowing duplicates:

- **ArrayList**: Resizable array implementation.
  - Internal mechanics: Starts with a default capacity (typically 10). Resizes by allocating a new array (usually 1.5x of the old capacity) and copying elements.
  - Performance: O(1) random access by index, O(1) amortized insertion at the end, O(N) insertion/removal at arbitrary positions (due to element shifting).
- **LinkedList**: Doubly-linked list implementation.
  - Internal mechanics: Each element is a Node containing data, a pointer to the next node, and a pointer to the previous node.
  - Performance: O(1) head/tail manipulation, O(N) lookup by index, O(1) insertions/deletions once the node is located. High memory overhead per element.
- **Vector**: Legacy resizable array implementation.
  - Internal mechanics: Similar to `ArrayList` but synchronized (thread-safe) on every method. Grows by doubling (2x) when capacity is exceeded.
  - Performance: Poor performance due to synchronization overhead. Generally obsolete.
- **Stack**: Legacy subclass of `Vector` representing LIFO.
  - Internal mechanics: Synchronized stack operations (`push`, `pop`, `peek`).
  - Limitations: Extends `Vector` (violates composition over inheritance by exposing index-based operations), has lock overhead. Use `Deque` / `ArrayDeque` instead.

Understand:
- **List Interface Methods**: Master index-based operations (`get(index)`, `set(index, element)`, `add(index, element)`, `remove(index)`, `indexOf(element)`, `lastIndexOf(element)`, and `subList(fromIndex, toIndex)`).
- **ArrayList vs LinkedList**: Detailed comparison on time complexity, memory allocation, caching benefits (contiguous memory benefits of ArrayList), and mutation behavior.
- **Appropriate use cases**: Read-heavy operations (ArrayList) vs insert/delete heavy at boundaries (LinkedList).

## 17.3 Set

Learn set implementations that store unique elements (no duplicates):

- **HashSet**: Backed internally by a `HashMap` (where elements are stored as keys with a constant dummy value object).
  - Performance: O(1) average time complexity for `add`, `remove`, and `contains`. Does not guarantee any order of elements.
- **LinkedHashSet**: Extends `HashSet` but maintains a doubly-linked list running through its elements.
  - Performance: Slightly lower performance than `HashSet` due to maintaining links, but preserves **insertion order**.
- **TreeSet**: Backed internally by a `TreeMap` (self-balancing Red-Black Tree).
  - Performance: O(log N) for `add`, `remove`, and `contains`. Maintains elements in **sorted/natural order** or custom order defined by a `Comparator`.
- **Set Operations**:
  - **Union**: `setA.addAll(setB)`
  - **Intersection**: `setA.retainAll(setB)`
  - **Difference**: `setA.removeAll(setB)`
  - **Subset**: `setA.containsAll(setB)`

Understand:
- **Hashing**: How `hashCode()` and `equals()` determine uniqueness in hash-sets.
- **Ordering**: No order (HashSet) vs Insertion order (LinkedHashSet) vs Sorted order (TreeSet).
- **Uniqueness**: How duplicate insertion attempts are ignored (returns `false`).

## 17.4 Map

Learn map implementations storing key-value pairs (keys must be unique):

- **HashMap**: Uses hashing to map keys to values.
  - Performance: O(1) average time complexity for get and put. Does not guarantee order.
- **LinkedHashMap**: Extends `HashMap` with a doubly-linked list traversing all entries.
  - Performance: O(1) average complexity. Preserves **insertion order** or **access order** (useful for LRU caches).
- **TreeMap**: Red-Black Tree-based sorted map.
  - Performance: O(log N) operations. Keys are **sorted** naturally or via a custom `Comparator`.
- **Hashtable**: Legacy synchronized key-value store.
  - Limitations: Thread-safe but synchronized on the entire object (poor scalability). **Does not allow null keys or values**. Use `ConcurrentHashMap` instead.
- **WeakHashMap**: Map with keys wrapped in `WeakReference`.
  - Behavior: Garbage collector automatically reclaims entries when keys are no longer referenced elsewhere. Highly useful for metadata/caching.
- **IdentityHashMap**: Compares keys using reference-equality `==` instead of `equals()`.
  - Behavior: Useful for object graph traversal, serialization, or tracking visited objects.
- **EnumMap**: Specialized map implementation for enum keys.
  - Performance: Extremely fast, internally represented as a flat array.
- **ConcurrentHashMap**: High-performance thread-safe map.
  - Internal mechanics: Uses bucket-level locking / lock-striping and CAS (Compare-And-Swap) operations to allow concurrent reads and writes. Does not throw `ConcurrentModificationException` during iteration. Does not allow null keys or values.

## 17.5 Queue and Deque

Learn queue implementations for holding elements prior to processing:

- **Queue Interface**: Standard FIFO operations (`add`, `remove`, `element` throw exceptions; `offer`, `poll`, `peek` return special values).
- **PriorityQueue**: Unbounded priority queue based on a binary min-heap.
  - Performance: O(log N) for insertions (`offer`) and removals (`poll`). Elements sorted naturally or by custom `Comparator`.
- **Deque Interface**: Double-ended queue supporting element insertion/removal at both ends. Represents LIFO (Stack) and FIFO (Queue) behaviors.
- **ArrayDeque**: Resizable-array implementation of `Deque`.
  - Performance: O(1) amortized head/tail operations. Faster than `Stack` and `LinkedList` for queue/stack workloads. No null values allowed.
- **BlockingQueue**: Thread-safe queues that block when retrieving from empty queue or inserting into full queue.
  - **ArrayBlockingQueue**: Bounded, backed by an array.
  - **LinkedBlockingQueue**: Optionally bounded, backed by linked nodes.
  - **PriorityBlockingQueue**: Unbounded blocking priority queue.
  - **DelayQueue**: Elements can only be taken when their delay has expired.
  - **SynchronousQueue**: Zero-capacity queue where each insert must wait for a corresponding retrieve.

## 17.6 Iteration

Learn how to traverse collections:

- **For-Each Loop**: Syntactic sugar over the `Iterator` for clean traversal.
- **Iterator**: Allows basic forward traversal and safe element removal via `remove()` during iteration without throwing `ConcurrentModificationException`.
- **ListIterator**: Extends `Iterator` for lists. Allows bidirectional traversal (`hasPrevious()`, `previous()`), index queries, element modification (`set()`), and addition (`add()`).
- **Spliterator**: Parallel-capable iterator. Supports partition-based traversal for stream operations via `trySplit()`.

## 17.7A Collection Factory and Immutable Collections

Learn creation of unmodifiable collections:

- **Factory Methods**: `List.of()`, `Set.of()`, `Map.of()`, and `Map.ofEntries()` return immutable collections.
- **Copy Methods**: `List.copyOf()`, `Set.copyOf()`, and `Map.copyOf()` return unmodifiable copies of existing collections.
- **Immutable vs Unmodifiable**: Unmodifiable collections are read-only wrappers over backing mutable collections (changes to backing collection affect the wrapper). Immutable collections are completely self-contained and structurally unchangeable.

## 17.7 Utility Methods

Learn algorithms provided by the `java.util.Collections` class:

- **Sorting**: `Collections.sort(List)` and `Collections.sort(List, Comparator)`.
- **Searching**: `Collections.binarySearch(List, key)` (requires sorted list, O(log N)).
- **Reversal & Shuffling**: `Collections.reverse(List)`, `Collections.shuffle(List)`.
- **Replication & Fill**: `Collections.fill(List, obj)`, `Collections.copy(dest, src)`.
- **Properties**: `Collections.min(Collection)`, `Collections.max(Collection)`, `Collections.frequency(Collection, obj)`.
- **Modifications**: `Collections.swap(List, i, j)`, `Collections.rotate(List, distance)`, `Collections.replaceAll(List, oldVal, newVal)`.
- **Relationships**: `Collections.disjoint(coll1, coll2)` checks if two collections have no elements in common.
- **Thread safety**: `Collections.synchronizedList()`, `Collections.synchronizedMap()`, etc. (wrap collections in synchronized monitors).

## 17.8 HashMap Internals

Understand the inner workings of `HashMap` (a frequent interview topic):

- **Key Mechanics**:
  - Generates hash using key's `hashCode()` and applies internal spreading `(h ^ (h >>> 16))`.
  - Determines array bucket index using bitwise modulo: `index = (n - 1) & hash` (where `n` is bucket array length, always a power of 2).
- **Collision Handling**:
  - **Chaining**: Entries mapping to the same bucket index are stored in a singly-linked list.
  - **Treeification (JDK 8+)**: If a bucket's linked list length exceeds the threshold of 8 (`TREEIFY_THRESHOLD`) and the map's total capacity is at least 64 (`MIN_TREEIFY_CAPACITY`), the bucket converts into a self-balancing Red-Black Tree. This reduces lookup complexity from O(N) to O(log N).
  - **Untreeification**: If capacity/size shrinks, the tree converts back to a linked list once the bucket size drops to 6 (`UNTREEIFY_THRESHOLD`).
- **Load Factor and Resizing**:
  - **Initial Capacity**: Default is 16.
  - **Load Factor**: Default is 0.75.
  - **Threshold**: `Capacity * Load Factor`. When size exceeds threshold, capacity doubles, and all elements undergo **rehashing** to their new indexes.
- **Mutable Keys Problem**: If a key's properties change after insertion in a way that alters its `hashCode()`, the key maps to a different index and becomes unretrievable, creating a memory leak.

## 17.9 Comparable and Comparator

Learn sorting and comparison mechanisms:

- **Comparable Interface**: Implemented by classes to define **natural ordering** (`compareTo(T o)` method). Returns negative, zero, or positive integer.
- **Comparator Interface**: Exists externally to define **custom ordering** (`compare(T o1, T o2)` method).
- **Comparator Chaining**: Build complex comparators using `thenComparing()`, `reversed()`, and `nullsFirst()` / `nullsLast()`.

## Practice

Build:

- Word frequency counter
- Employee ranking
- Phone book
- Student management system
- Inventory manager
- Top-K frequency calculator
- LRU cache

# Phase 18 — Functional Interfaces

Learn:

- Functional interface concept
- @FunctionalInterface
- Predicate
- Function
- Consumer
- Supplier
- UnaryOperator
- BinaryOperator
- BiPredicate
- BiFunction
- BiConsumer

Practice creating custom functional interfaces.

---

# Phase 19 — Lambda Expressions

Learn:

- Lambda syntax
- Parameters
- Return values
- Type inference
- Functional interfaces
- Variable capture
- Effectively final variables

Practice converting anonymous classes to lambdas.

---

# Phase 20 — Method References

Learn:

- Static method reference
- Instance method reference
- Bound instance reference
- Constructor reference

Examples:

```text
ClassName::staticMethod
object::instanceMethod
ClassName::instanceMethod
ClassName::new
```

---

# Phase 21 — Stream API

One of the most important modern Java topics.

## 21.1 Stream Fundamentals

Learn:

- Stream creation
- Intermediate operations
- Terminal operations
- Lazy evaluation
- Pipeline

## 21.2 Intermediate Operations

Learn:

- filter
- map
- flatMap
- distinct
- sorted
- peek
- limit
- skip

## 21.3 Terminal Operations

Learn:

- forEach
- collect
- reduce
- count
- min
- max
- findFirst
- findAny
- anyMatch
- allMatch
- noneMatch

## 21.4 Collectors

Learn:

- toList
- toSet
- toMap
- joining
- counting
- groupingBy
- partitioningBy
- mapping
- reducing
- summarizing

## 21.5 Primitive Streams

Learn:

- IntStream
- LongStream
- DoubleStream

## Practice

Use Employee data to solve:

- Highest salary
- Average salary
- Employees by department
- Group by role
- Count employees
- Find duplicates
- Sort by multiple fields
- Partition by salary
- Convert list to map
- Flatten nested collections

---

# Phase 22 — Optional

Learn:

- Optional creation
- of
- ofNullable
- empty
- isPresent
- ifPresent
- map
- flatMap
- filter
- orElse
- orElseGet
- orElseThrow

Understand:

- Why Optional exists
- Optional vs null
- Optional in return types
- When not to use Optional
- Optional performance/readability considerations

---

# Phase 23 — Multithreading Fundamentals

This phase takes Java from intermediate to advanced.

## 23.1 Thread Basics

Learn the core primitives of concurrent execution:

- **Multitasking vs Multithreading**:
  - **Process-based Multitasking**: Running multiple programs concurrently (e.g., web browser and IDE). Each process has its own address space.
  - **Thread-based Multitasking (Multithreading)**: Running multiple parts of the same program concurrently. Threads share the process's memory space.
- **Process vs Thread**: Processes are heavyweight, have independent memory space, and take longer to create/terminate. Threads are lightweight, share the heap/metaspace of their parent process but possess their own stack, program counter (PC), and local variables.
- **Thread Creation Models**:
  - **Extending Thread**: Override the `run()` method. Simple, but prevents extending other classes.
  - **Implementing Runnable**: Pass a `Runnable` implementation to a `Thread` constructor. Separates task logic from thread execution (preferred).
  - **Implementing Callable**: Part of `java.util.concurrent`. Allows the task to return a value (via `Future`) and throw checked exceptions.
- **Thread Execution Types**:
  - **User Threads**: High-priority threads that keep the JVM alive. The JVM will not terminate until all user threads have finished.
  - **Daemon Threads**: Low-priority background threads providing services (e.g., Garbage Collection). The JVM will terminate immediately when only daemon threads are running. Set using `thread.setDaemon(true)` before starting.

## 23.2 Thread Lifecycle

Understand the 6 thread states defined in `Thread.State`:

- **NEW**: Thread object is instantiated, but `start()` has not been called.
- **RUNNABLE**: Thread is executing in the JVM or is ready to run, waiting for CPU resource allocation from the operating system scheduler.
- **BLOCKED**: Thread is waiting to acquire an intrinsic monitor lock to enter a synchronized block or method.
- **WAITING**: Thread is waiting indefinitely for another thread to perform a specific action (e.g., `object.wait()`, `thread.join()`, `LockSupport.park()`).
- **TIMED_WAITING**: Thread is waiting for another thread to perform an action for up to a specified waiting time (e.g., `Thread.sleep(ms)`, `object.wait(ms)`, `thread.join(ms)`).
- **TERMINATED**: Thread has completed execution (run method finished or uncaught exception occurred).

## 23.3 Thread Methods

Learn the API methods of the `Thread` class:

- **start()**: Allocates system resources, creates a new call stack, and moves the thread to the `RUNNABLE` state, triggering `run()`.
- **run()**: Contains the execution code. Calling `run()` directly executes code in the current thread instead of starting a new one.
- **sleep(long millis)**: Static method that pauses the current thread's execution for the specified duration without releasing acquired monitor locks. Moves thread to `TIMED_WAITING` state.
- **join()**: Blocks the calling thread until the target thread terminates. Moves the calling thread to `WAITING` or `TIMED_WAITING` state.
- **yield()**: Static hint to the scheduler that the current thread is willing to yield its current use of a processor. The scheduler is free to ignore this hint.
- **interrupt()**: Interrupts the target thread. Sets the interrupted status flag. If the thread is in a blocking state (`sleep`, `wait`, `join`), it clears the flag and throws `InterruptedException`.
- **currentThread()**: Static method returning a reference to the currently executing thread object.
- **isAlive()**: Checks if the thread has been started and has not yet terminated.
- **Priority**: Threads have priorities ranging from 1 (`Thread.MIN_PRIORITY`) to 10 (`Thread.MAX_PRIORITY`), default is 5 (`Thread.NORM_PRIORITY`). Used by OS schedulers, though not guaranteed across platform implementations.

# Phase 24 — Synchronization and Thread Safety

Learn mechanisms to ensure thread safety and prevent resource corruption:

- **Concurrency Problems**:
  - **Race Condition**: Occurs when multiple threads access and mutate shared data concurrently, and the final outcome depends on the execution timing/interleaving.
  - **Critical Section**: A block of code that accesses shared resources and must not be concurrently executed by multiple threads.
- **Three Pillars of Concurrency**:
  - **Atomicity**: Operations must complete as a single, indivisible unit (e.g., `count++` is NOT atomic; it comprises read, modify, and write steps).
  - **Visibility**: Changes made by one thread to shared data must be immediately visible to other threads.
  - **Ordering**: The compiler or CPU can reorder instructions for optimization unless prevented.
- **Intrinsic Synchronization**:
  - **synchronized Method**: Locks the instance (`this`) for instance methods, or the Class object for static methods.
  - **synchronized Block**: Locks a specified object monitor. Offers finer granularity than synchronized methods.
  - **Monitor Lock (Intrinsic Lock)**: Every object has an associated monitor. A thread must acquire the monitor lock before entering the synchronized block.
  - **Static Synchronization**: Synchronizing on static methods or blocks locking the class metadata, preventing concurrent access across all instances.
- **Thread Communication**:
  - **wait()**: Releases the acquired monitor lock and causes the current thread to wait until another thread invokes `notify()` or `notifyAll()` on the same object. Must be called from a synchronized context.
  - **notify() / notifyAll()**: Wakes up a single thread (or all threads) waiting on the object's monitor. Must be called from a synchronized context.
- **Volatile Keyword**:
  - **volatile**: Guarantees variable **visibility** across threads and prevents instruction **reordering** (happens-before relationship). It does NOT guarantee atomicity (e.g., `volatile int count` is still vulnerable to race conditions on write operations).

## Locks

Learn advanced locks from `java.util.concurrent.locks` providing flexible locking behaviors:

- **Lock Interface**: Defers from synchronized blocks by offering explicit locking/unlocking methods (`lock()`, `unlock()`), non-blocking lock attempts (`tryLock()`), and interruptible locking (`lockInterruptibly()`).
- **ReentrantLock**: Reentrant mutual exclusion lock.
  - **Reentrancy**: A thread can re-acquire a lock it already holds without deadlocking itself.
  - **Fairness Policy**: Optional parameter in constructor (`ReentrantLock(true)`). Ensures the longest-waiting thread acquires the lock first, preventing starvation at the cost of throughput.
  - **Condition Variables**: Supports multiple wait-sets per lock using `lock.newCondition()`, enabling target wakeups via `await()` and `signal()`.
- **ReadWriteLock / ReentrantReadWriteLock**: Separates read and write operations.
  - **Mechanics**: Multiple threads can hold the read lock concurrently (shared), but only one thread can hold the write lock (exclusive). Significantly improves performance in read-heavy, write-rare applications.
- **StampedLock**: Lock offering optimistic read capability.
  - **Mechanics**: Returns a stamp (token) upon locking. Allows reading without acquiring a full read lock. Before consuming read data, the thread validates the stamp (`lock.validate(stamp)`); if a write occurred, it falls back to a pessimistic read lock.

## Atomic Classes

Learn lock-free classes utilizing low-level hardware support:

- **Atomic Variables**: `AtomicInteger`, `AtomicLong`, `AtomicBoolean`, and `AtomicReference`.
  - **Mechanics**: Implement thread-safe operations without locking by utilizing the **Compare-And-Swap (CAS)** CPU instruction.
  - **CAS Algorithm**: Compares the expected value of a variable to its current value; if they match, it updates the variable to the new value.
  - **Adders**: `LongAdder` and `DoubleAdder`. Better throughput than `AtomicLong` in highly contested environments by maintaining a striped cell array to reduce thread contention.

## Problems

Understand common synchronization hazards:

- **Deadlock**: Two or more threads are blocked forever, each waiting for the lock held by the other. (Prevention: Lock ordering, lock timeouts using `tryLock()`).
- **Livelock**: Threads continuously change their states in response to each other without making any actual forward progress.
- **Starvation**: A thread is perpetually denied access to resources/locks due to greedy threads or priority imbalances.
- **Thread Dumps**: Generating stack traces of all active threads (e.g., `jstack`) to diagnose deadlocks and execution bottlenecks.

Practice implementing:
- Thread-safe counter
- Bank account ledger with transfer capabilities
- Shared inventory system
- Ticket booking simulation with double-booking prevention

# Phase 25 — Executor Framework

Learn the framework that decouples task submission from execution details, managing thread allocation efficiently:

## 25.1 Executor Core Interfaces

- **Executor**: Root interface with a single method: `execute(Runnable)`.
- **ExecutorService**: Extends `Executor`, adding methods to manage lifecycle (`shutdown`, `shutdownNow`) and produce futures for task tracking (`submit(Callable)`, `invokeAll`, `invokeAny`).
- **ScheduledExecutorService**: Extends `ExecutorService` to schedule tasks to run after a delay or periodically (`scheduleAtFixedRate`, `scheduleWithFixedDelay`).

## 25.2 ThreadPoolExecutor Parameters

Understand the core parameters of the standard thread pool implementation:

```java
ThreadPoolExecutor executor = new ThreadPoolExecutor(
    int corePoolSize,
    int maximumPoolSize,
    long keepAliveTime,
    TimeUnit unit,
    BlockingQueue<Runnable> workQueue,
    ThreadFactory threadFactory,
    RejectedExecutionHandler handler
);
```

- **corePoolSize**: The number of threads kept in the pool, even if they are idle.
- **maximumPoolSize**: The maximum number of threads allowed in the pool.
- **keepAliveTime**: If the pool currently has more than `corePoolSize` threads, excess idle threads are terminated if they remain idle for longer than this duration.
- **workQueue**: The queue holding tasks before they are executed (e.g., `LinkedBlockingQueue`, `SynchronousQueue`, `ArrayBlockingQueue`).
- **threadFactory**: Factory to customize thread creation (naming, daemon status, priorities).
- **handler (RejectedExecutionHandler)**: Policy invoked when a task cannot be accepted because the queue is full and all threads are busy:
  - **AbortPolicy**: Throws `RejectedExecutionException` (default).
  - **CallerRunsPolicy**: Executes the task in the caller's thread, slowing down task submission.
  - **DiscardPolicy**: Silently discards the rejected task.
  - **DiscardOldestPolicy**: Discards the oldest unhandled task in the queue and retries execution.

## 25.3 Thread Pools and Lifecycle

Understand pre-configured thread pools available via the `Executors` utility class:

- **FixedThreadPool**: Creates a pool with a fixed number of threads, backed by an unbounded queue (`LinkedBlockingQueue`).
- **CachedThreadPool**: Creates a pool that creates new threads as needed but reuses idle threads when available. Threads terminate if idle for 60 seconds. Backed by `SynchronousQueue`.
- **SingleThreadExecutor**: Uses a single worker thread backed by an unbounded queue, ensuring tasks execute sequentially.
- **ForkJoinPool / WorkStealingPool**: Uses a work-stealing algorithm. Each thread maintains its own double-ended queue (deque) of tasks. When a thread runs out of tasks, it steals tasks from the end of other threads' deques, maximizing CPU utilization.

Understand pool lifecycle management:
- **shutdown()**: Initiates an orderly shutdown where previously submitted tasks are executed, but no new tasks are accepted.
- **shutdownNow()**: Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were awaiting execution.
- **awaitTermination(timeout, unit)**: Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs.

# Phase 26 — CompletableFuture

Learn how to write non-blocking asynchronous code by composing pipeline stages:

## 26.1 Async Foundations

- **Future Limitations**: The legacy `Future` interface requires blocking (`get()`) or polling (`isDone()`) to retrieve results, making it difficult to chain asynchronous operations.
- **CompletableFuture**: Implements `Future` and `CompletionStage`, enabling functional-style asynchronous callbacks and task chaining without blocking the main execution thread.

## 26.2 Execution Pipelines and Methods

- **Starting Tasks**:
  - `supplyAsync(Supplier<U>)`: Runs task asynchronously and returns a result. Executed in the `ForkJoinPool.commonPool()` by default.
  - `runAsync(Runnable)`: Runs task asynchronously without returning a result.
- **Transforming & Consuming (Chaining)**:
  - `thenApply(Function)`: Transforms the result of the previous stage (like `map`).
  - `thenAccept(Consumer)`: Consumes the result of the previous stage without returning anything.
  - `thenRun(Runnable)`: Executes a Runnable stage after the previous stage completes.
- **Combining Futures**:
  - `thenCompose(Function)`: Flattens nested futures (like `flatMap`). Used when the next stage returns a `CompletableFuture`.
  - `thenCombine(CompletionStage, BiFunction)`: Executes two independent futures concurrently and combines their results once both complete.
- **Aggregation**:
  - `allOf(CompletableFuture<?>...)`: Returns a new future that completes when all of the given futures complete.
  - `anyOf(CompletableFuture<?>...)`: Returns a new future that completes when any of the given futures complete.

## 26.3 Exception Handling

- **exceptionally(Function)**: Acts as a catch block. Recovers from an exception by returning a default fallback value.
- **handle(BiFunction)**: Executed regardless of success or failure. Accesses both the result and the exception, allowing transformations and fallbacks.
- **whenComplete(BiConsumer)**: A callback executed when the stage completes; does not modify the result or exception but can perform side effects.

Practice:
- Parallel API simulation
- Async order processing
- Parallel data fetching
- Failure handling

# Phase 27 — Concurrent Collections

Learn specialized collection classes designed for multi-threaded access without global locking bottlenecks:

- **Collections.synchronized vs Concurrent Collections**:
  - `Collections.synchronizedList()` wrappers lock the entire collection on every access, causing severe thread bottlenecking.
  - Concurrent collections utilize granular locks, lock-striping, or copy-on-write strategies to enable high concurrent access.

## 27.1 Implementations

- **ConcurrentHashMap**:
  - Thread-safe key-value store. Utilizes bucket-level synchronization and CAS instructions. Allows concurrent reads and highly concurrent writes. Iterators are weakly consistent and do not throw `ConcurrentModificationException`.
- **CopyOnWriteArrayList / CopyOnWriteArraySet**:
  - Thread-safe implementations where all mutative operations (`add`, `set`, `remove`) are implemented by making a fresh copy of the underlying array.
  - Performance: O(1) read operations with zero locking overhead. Mutations are highly expensive. Ideal for scenarios where **reads vastly outnumber writes** (e.g., event listener lists).
- **ConcurrentSkipListMap / ConcurrentSkipListSet**:
  - Thread-safe sorted map/set implementations based on a SkipList data structure, providing O(log N) operations as a concurrent alternative to `TreeMap`.
- **BlockingQueue Implementations**:
  - **ArrayBlockingQueue**: Bounded queue backed by a circular array. Uses a single lock for enqueue/dequeue.
  - **LinkedBlockingQueue**: Optionally bounded queue backed by nodes. Uses separate locks for enqueue and dequeue, improving throughput.
  - **PriorityBlockingQueue**: Unbounded priority queue with blocking retrieval operations.
  - **DelayQueue**: Time-based blocking queue where elements are only accessible once their specified delay has elapsed.
  - **SynchronousQueue**: Hand-off queue where each enqueue operation must wait for a corresponding dequeue operation by another thread.

## Project

### Multithreaded Task Processing System

Architecture:

Producer
   |
   v
BlockingQueue
   |
   +---- Worker 1
   +---- Worker 2
   +---- Worker 3
   |
   v
Result Processor


---

# Phase 27A — Thread Coordination Utilities

Learn specialized synchronization aids for coordinating execution flows across multiple threads:

## 27A.1 Coordination Classes

- **CountDownLatch**:
  - **Mechanics**: Allows one or more threads to block until a set of operations being performed in other threads completes.
  - **API**: Initialized with a count (`new CountDownLatch(N)`). Threads call `await()` to block. Working threads call `countDown()` to decrement the counter. Once the count reaches zero, all waiting threads are released.
  - **Reset**: Cannot be reset (one-shot utility).
- **CyclicBarrier**:
  - **Mechanics**: Allows a set of threads to all wait for each other to reach a common barrier point.
  - **API**: Initialized with the number of parties (`new CyclicBarrier(N)`). Threads call `barrier.await()` to block until all N threads have reached the barrier. Supports a optional runnable barrier action executed once the barrier is tripped.
  - **Reset**: Reusable; can be reset using `barrier.reset()`.
- **Semaphore**:
  - **Mechanics**: Maintains a set of permits. Used to restrict access to a resource pool or rate-limit operations.
  - **API**: Threads call `acquire()` to obtain a permit (blocking if none are available) and `release()` to return it. A binary semaphore (1 permit) acts as a mutual exclusion lock.
- **Phaser**:
  - **Mechanics**: A flexible, reusable synchronization barrier that supports dynamic registration of participating threads and multi-phase execution. A more advanced alternative to `CountDownLatch` and `CyclicBarrier`.
- **Exchanger**:
  - **Mechanics**: A bidirectional synchronization point where two threads can pair and swap elements (useful in pipeline designs).

## Practice

Build:
- A service initializer using `CountDownLatch` to wait for dependent microservice checks before launching.
- A multi-threaded algorithm using `CyclicBarrier` to divide computation and merge results at each step.
- A connection pool manager using `Semaphore` to limit concurrent database connections.

# Phase 28 — JVM Deep Dive

## 28.1 JVM Runtime Areas

Learn:

- Heap
- Stack
- PC register
- Method area / Metaspace
- Native method stack

## 28.2 Heap

Understand:

- Objects
- References
- Young generation
- Old generation
- Survivor spaces

## 28.3 Stack

Understand:

- Stack frames
- Local variables
- Operand stack
- Method calls
- StackOverflowError

## 28.4 Class Loading

Learn:

- Bootstrap ClassLoader
- Platform ClassLoader
- Application ClassLoader
- Loading
- Linking
- Initialization

---

# Phase 29 — Garbage Collection

Learn:

- Reachability
- GC roots
- Minor GC
- Major GC
- Full GC
- Stop-the-world
- Generational GC

Know the concepts behind:

- Serial GC
- Parallel GC
- G1 GC
- ZGC

Understand:

- Memory leaks in managed languages
- Object retention
- Heap dumps
- GC logs basics

---

# Phase 30 — Java Memory Model

Learn deeply:

- Heap
- Stack
- Visibility
- Atomicity
- Ordering
- Happens-before
- volatile
- synchronized
- Final field semantics

Connect JMM with:

- Threads
- Locks
- Concurrent collections
- Race conditions

---

# Phase 31 — Reflection

Learn:

- Class object
- Reflection API
- Constructor inspection
- Field inspection
- Method inspection
- Dynamic invocation

Understand:

- Reflection use cases
- Reflection costs
- Framework usage

Understand how frameworks can discover classes, methods and annotations at runtime.

---

# Phase 32 — Annotations

Learn:

- Built-in annotations
- @Override
- @Deprecated
- @SuppressWarnings
- @FunctionalInterface

## Custom Annotations

Learn:

- @interface
- Retention
- Target
- Documented
- Inherited

Understand:

- SOURCE
- CLASS
- RUNTIME

Build a small annotation-based validation example.

---

# Phase 33 — Modern Java Features

Learn the important language/library evolution across modern Java versions.

Topics include:

- var
- switch expressions
- text blocks
- records
- pattern matching
- sealed classes
- enhanced instanceof
- pattern matching for switch
- helpful NullPointerExceptions
- modern collection factory methods
- sequenced collections where applicable
- virtual threads
- structured concurrency concepts where supported by your selected JDK

Always verify feature availability against the Java version you are using.

---


---

# Phase 33A — Java 8 Deep Dive

Master the revolutionary changes introduced in Java 8 (LTS) that shifted Java toward functional programming:

## 33A.1 Lambda Expressions and Functional Interfaces
- **Lambda Expressions**: Syntax `(parameters) -> { body }`. Eliminates the boilerplate code of anonymous inner classes.
- **Variable Capture**: Lambdas can access local variables of the enclosing scope, but they must be **final** or **effectively final** (not modified after initialization).
- **Functional Interface**: An interface containing exactly one abstract method. Can be annotated with `@FunctionalInterface`.
- **Standard Functional Interfaces (`java.util.function`)**:
  - **Predicate<T>**: `boolean test(T t)` — Evaluates a condition.
  - **Function<T, R>**: `R apply(T t)` — Transforms input T to output R.
  - **Consumer<T>**: `void accept(T t)` — Performs an action on input.
  - **Supplier<T>**: `T get()` — Generates a value.
  - **UnaryOperator<T>** / **BinaryOperator<T>**: Specialized sub-interfaces where inputs and outputs have the same type.
  - **Bi-interfaces**: `BiPredicate<T, U>`, `BiFunction<T, U, R>`, `BiConsumer<T, U>`.
  - **Primitive Specializations**: `IntPredicate`, `LongConsumer`, `DoubleSupplier` to avoid boxing/unboxing overhead.

## 33A.2 Stream API
- **Stream Concepts**: A sequence of elements supporting sequential and parallel aggregate operations. Streams do not store data; they operate on a source (like collections) and pipeline operations.
- **Pipeline Structure**: Source -> Intermediate Operations (lazy) -> Terminal Operation (eager).
- **Lazy Evaluation**: Intermediate operations (e.g., `filter`, `map`) are not executed until a terminal operation (e.g., `collect`, `forEach`) is invoked.
- **Key Intermediate Operations**:
  - `filter(Predicate)`: Filters elements.
  - `map(Function)`: Transforms elements.
  - `flatMap(Function)`: Flattens nested streams (transforms stream of collections/streams to flat stream).
  - `distinct()`, `sorted()`, `limit(n)`, `skip(n)`.
- **Key Terminal Operations**:
  - `forEach(Consumer)`, `count()`.
  - `collect(Collector)`: Accumulates elements into collections (`toList()`, `toSet()`).
  - `reduce(BinaryOperator)`: Combines elements into a single value (e.g., sum, product).
  - Matches: `anyMatch(Predicate)`, `allMatch(Predicate)`, `noneMatch(Predicate)`.
  - Finds: `findFirst()`, `findAny()`.
- **Collectors Utility (`java.util.stream.Collectors`)**:
  - Grouping: `groupingBy(Function)` (returns Map grouped by key).
  - Partitioning: `partitioningBy(Predicate)` (splits data into true/false keys).
  - Joining: `joining(delimiter)` (concatenates strings).
- **Parallel Streams**: Created using `parallelStream()`. Automatically splits tasks and runs them concurrently in the ForkJoin common pool.

## 33A.3 Interface Evolution
- **Default Methods**: Declared using the `default` keyword. Allows adding new methods to interfaces without breaking existing implementations.
- **Static Methods**: Declared using the `static` keyword inside interfaces. Used as utility methods.
- **Diamond Problem Resolution**: If a class implements two interfaces defining default methods with identical signatures, the class must explicitly override the method to resolve the ambiguity (e.g., `InterfaceA.super.methodName()`).

## 33A.4 Optional Class
- **Optional<T>**: A container object which may or may not contain a non-null value. Designed to replace `null` checks and prevent `NullPointerException`.
- **API Methods**:
  - Creation: `Optional.of(value)` (throws NPE if null), `Optional.ofNullable(value)`, `Optional.empty()`.
  - Consumption: `isPresent()`, `ifPresent(Consumer)`, `orElse(fallback)`, `orElseGet(Supplier)`, `orElseThrow()`.
  - Transformation: `map()`, `flatMap()`, `filter()`.

## 33A.5 Date and Time API (`java.time`)
- **Flaws of Legacy Date/Calendar**: Mutable, not thread-safe, poor timezone handling, inconsistent numbering (months started at 0).
- **New Core Classes**:
  - **LocalDate**: Date only (YYYY-MM-DD).
  - **LocalTime**: Time only (HH:MM:SS.nanos).
  - **LocalDateTime**: Combines date and time.
  - **Instant**: Represents a timestamp on the timeline (UTC timezone).
  - **ZonedDateTime** / **OffsetDateTime**: Timezones and UTC offsets.
  - **Duration**: Time-based amount (seconds, nanoseconds).
  - **Period**: Date-based amount (years, months, days).
  - **DateTimeFormatter**: Thread-safe formatter.

## 33A.6 Additional Features
- **Method References**: Syntactic shorthand for lambdas that call existing methods (`Class::methodName`).
- **Parallel Array Sorting**: `Arrays.parallelSort()` splits arrays into sub-arrays sorted in parallel on multiple cores.
- **Base64**: Standardized Base64 encoding and decoding API (`java.util.Base64`).
- **Nashorn JS Engine**: Lightweight JavaScript engine running on the JVM (later deprecated).
- **Metaspace**: JVM PermGen memory space replaced by Metaspace, which allocates class metadata in native memory (dynamically sized).

---

# Phase 33B — Java 21 Deep Dive

Master the advanced features introduced in Java 21 (LTS), the baseline for modern enterprise development:

## 33B.1 Virtual Threads (Project Loom)
- **Problem**: Traditional Java threads (Platform Threads) map 1:1 to operating system threads. OS threads are resource-heavy, limiting scaling to a few thousand concurrent threads (thread-per-request model bottleneck).
- **Virtual Threads**: Extremely lightweight threads managed by the JVM rather than the OS. Millions of virtual threads can run on a single JVM.
- **Mechanics**:
  - **Carrier Threads**: Virtual threads are mounted onto platform threads (carrier threads) to perform CPU work.
  - **Non-blocking I/O**: When a virtual thread encounters blocking operations (socket read, sleep, lock wait), the JVM demounts the virtual thread from the carrier thread, allowing other virtual threads to execute on it.
  - **Thread Pinning**: Occurs when a virtual thread cannot be demounted from its carrier thread (e.g., inside `synchronized` blocks/methods, or calling native code). Avoid pinning by using `ReentrantLock` instead of `synchronized` in performance-critical blocking sections.
- **API**:
  - Creating: `Thread.ofVirtual().start(Runnable)` or `Thread.ofVirtual().unstarted(Runnable)`.
  - Executors: `Executors.newVirtualThreadPerTaskExecutor()`.

## 33B.2 Sequenced Collections (PEP 431)
- **Problem**: Accessing the first and last elements in Java collections was inconsistent (e.g., `list.get(0)` vs `set.iterator().next()` vs `deque.getFirst()`).
- **New Interfaces**:
  - **SequencedCollection**: Extends `Collection`. Defines `addFirst(e)`, `addLast(e)`, `getFirst()`, `getLast()`, `removeFirst()`, `removeLast()`, and `reversed()`.
  - **SequencedSet**: Extends `SequencedCollection` and `Set`. Ensures uniqueness while maintaining order (implemented by `LinkedHashSet`, `TreeSet`).
  - **SequencedMap**: Extends `Map`. Defines `putFirst(k,v)`, `putLast(k,v)`, `firstKey()`, `lastKey()`, `firstEntry()`, `lastEntry()`, `pollFirstEntry()`, `pollLastEntry()`, `sequencedKeySet()`, `sequencedValues()`, `sequencedEntrySet()`, and `reversed()`.

## 33B.3 Pattern Matching and Records
- **Pattern Matching for Switch**: Allows matching switch selector expressions against patterns, including type patterns and record patterns.
  - **Guarded Patterns**: Use `when` to apply boolean checks inside switch cases (e.g., `case String s when s.length() > 5 -> ...`).
  - **Null Handling**: Switch blocks can handle null directly using `case null -> ...`.
- **Record Patterns**: Allows destructuring record objects directly in `instanceof` or `switch` statements (e.g., `if (obj instanceof Point(int x, int y)) { ... }`).

## 33B.4 Preview Features and APIs
- **String Templates (Preview)**: Safely performs string interpolation using template processors (e.g., `STR."Hello \{name}"` to prevent injection attacks).
- **Unnamed Patterns and Variables**: Uses an underscore `_` to represent unused variables in try-with-resources, catch blocks, lambdas, and pattern matching.
- **Unnamed Classes and Instance Main Methods**: Simplifies the entry point for beginners by allowing a main method to execute outside of a class declaration (e.g., `void main() { System.out.println("Hello"); }`).
- **Scoped Values**: Allows sharing immutable data across threads and sub-tasks safely and efficiently (alternative to `ThreadLocal`).
- **Structured Concurrency**: Treats groups of related tasks running in different threads as a single unit of work, improving error handling and cancellation.

## 33B.5 JVM and GC Updates
- **Generational ZGC**: The Z Garbage Collector (ZGC) updated to maintain separate generations for young and old objects, reducing memory overhead, maximizing throughput, and maintaining sub-millisecond GC pauses.

# Phase 34 — Virtual Threads

Learn Project Loom and virtual threads mechanics:

- **Platform Threads vs Virtual Threads**:
  - **Platform Threads**: Managed by the OS, 1:1 mapping, heavy resource utilization (typically 1MB-2MB stack size).
  - **Virtual Threads**: Managed by the JVM, M:N mapping (many virtual threads run on a pool of carrier platform threads), lightweight resource utilization (a few hundred bytes to kilobytes stack).
- **Thread-Per-Request Model**: Traditional server designs spawned one platform thread per request, hitting memory ceilings quickly. Virtual threads allow spawning a new thread per request with near-zero overhead.
- **Carrier Threads**: The underlying platform thread on which a virtual thread is scheduled and run by the JVM.
- **Mounting & Demounting**:
  - **Mounting**: Binding a virtual thread to a carrier thread to run CPU instructions.
  - **Demounting (Unmounting)**: Unbinding a virtual thread when it blocks on I/O or synchronizers, saving the stack state inside the heap and freeing the carrier thread for other virtual threads.
- **Thread Pinning**:
  - Occurs when a virtual thread cannot be unmounted from its carrier thread during a blocking operation.
  - Causes: Inside a `synchronized` block/method, or executing native code (JNI).
  - Prevention: Replace critical `synchronized` blocks with `ReentrantLock` to allow proper unmounting.
- **APIs**:
  - `Thread.ofVirtual().start(Runnable)` / `Thread.ofVirtual().name("vt-").unstarted(Runnable)`.
  - `Executors.newVirtualThreadPerTaskExecutor()`.
  - `Thread.isVirtual()`.
- **CPU-bound vs I/O-bound workloads**: Virtual threads maximize throughput for I/O-bound tasks but do not speed up CPU-bound operations.

Understand:

> Virtual threads are not a replacement for understanding concurrency.

# Phase 35 — Java Networking Basics

Learn enough Java networking to understand backend development:

- URI
- URL
- HTTP concepts
- HttpClient
- HttpRequest
- HttpResponse
- Synchronous requests
- Asynchronous requests

Practice:

- Call a public API
- Parse a response
- Handle timeout
- Handle failure
- Perform concurrent requests

---

# Phase 36 — Serialization Concepts

Learn:

- Object serialization concept
- Serializable
- transient
- serialVersionUID
- Java serialization risks
- JSON concept
- DTO concept

Do not rely on native Java serialization for modern API design without understanding its security and compatibility implications.

---

# Phase 37 — Regular Expressions

Learn Java regex:

- Pattern
- Matcher
- Character classes
- Quantifiers
- Groups
- Capturing groups
- Lookahead
- Lookbehind
- Replace
- Split

Practice:

- Email validation
- Phone validation
- Log parsing
- Password rules
- Data extraction

---

# Phase 38 — Assertions

Learn:

- assert
- AssertionError
- Enabling assertions
- When assertions are appropriate

Understand assertions vs exceptions.

---

# Phase 39 — Maven

Learn:

- Maven
- pom.xml
- Group ID
- Artifact ID
- Version
- Dependencies
- Dependency scopes
- Plugins
- Lifecycle
- Clean
- Compile
- Test
- Package
- Install
- Dependency management
- Profiles

Build a Java project with Maven.

---

# Phase 40 — Gradle Basics

Learn:

- Gradle
- build.gradle / build.gradle.kts
- Dependencies
- Tasks
- Plugins
- Build lifecycle
- Gradle vs Maven

You do not need expert Gradle knowledge initially.

---

# Phase 41 — Logging

Learn:

- Why logging matters
- Log levels
- Logging best practices
- SLF4J concepts
- Logback concepts
- Structured logging
- Correlation IDs
- Avoiding sensitive data in logs

---

# Phase 42 — Testing with JUnit

Learn:

- Unit testing
- JUnit 5
- @Test
- Assertions
- @BeforeEach
- @AfterEach
- @BeforeAll
- @AfterAll
- Parameterized tests
- Test lifecycle
- Test naming

Practice testing:

- Services
- Utilities
- Collections
- Validation
- Exception cases

---

# Phase 43 — Mockito Basics

Learn:

- Mock
- Stub
- Verify
- Argument matchers
- @Mock
- @InjectMocks
- Spies
- Mocking dependencies

Understand:

> Unit tests should isolate the unit under test.

---

# Phase 44 — Debugging and Profiling

Learn:

- IDE debugger
- Breakpoints
- Conditional breakpoints
- Step over
- Step into
- Step out
- Watch expressions
- Stack traces
- Heap analysis concepts
- CPU profiling concepts
- Thread dumps
- Heap dumps

Tools to know:

- IntelliJ debugger
- JDK command-line diagnostic tools
- JFR basics
- VisualVM basics

---

# Phase 45 — Clean Code

Learn:

- Meaningful names
- Small methods
- Single responsibility
- Avoid duplication
- Clear APIs
- Immutability
- Defensive programming
- Error handling
- Comments vs self-documenting code

Study:

- SOLID
- DRY
- KISS
- YAGNI

---

# Phase 46 — Design Patterns in Java

Master patterns through implementation rather than memorization.

## Creational

- Factory
- Abstract Factory
- Builder
- Singleton
- Prototype

## Structural

- Adapter
- Decorator
- Facade
- Proxy
- Composite

## Behavioral

- Strategy
- Observer
- Template Method
- Chain of Responsibility
- Command
- State
- Iterator

Focus on:

- Problem
- Why the pattern exists
- Implementation
- Trade-offs
- Real-world use

---

# Phase 47 — Data Structures Implementation in Java

Before relying entirely on library implementations, implement these yourself.

## Linear

- Dynamic array
- Linked list
- Doubly linked list
- Stack
- Queue
- Circular queue
- Deque

## Trees

- Binary tree
- BST
- Heap
- Trie

## Graphs

- Adjacency matrix
- Adjacency list
- BFS
- DFS

## Hashing

Implement:

- Simple hash table
- Collision handling
- Chaining
- Basic open addressing

---

# Phase 48 — DSA Using Java

Start serious algorithm preparation.

## Arrays

- Two pointers
- Sliding window
- Prefix sum
- Difference array
- Kadane's algorithm
- Binary search

## Strings

- Frequency counting
- Sliding window
- String hashing concepts
- Pattern matching concepts

## Linked Lists

- Reverse
- Cycle detection
- Merge
- Intersection
- Fast/slow pointers

## Stack and Queue

- Monotonic stack
- Next greater element
- BFS

## Trees

- Traversals
- Height
- Diameter
- BST
- LCA

## Heap

- Top K
- Median
- Scheduling

## Graphs

- BFS
- DFS
- Topological sort
- Union Find
- Dijkstra
- Minimum spanning tree

## Dynamic Programming

- 1D DP
- 2D DP
- Knapsack
- Subsequence
- Grid DP

---

# Phase 49 — Core Java Projects

Complete these in increasing difficulty.

## Project 1 — Calculator

Topics:

- Variables
- Methods
- Conditions

## Project 2 — Student Management System

Topics:

- OOP
- Collections
- Validation

## Project 3 — Library Management System

Topics:

- OOP
- Collections
- Exceptions
- File I/O

## Project 4 — Expense Tracker

Topics:

- Collections
- Streams
- Date/Time
- File persistence

## Project 5 — Banking System

Topics:

- OOP
- Interfaces
- Exceptions
- Collections
- Transactions concepts

## Project 6 — Multithreaded Task Processor

Topics:

- Threads
- ExecutorService
- BlockingQueue
- Concurrency

## Project 7 — Mini In-Memory Database

Features:

- Insert
- Update
- Delete
- Search
- Index
- Query-like filtering

Topics:

- Collections
- Generics
- OOP
- Streams
- Concurrency

## Project 8 — Mini E-Commerce Engine

Features:

- Users
- Products
- Cart
- Orders
- Inventory
- Discounts
- Payment abstraction

Topics:

- OOP
- Design patterns
- Collections
- Exceptions
- Concurrency
- Testing

---

# Phase 50 — Core Java Interview Preparation

## Beginner Questions

Prepare:

- What is Java?
- JDK/JRE/JVM?
- Why Java is platform independent?
- Primitive vs reference types?
- Stack vs heap?
- String immutability?
- `==` vs `equals()`?
- final vs finally vs finalize?
- static keyword?
- Constructor?

## OOP Questions

Prepare:

- Encapsulation?
- Inheritance?
- Polymorphism?
- Abstraction?
- Interface vs abstract class?
- Composition vs inheritance?
- Overloading vs overriding?
- Upcasting vs downcasting?
- Why Java doesn't support multiple class inheritance?

## Collections Questions

Prepare:

- ArrayList vs LinkedList?
- HashMap internals?
- HashSet internals?
- HashMap vs Hashtable?
- HashMap vs ConcurrentHashMap?
- TreeMap vs HashMap?
- Comparable vs Comparator?
- Fail-fast vs fail-safe?
- What happens when HashMap key is mutable?

## Concurrency Questions

Prepare:

- Process vs thread?
- Race condition?
- synchronized?
- volatile?
- Atomic classes?
- Lock vs synchronized?
- Deadlock?
- ExecutorService?
- Future?
- CompletableFuture?
- ConcurrentHashMap?
- Virtual threads?

## JVM Questions

Prepare:

- JVM architecture?
- Heap vs stack?
- Garbage collection?
- JIT?
- Class loading?
- Metaspace?
- Memory leak in Java?
- StackOverflowError?
- OutOfMemoryError?
- G1 GC?
- JMM?
- Happens-before?

---

# Phase 51 — FAANG/MAANG-Level Java Preparation

For FAANG-level interviews, Java syntax alone is not enough.

Combine Core Java with:

## DSA

Target:

- ~250–350 quality problems
- Mostly Easy/Medium
- Carefully selected Hard problems

## Low-Level Design

Learn:

- SOLID
- Interfaces
- Composition
- Design patterns
- Extensible class design

Practice:

- Parking lot
- Library
- Elevator
- ATM
- Payment system
- Food ordering
- Cab booking

## High-Level Design

Learn:

- Scalability
- Load balancing
- Caching
- Databases
- Replication
- Sharding
- Queues
- Kafka
- Distributed systems
- Consistency
- Availability
- Fault tolerance

---

# Phase 52 — Java Backend Transition

After Core Java, move to:

```text
Core Java
    |
    +--> DSA
    |
    +--> SQL
    |
    v
Spring
    |
    v
Spring Boot
    |
    +--> REST
    +--> JPA/Hibernate
    +--> Spring Security
    +--> Testing
    |
    v
Redis
    |
    v
Kafka
    |
    v
Microservices
    |
    v
Docker
    |
    v
AWS
```

---

# Complete Learning Sequence

```text
01. Java Introduction
02. JDK/JRE/JVM
03. Variables
04. Data Types
05. Operators
06. Type Casting
07. Control Flow
08. Loops
09. Methods
10. Arrays
11. Strings
12. OOP
13. Classes and Objects
14. Constructors
15. Encapsulation
16. Inheritance
17. Polymorphism
18. Abstraction
19. Interfaces
20. Composition
21. Packages
22. Access Modifiers
23. static/final/this/super
24. Enums
25. Records
26. Nested Classes
27. Exceptions
28. File I/O
29. NIO
30. Date/Time
31. Generics
32. Collections
33. HashMap Internals
34. Comparable/Comparator
35. Functional Interfaces
36. Lambdas
37. Method References
38. Streams
39. Optional
40. Threads
41. Synchronization
42. Locks
43. Atomic Classes
44. Executors
45. CompletableFuture
46. Concurrent Collections
47. JVM
48. Memory
49. Garbage Collection
50. Class Loading
51. Java Memory Model
52. Reflection
53. Annotations
54. Modern Java
55. Virtual Threads
56. Networking
57. Serialization
58. Regex
59. Maven
60. Gradle
61. Logging
62. JUnit
63. Mockito
64. Debugging
65. Profiling
66. Clean Code
67. Design Patterns
68. Data Structures
69. Algorithms
70. Projects
71. Interview Preparation
```

---

# Suggested 4-Month Core Java Schedule

## Month 1 — Fundamentals + OOP

### Week 1

- Java setup
- JDK/JRE/JVM
- Variables
- Data types
- Operators

### Week 2

- Conditions
- Loops
- Methods
- Basic problem solving

### Week 3

- Arrays
- Strings
- StringBuilder
- StringBuffer

### Week 4

- Classes
- Objects
- Constructors
- Encapsulation
- Inheritance
- Polymorphism

---

# Month 2 — Java Intermediate

### Week 5

- Abstraction
- Interfaces
- Composition
- Packages
- Access modifiers
- Enums

### Week 6

- Exceptions
- File I/O
- NIO
- Date/Time

### Week 7

- Generics
- Collections
- List
- Set

### Week 8

- Map
- Queue
- Deque
- HashMap internals
- Comparable/Comparator

---

# Month 3 — Modern Java + Concurrency

### Week 9

- Functional interfaces
- Lambdas
- Method references

### Week 10

- Stream API
- Collectors
- Optional

### Week 11

- Threads
- Synchronization
- volatile
- Atomic classes
- Locks

### Week 12

- ExecutorService
- Thread pools
- Future
- CompletableFuture
- Concurrent collections

---

# Month 4 — Advanced Java + Projects

### Week 13

- JVM architecture
- Heap
- Stack
- Class loading
- JIT

### Week 14

- Garbage collection
- Java Memory Model
- Happens-before
- Memory leaks

### Week 15

- Reflection
- Annotations
- Modern Java
- Virtual threads
- Networking

### Week 16

- Maven
- Testing
- Debugging
- Profiling
- Design patterns
- Final project

---

# Daily Study Template

For a 3-hour study day:

```text
60 min — Learn Java concept
45 min — Write Java code
45 min — DSA
30 min — Revision/interview questions
```

For a 5-hour weekend:

```text
2 hours — Java
1.5 hours — DSA
1 hour — Project
30 min — Revision
```

---

# Core Java Completion Checklist

## Fundamentals

- [ ] JDK/JRE/JVM
- [ ] Java execution process
- [ ] Variables
- [ ] Primitive types
- [ ] Reference types
- [ ] Operators
- [ ] Type casting
- [ ] Control flow
- [ ] Methods

## OOP

- [ ] Classes
- [ ] Objects
- [ ] Constructors
- [ ] Encapsulation
- [ ] Inheritance
- [ ] Polymorphism
- [ ] Abstraction
- [ ] Interfaces
- [ ] Composition
- [ ] equals/hashCode
- [ ] Object class

## Collections

- [ ] ArrayList
- [ ] LinkedList
- [ ] HashSet
- [ ] LinkedHashSet
- [ ] TreeSet
- [ ] HashMap
- [ ] LinkedHashMap
- [ ] TreeMap
- [ ] Queue
- [ ] Deque
- [ ] PriorityQueue
- [ ] ConcurrentHashMap
- [ ] Iterator
- [ ] Comparable
- [ ] Comparator
- [ ] Generics

## Modern Java

- [ ] Functional interfaces
- [ ] Lambda
- [ ] Method references
- [ ] Streams
- [ ] Collectors
- [ ] Optional
- [ ] Records
- [ ] Pattern matching
- [ ] Sealed classes
- [ ] Virtual threads

## Advanced

- [ ] Threads
- [ ] Synchronization
- [ ] volatile
- [ ] Locks
- [ ] Atomic classes
- [ ] ExecutorService
- [ ] CompletableFuture
- [ ] Concurrent collections
- [ ] JVM
- [ ] Heap
- [ ] Stack
- [ ] GC
- [ ] JMM
- [ ] Class loading
- [ ] Reflection
- [ ] Annotations

## Engineering

- [ ] Maven
- [ ] Gradle basics
- [ ] Logging
- [ ] JUnit
- [ ] Mockito
- [ ] Debugging
- [ ] Profiling
- [ ] Clean Code
- [ ] SOLID
- [ ] Design Patterns

## Projects

- [ ] Calculator
- [ ] Student Management
- [ ] Library Management
- [ ] Expense Tracker
- [ ] Banking System
- [ ] Multithreaded Task Processor
- [ ] Mini In-Memory Database
- [ ] Mini E-Commerce Engine

---

# Final Outcome

After completing this syllabus, you should be able to:

1. Write Java from scratch.
2. Understand Java's object model.
3. Use the Collections Framework confidently.
4. Explain HashMap internals.
5. Write generic and reusable Java code.
6. Use streams and functional programming.
7. Write concurrent programs.
8. Understand JVM memory and garbage collection.
9. Debug and profile Java applications.
10. Write unit tests.
11. Design maintainable object-oriented systems.
12. Solve DSA problems using Java.
13. Start Spring Boot with a strong foundation.
14. Prepare for Java backend interviews.
15. Progress toward FAANG/MAANG Software Engineer interviews.

---

# Recommended Next Step

Do not try to finish the entire syllabus by watching tutorials.

Follow this cycle:

```text
CONCEPT
   ↓
EXAMPLE
   ↓
CODE YOURSELF
   ↓
EXERCISES
   ↓
DSA
   ↓
MINI PROJECT
   ↓
INTERVIEW QUESTIONS
   ↓
REVISION
```

Then proceed:

```text
Core Java
   ↓
DSA
   ↓
SQL
   ↓
Spring Boot
   ↓
Spring Security
   ↓
JPA/Hibernate
   ↓
Redis
   ↓
Kafka
   ↓
Microservices
   ↓
Docker
   ↓
AWS
   ↓
System Design
   ↓
FAANG/MAANG Interviews
```


# Appendix A — Engineering Digest Playlist Mapping

The supplied Engineering Digest playlist should be used as the **primary video-learning track** for the foundational Core Java sections. Use this document as the master syllabus and mark a topic complete only after you can code it independently.

| Playlist area | Syllabus phase | What to master |
|---|---|---|
| Java introduction | Phase 1 | JDK, JRE, JVM, bytecode, program execution |
| Java syntax | Phase 1 | Program structure, comments, keywords, literals, main method |
| Variables and data types | Phase 2 | Primitive/reference types, casting, wrappers |
| Operators | Phase 2 | Arithmetic, relational, logical, bitwise, ternary, shifts |
| Control flow | Phase 3 | if/else, switch, loops, break, continue |
| Methods | Phase 4 | Parameters, return values, overload, varargs, pass-by-value |
| Arrays | Phase 5 | 1D/2D arrays and common operations |
| Strings | Phase 6 | String, immutability, StringBuilder/StringBuffer |
| OOP | Phase 7 | Classes, objects, constructors, encapsulation, inheritance, polymorphism, abstraction |
| Interfaces | Phase 9 | Interface design, default/static methods, abstraction |
| Packages/access | Phase 8 | Packages and access modifiers |
| Collections | Phase 17 | List, Set, Map, Queue, iteration, HashMap concepts |
| Generics | Phase 16 | Generic classes, methods, bounds, wildcards, PECS |
| Exception handling | Phase 13 | try/catch/finally, throw/throws, custom exceptions |
| File handling | Phase 14 | File I/O, streams, readers/writers, NIO |
| Advanced Java | Phases 18–46 | Lambdas, Streams, Optional, concurrency, JVM, testing, tools, design |

## Playlist Completion Rule

For every playlist lesson:

1. Watch the lesson.
2. Reproduce the code without copying.
3. Modify the example.
4. Solve 2–5 related exercises.
5. Add one interview question to your notes.
6. Mark the syllabus topic complete only when you can explain it without the video.

## Important

The Engineering Digest playlist is a **learning resource**, not the entire target syllabus. The later sections on JVM internals, concurrency, testing, build tools, DSA, design patterns, distributed systems, Spring Boot and system design are intentionally included to bridge the gap between basic Java learning and your Software Engineer/FAANG preparation.
