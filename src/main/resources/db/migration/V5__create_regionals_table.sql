CREATE TABLE regionals
(
    id          BIGSERIAL PRIMARY KEY,
    external_id INTEGER      NOT NULL,
    name        VARCHAR(200) NOT NULL,
    active      BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_regionals_active
    ON regionals (active);

CREATE UNIQUE INDEX ux_regionals_external_id_active_true
    ON regionals (external_id)
    WHERE active = true;