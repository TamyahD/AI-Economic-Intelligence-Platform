# AI Economic Intelligence Platform

A cloud-native AI-powered economic intelligence platform built with **Java**, **Spring Boot**, and **PostgreSQL**. The project is designed to aggregate economic data, analyze trends, and generate AI-driven insights through a scalable microservice architecture.

> **Project Status:** 🚧 Initial setup in progress


## Planned Features

- Economic data ingestion from external APIs
- PostgreSQL persistence layer
- AI-generated economic summaries using a local LLM
- REST API for accessing economic indicators
- Scheduled background jobs for data collection
- Docker containerization
- Kubernetes deployment
- Observability with Spring Boot Actuator
- CI/CD pipeline automation
- Cloud deployment (Azure/AWS)

## Technology Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

### Planned Technologies

- Ollama (Local LLM)
- Docker
- Kubernetes
- Spring AI
- Prometheus & Grafana
- GitHub Actions

## Current Progress

```
                Spring Boot Application
                          │
                    Spring Data JPA
                          │
                       Hibernate
                          │
                      JDBC Driver
                          │
                          ▼
                       PostgreSQL
                          │
                          ▼
              Database: economic_platform

```

## Running the Project

### Prerequisites

- Java 17+
- Maven
- PostgreSQL

### Start the application

```bash
mvn spring-boot:run
```

or

```bash
./mvnw spring-boot:run
```

## Roadmap

- [x] Initialize Spring Boot project
- [x] Configure PostgreSQL datasource
- [x] Create project structure
- [ ] Create JPA entities
- [ ] Implement repositories
- [ ] Build com.td.aieconomics.service layer
- [ ] Develop REST controllers
- [ ] Integrate AI summarization
- [ ] Containerize with Docker
- [ ] Deploy to Kubernetes
- [ ] Configure monitoring and logging

---

**Status:** Early development (foundation complete)