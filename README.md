🚀 Java Concurrency Examples
--
This project demonstrates 3 different approaches to process a list of numbers in parallel in Java, all inside a single Main file:

🧵 Manual Threads

⚡ ExecutorService (Thread Pool)

🌊 Parallel Streams

Each approach multiplies a list of 100 integers by 2 and compares performance.

--
📌 Problem Statement
-
Given a list of 100 numbers, multiply each number by 2.
Process the numbers in parallel with 5 workers.

--
🛠️ Approache
-
1. 🧵 Using Threads

Manually create 5 threads.

Each thread handles a chunk of 20 numbers.

Requires explicit management (start(), join()).

--
2. ⚡ Using ExecutorService

Uses a fixed thread pool of 5 workers.

Tasks are submitted via executor.submit(...).

Automatically manages scheduling of tasks.

--
3. 🌊 Using Parallel Streams

Java 8+ built-in way to process collections in parallel.

Just call .parallelStream() and apply a map().

---------------------------------------------------------------------------
| Approach            | Code Complexity | Control Over Threads | Readability | Performance ⚡ |
| ------------------- | --------------- | -------------------- | ----------- | ------------- |
| 🧵 Threads          | High            | ✅ Full Control       | ❌ Verbose   | Medium        |
| ⚡ ExecutorService   | Medium          | ✅ Good Control       | 🙂 Balanced | High          |
| 🌊 Parallel Streams | Low             | ❌ Limited            | ✅ Very High | High          |
----------------------------------------------------------------------------------
💡 Interview Notes
-
🧵 Threads → Good for learning fundamentals, but not scalable.

⚡ ExecutorService → Best for controlled thread pools in production.

🌊 Parallel Streams → Perfect for data pipelines where readability matters.

👉 Key Interview Tip:
-
Use Threads when asked to show low-level control.

Use ExecutorService for real-world production apps.

Use Parallel Streams when interviewer values Java 8+ modern style.
