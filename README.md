# Projeto Back End Java Sênior - Artist Album API

## Dados de Inscrição

* **Nome:** RAPHAEL RYCHARD SOARES DE ALMEIDA SOUZA
* **Telefone:** (65) 99342-5278
* **Nº de Inscrição:** 16394
* **Vaga:** Back End Java Sênior

API para gerenciar **Artistas** e **Álbuns** (N:N), com armazenamento de arquivos em **MinIO**.

---

## Tecnologias e Arquitetura

* **Backend:** Java 17 / Spring Boot / REST
* **Persistência:** PostgreSQL + Flyway / Spring Data JPA / Hibernate
* **Armazenamento de arquivos:** MinIO
* **Autenticação:** JWT
* **Testes:** JUnit 5 + Mockito
* **Documentação:** Swagger UI (springdoc-openapi)
* **Deploy e containerização:** Docker / Docker Compose
* **WebSocket:** Notificações em tempo real de novos álbuns
* **Rate Limiting:** 10 requisições/minuto por usuário

---

## Estrutura do Projeto

```
artist-album-api/
├── src/
│   ├── main/java/br/gov/mt/seplag/artistalbumapi/
│   │   ├── config/          # Configurações (CORS, Security, Swagger, WebSocket, MinIO)
│   │   ├── modules/
│   │   │   ├── artist/      # CRUD e lógica de artistas
│   │   │   ├── album/       # CRUD e lógica de álbuns
│   │   │   ├── regional/    # Sincronização de regionais
│   │   │   └── auth/        # JWT e autenticação
│   │   ├── security/        # Filtros JWT e rate limiting
│   │   ├── infra/
│   │   │   ├── health/      # Health checks (MinIO, WebSocket)
│   │   │   └── websocket/   # Infra de WebSocket
│   │   ├── providers/       # Providers de infraestrutura (ex: JWT, tokens, serviços técnicos)
│   │   └── ArtistAlbumApiApplication.java
│   └── resources/
│       ├── application.yml
│       └── db/migration/    # Scripts Flyway
├── docker/
│   ├── Dockerfile
│   └── docker-compose.yml
├── README.md
├── pom.xml
└── .gitignore
```

---

## Funcionalidades Implementadas

* [x] CRUD de Artistas
* [x] CRUD de Álbuns
* [x] Associação N:N entre Artistas e Álbuns
* [x] Upload de arquivos via MinIO
* [x] Autenticação JWT
* [x] WebSocket para notificações de novos álbuns
* [ ] Sincronização de Regionais
* [x] Rate Limiting (10 requisições/minuto)
* [ ] Testes unitários e de integração
* [x] Documentação Swagger/OpenAPI
* [x] Health Checks com Spring Boot Actuator

---

## Execução Local

Pré-requisitos: **Docker** e **Docker Compose** instalados. Não é necessário Java ou Maven localmente.

```bash
# Clonar repositório
git clone https://github.com/seu-usuario/artist-album-api.git
cd artist-album-api

# Iniciar containers (API + PostgreSQL + MinIO)
docker-compose up --build

# Parar containers
docker-compose down

# Acompanhar logs do app
docker-compose logs -f app
```

* A aplicação estará disponível em: **[http://localhost:8080](http://localhost:8080)**
* Swagger UI: **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

---

## WebSocket (notificação de novos álbuns)

A API expõe um endpoint **WebSocket STOMP** para notificar clientes quando um novo álbum é cadastrado.

### Endpoint e tópico

* **Endpoint:** `ws://localhost:8080/ws`
* **Tópico (subscribe):** `/topic/albums`

Sempre que um álbum é criado via `POST /api/v1/album`, uma mensagem é enviada para `/topic/albums`.

### Configuração de origins

Para permitir conexão do seu front/HTML local, inclua a origem em:

* `app.allowed-origins` (WebSocket e HTTP REST)

---

## Health Checks (Liveness e Readiness)

A aplicação utiliza **Spring Boot Actuator** para expor health checks no padrão de produção (Kubernetes / Cloud).

### Endpoints expostos

* `GET /actuator/health`
* `GET /actuator/health/liveness`
* `GET /actuator/health/readiness`

Apenas endpoints de **health** são expostos publicamente. Demais endpoints do Actuator permanecem protegidos.

### O que é verificado

**Liveness** (saúde da aplicação):

* Verifica se a aplicação está viva e respondendo
* Não depende de serviços externos
* Usado para evitar restart desnecessário do container

**Readiness** (pronta para receber tráfego):

* Conectividade com **PostgreSQL**
* Conectividade com **MinIO**
* Handshake do **WebSocket**

Se qualquer dependência falhar, o readiness retorna **DOWN (503)** e a aplicação sai do tráfego.

### Exemplos de teste

```bash
# Health geral
curl -i http://localhost:8080/actuator/health

# Readiness (dependências externas)
curl -i http://localhost:8080/actuator/health/readiness

# Liveness (processo da aplicação)
curl -i http://localhost:8080/actuator/health/liveness
```

**Comportamento esperado:**

* PostgreSQL ou MinIO fora → `readiness = DOWN`, `liveness = UP`
* Todos os serviços ativos → `readiness = UP`, `liveness = UP`

---

## Testes

* Testes unitários com JUnit 5 e Mockito
* Cobertura mínima esperada: 70%

```bash
mvn test
```

---

## Deploy

* Docker multi-stage empacota a aplicação com todas as dependências
* Pode ser deployada em qualquer orquestrador (Kubernetes, ECS, etc.)
* Em produção, configurar PostgreSQL e MinIO externos
