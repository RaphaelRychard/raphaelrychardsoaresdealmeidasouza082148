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
│   │   ├── exception/       # Tratamento global de exceções
│   │   ├── infra/           # MinIO e WebSocket
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

* [ ] CRUD de Artistas
* [ ] CRUD de Álbuns
* [ ] Associação N:N entre Artistas e Álbuns
* [ ] Upload de arquivos via MinIO
* [ ] Autenticação JWT
* [ ] WebSocket para notificações de novos álbuns
* [ ] Sincronização de Regionais
* [ ] Rate Limiting (10 requisições/minuto)
* [ ] Testes unitários e de integração
* [ ] Documentação Swagger/OpenAPI
* [ ] Health Checks com Spring Boot Actuator

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

## Testes

* Testes unitários com JUnit 5 e Mockito
* Cobertura mínima esperada: 70%
* Comando:

```bash
mvn test
```

---

## Deploy

* Docker multi-stage já empacota a aplicação com todas as dependências
* Pode ser deployado em qualquer orquestrador (Kubernetes, ECS, etc.)
* Apontar para PostgreSQL externo e MinIO em produção
