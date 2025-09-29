# Kadane's Algorithm — Maximum Subarray with Position Tracking

## Overview
This project implements Kadane's algorithm in Java to find the subarray with the maximum sum in O(n) time and O(1) extra space. It includes:
- position tracking (start index and end index),
- input validation and error handling,
- a small `PerformanceTracker` for basic metrics,
- a CLI `BenchmarkRunner` for different input distributions,
- comprehensive JUnit 5 tests.

## Repository layout
(see the structure in README previously provided)

## Algorithm summary
Kadane's algorithm keeps two variables as it scans the array:
- `maxEndingHere` — best sum for subarray ending at current index,
- `maxSoFar` — best sum found so far across all indices.

We also track `tempStart` to update start index when we restart sum at current element.

Edge cases: all-negative array returns the single largest (least negative) element and its index.

## Build & test

Requirements:
- JDK 17+
- Maven (optional if using IntelliJ's bundled Maven)

Build:
```bash
mvn clean package
