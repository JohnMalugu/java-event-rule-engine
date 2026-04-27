# Java Event Rule Engine 🚀

A high-performance, real-time event processing and rule evaluation engine built with **Java 21**.

Designed for:

* ⚡ Low latency processing
* 🔁 High throughput systems
* 🧩 Extensibility via rules
* 🧵 Multi-threaded event pipelines
* 🚫 Zero external dependencies

---

## 🧠 Problem Statement

Modern systems generate massive streams of events (e.g. network metrics, logs, IoT data).
This project provides a **lightweight rule engine** to process events in real-time and trigger actions when conditions are met.

---

## 🏗️ Architecture Overview

```
EventSource → EventQueue → Workers → EventProcessor → RuleEngine → Actions
```

### Flow:

1. **EventSource** generates events continuously
2. Events are pushed into a **bounded queue (backpressure)**
3. Multiple **worker threads** consume events
4. **EventProcessor** processes events
5. **RuleEngine** evaluates rules
6. Matching rules trigger **Actions**

---

## 🔩 Core Components

### 1. Event

Immutable data structure representing incoming data.

```java
public record Event(
    UUID id,
    String type,
    Instant timestamp,
    Map<String, Object> attributes
) {}
```

---

### 2. Rule

Encapsulates:

* Condition (logic)
* Action (execution)

```java
public record Rule(
    String name,
    Condition condition,
    Action action
) {}
```

---

### 3. RuleEngine

Evaluates all rules against an event.

```java
public void evaluate(Event event) {
    for (Rule rule : rules) {
        if (rule.condition().evaluate(event)) {
            rule.action().execute(event);
        }
    }
}
```

---

### 4. Condition

Defines logic for rule matching.

Example:

```java
latency > 150 && packetLoss > 0.03
```

---

### 5. Action

Defines what happens when a rule is triggered.

Example:

```java
System.out.println("ALERT triggered...");
```

---

### 6. EventQueue (Backpressure)

* Uses `ArrayBlockingQueue`
* Prevents system overload
* Blocks producers when full

---

### 7. Worker Threads

* Parallel event consumption
* Scales with CPU cores

```java
int workers = Runtime.getRuntime().availableProcessors();
```

---

### 8. EventProcessor

Handles:

* Counting events
* Delegating to RuleEngine

---

## ▶️ How to Run

```bash
javac *.java
java Main
```

---

## 📊 Example Output

```
ALERT triggered for event 123...
Processed events: 1000
Processed events: 2000
```

---

## 🧪 Example Rule

```java
Rule rule = new Rule(
    "Poor Network Quality",
    new LatencyAndLossCondition(),
    new AlertAction()
);
```

---

## 🔌 How to Extend

### Add a New Condition

```java
public class HighCpuCondition implements Condition {
    public boolean evaluate(Event event) {
        return (int) event.attributes().get("cpu") > 80;
    }
}
```

---

### Add a New Action

```java
public class EmailAction implements Action {
    public void execute(Event event) {
        // send email
    }
}
```

---

### Add New Rule

```java
new Rule("High CPU", new HighCpuCondition(), new EmailAction());
```

---

## ⚡ Performance Design

* **Lock-free reads** (immutable events)
* **Backpressure control** via bounded queue
* **Multi-threaded workers**
* **Minimal object allocation**
* **No external libraries**

---

## 🧠 Design Decisions

| Decision              | Reason                     |
| --------------------- | -------------------------- |
| BlockingQueue         | Built-in backpressure      |
| Records (Java 21)     | Immutable & concise        |
| Functional Interfaces | Flexible rules             |
| Multi-threading       | High throughput            |
| No frameworks         | Full control & performance |

---

## 🚧 Future Improvements

* Rule DSL (JSON/YAML rules)
* Dynamic rule loading
* Metrics dashboard
* Async/non-blocking queue
* Event persistence
* Priority queues
* Complex event processing (CEP)

---

## 🎯 Why This Project Matters

This project demonstrates:

* Concurrent system design
* Real-time data processing
* Clean architecture principles
* Extensibility patterns
* Performance-oriented thinking

---

## 📌 Author Notes

This project is built as part of a portfolio to showcase backend engineering skills in:

* Java concurrency
* System design
* Event-driven architecture
