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
                                     +----------------------+
                                     |   Spring Boot App    |
                                     +----------+-----------+
                                                |
                                                v
                                    +-------------------------+
                                    | REST Controllers        |
                                    +-----------+-------------+
                                                |
                                                v
                               +------------------------------------+
                               | EconomicAnalysisService            |
                               | (AI Workflow Orchestration)        |
                               +------+----------------------+-------+
                                      |                      |
                                      |                      |
                         +------------v----+        +--------v----------------+
                         | EconomicPrompt  |        | Spring AI ChatClient    |
                         | Service         |        |                         |
                         +------------+----+        +------------+------------+
                                      |                          |
                                      +------------+-------------+
                                                   |
                                                   v
                                          +------------------+
                                          | Ollama / Mistral |
                                          +--------+---------+
                                                   |
                                                   v
                                     +-------------------------------+
                                     | AiGenerationContext           |
                                     | (Runtime AI Generation State) |
                                     +---------------+---------------+
                                                     |
                                                     v
                                  +--------------------------------------+
                                  | AiSummaryPersistenceService          |
                                  +------------------+-------------------+
                                                     |
                                                     v
                                         +-------------------------+
                                         | AiSummaryRepository     |
                                         +-----------+-------------+
                                                     |
                                                     |
                         +---------------------------+---------------------------+
                         |                                                       |
                         v                                                       v
              +-------------------------+                           +-------------------------+
              | EconomicIndicator       |<------------------------->| AiSummary               |
              | (Master Data)           |      One-to-Many          | (AI Knowledge)          |
              +-------------------------+                           +-------------------------+
                                                     |
                                                     v
                                         +-------------------------+
                                         | PostgreSQL              |
                                         | Schema:                 |
                                         | economic_intelligence   |
                                         +-------------------------+

Development Profile Only
------------------------

ApplicationVerificationRunner
          |
          v
Verifies:
• EconomicIndicator availability
• Prompt generation
• Mistral connectivity
• AI summary persistence
• Entity relationships
• End-to-end workflow
```

## Running the Project

### Prerequisites

- Java 17+
- Maven
- PostgreSQL
- Ollama/Mistral

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
- [x] Create JPA entities
- [x] Implement repositories
- [x] Build com.td.aieconomics.service layer
- [ ] Develop REST controllers
- [ ] Integrate AI summarization
- [ ] Containerize with Docker
- [ ] Deploy to Kubernetes
- [ ] Configure monitoring and logging

---

**Status:** Early development (foundation complete)

## Final Architecture Resembles:
```
                               Internet
                                   │
                                   ▼
                           REST API Gateway
                                   │
                    ┌──────────────┴──────────────┐
                    ▼                             ▼
          Authentication Service        Economic API Service
                    │                             │
                    ▼                             ▼
               Spring Security           Business Services
                    │                             │
        ┌───────────┼─────────────────────────────┼──────────────┐
        ▼           ▼                             ▼              ▼
   User Service  AI Service              Indicator Service  News Service
        │           │                             │              │
        └───────────┼─────────────────────────────┴──────────────┘
                    ▼
              PostgreSQL
        (economic_platform)
                    │
        economic_intelligence schema
                    │
        ├── users
        ├── economic_indicator
        ├── ai_summary
        ├── news_article
        ├── watchlist
        ├── audit_log
        └── ...
                    │
        ┌───────────┴────────────┐
        ▼                        ▼
     Redis Cache            Ollama (Local LLM)
                                     │
                                     ▼
                          AI Economic Analysis
                    │
                    ▼
          Prometheus / Grafana / Elastic
                    │
                    ▼
          Docker → Kubernetes → Cloud
```