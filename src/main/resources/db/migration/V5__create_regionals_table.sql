CREATE TABLE regionals
(
    id          BIGSERIAL PRIMARY KEY,
    external_id INTEGER UNIQUE NOT NULL, -- ID do endpoint externo
    name        VARCHAR(200)   NOT NULL,
    active      BOOLEAN   DEFAULT TRUE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_regional_active ON regionals (active);
