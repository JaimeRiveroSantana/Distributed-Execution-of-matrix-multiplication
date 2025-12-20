# Distributed Execution of Matrix Multiplication

This repository contains Python and Java implementations for distributed matrix multiplication using the MapReduce programming model.

---

## Objectives

- Implement matrix multiplication using the MapReduce programming model.
- Provide two working implementations:  
  • Python (Hadoop Streaming style with `stdin`/`stdout`)  
  • Java (in-memory concurrent simulation)
- Demonstrate the Map → Shuffle → Reduce workflow explicitly.
- Evaluate performance and scalability of the distributed approach.
- Deliver a clear, reproducible, and well-documented solution.

---

## Repository Content

- `python/` : Python implementation using `mrjob`.
- `java/` : Java in-memory concurrent simulation of MapReduce.
- `input.txt` : Sample input data for Python implementation.
- `README.md` : This file.

---

## Python Execution

Requirements:

```bash
pip install mrjob
```
## How to run: 

```bash
cd python/
cat input.txt | python3 mapper.py | sort | python3 reducer.py
```
---
## Java Execution
```
Open project in IntelliJ IDEA.
Mark src/main/java as Sources Root.
Run DistributedMatrixMultiplication.java.
```
