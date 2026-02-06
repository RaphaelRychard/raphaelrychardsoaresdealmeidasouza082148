Abaixo está um **README completo e corrigido**, com a sua **justificativa ajustada** (incluindo **curso** e **deslocamento/viagem**) e também com **pequenas correções técnicas** para “funcionar” melhor (ex.: endpoint do álbum, paths consistentes, e comandos Docker). Eu **não mudei o sentido** do que você escreveu — só deixei mais claro, correto e apresentável.

---

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
* **Testes:** JUnit 5 + Mockito *(previsto, pendente)*
* **Documentação:** Swagger UI (springdoc-openapi)
* **Deploy e containerização:** Docker / Docker Compose
* **WebSocket:** Notificações em tempo real de novos álbuns
* **Rate Limiting:** 10 requisições/minuto por usuário

---

## Estrutura do Projeto

```text
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
│   │   │   ├── health/      # Health checks (DB/MinIO/WebSocket)
│   │   │   └── websocket/   # Infra de WebSocket
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

> Observação: Controllers estão versionados em `/api/v1/**` (ex.: `modules/*/controller/v1`).

---

## Funcionalidades Implementadas

* [x] CRUD de Artistas
* [x] CRUD de Álbuns
* [x] Associação N:N entre Artistas e Álbuns
* [x] Upload de arquivos via MinIO
* [x] Autenticação JWT
* [x] WebSocket para notificações de novos álbuns
* [x] Sincronização de Regionais
* [x] Rate Limiting (10 requisições/minuto)
* [ ] Testes unitários e de integração *(pendente)*
* [x] Documentação Swagger/OpenAPI
* [x] Health Checks com Spring Boot Actuator (liveness/readiness)

---

## Execução Local (Docker)

### Pré-requisitos

* **Docker** e **Docker Compose** instalados
* Não é necessário Java ou Maven localmente (execução via containers)

### Subir ambiente

```bash
# Clonar repositório
git clone https://github.com/seu-usuario/artist-album-api.git
cd artist-album-api

# Iniciar containers (API + PostgreSQL + MinIO)
docker-compose up --build
```

### Parar ambiente

```bash
docker-compose down
```

### Ver logs da API

```bash
docker-compose logs -f app
```

### URLs principais

* API: `http://localhost:8080`
* Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## WebSocket (notificação de novos álbuns)

A API expõe um endpoint **WebSocket STOMP** para notificar clientes quando um novo álbum é cadastrado.

### Endpoint e tópico

* **Endpoint:** `ws://localhost:8080/ws`
* **Tópico (subscribe):** `/topic/albums`

Sempre que um álbum é criado via **POST `/api/v1/albums`**, uma mensagem é enviada para `/topic/albums`.

### Configuração de origins

Para permitir conexão do seu front/HTML local, inclua a origem em:

* `app.allowed-origins` (WebSocket e HTTP REST)

---

## Health Checks (Liveness e Readiness)

A aplicação utiliza **Spring Boot Actuator** para expor health checks no padrão de produção.

### Endpoints expostos

* `GET /actuator/health`
* `GET /actuator/health/liveness`
* `GET /actuator/health/readiness`

Apenas endpoints de **health** são expostos publicamente. Os demais endpoints do Actuator permanecem protegidos.

### O que é verificado

**Liveness (saúde do processo):**

* Verifica se a aplicação está viva e respondendo
* Não depende de serviços externos

**Readiness (pronta para tráfego):**

* Conectividade com **PostgreSQL**
* Conectividade com **MinIO**
* Disponibilidade do **WebSocket** (camada da aplicação)

Se qualquer dependência falhar, o readiness retorna **DOWN (503)** e a aplicação deve ser retirada do tráfego.

### Exemplos de teste

```bash
curl -i http://localhost:8080/actuator/health
curl -i http://localhost:8080/actuator/health/readiness
curl -i http://localhost:8080/actuator/health/liveness
```

**Comportamento esperado:**

* PostgreSQL ou MinIO fora → `readiness = DOWN`, `liveness = UP`
* Todos os serviços ativos → `readiness = UP`, `liveness = UP`

---

## Deploy

* Docker multi-stage empacota a aplicação com as dependências
* Pode ser deployado em orquestradores (Kubernetes, ECS etc.)
* Em produção, recomenda-se PostgreSQL e MinIO gerenciados/externos e variáveis de ambiente para segredos

---

## Itens não implementados (escopo priorizado)

- **Testes unitários e de integração:** não foram incluídos nesta entrega.
    - **Motivo:** priorizei a implementação completa das funcionalidades e requisitos sênior (CRUD, N:N, Flyway, JWT 5 min, Rate Limit 10/min, MinIO, WebSocket e Health Checks) para garantir o funcionamento end-to-end dentro do prazo.
    - **Como evoluir:** adicionar testes com **JUnit 5 + Mockito** para services/useCases e testes de integração com **@SpringBootTest/Testcontainers** (PostgreSQL/MinIO) cobrindo os principais fluxos e cenários de erro.

- **Refresh token:** não foi incluído nesta entrega.
    - **Motivo:** o requisito principal era autenticação JWT com expiração de 5 minutos; para não comprometer os demais requisitos, mantive o escopo no fluxo de login + validação.
    - **Como evoluir:** criar `POST /api/v1/auth/refresh` com rotação/revogação de refresh tokens e validações de segurança.