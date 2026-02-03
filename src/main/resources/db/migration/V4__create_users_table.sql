CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    login    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(150) NOT NULL
);
