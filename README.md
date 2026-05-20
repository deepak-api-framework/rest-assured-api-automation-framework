# Rest Assured API Automation Framework

## Overview
This project is a scalable and reusable API Automation Framework developed using Rest Assured, TestNG, and Maven.

The framework is designed using industry-standard automation practices including:
- Reusable request handling
- Generic API utilities
- POJO serialization/deserialization
- Data-driven testing
- Dynamic Extent Reporting
- Centralized request specifications
- Maintainable framework architecture

The framework currently supports:
- Add Place API
- GET Place API
- End-to-end API validation flow

---

## Tech Stack

- Java
- Rest Assured
- TestNG
- Maven
- Jackson Databind
- Extent Reports
- Git & GitHub

---

## Framework Features

- Reusable API utility methods
- Generic HTTP request handling
- POJO-based response deserialization
- Data-driven execution using TestNG DataProviders
- Extent Report integration with request/response logging
- Centralized Request and Response Specifications
- Clean Maven project structure
- Git integrated project management

---

## Project Structure

src/main/java
- base
- payloads
- pojo
- resources
- utils

src/test/java
- listeners
- testdata
- tests

---

## Execute Tests

Run using Maven:

```bash
mvn test