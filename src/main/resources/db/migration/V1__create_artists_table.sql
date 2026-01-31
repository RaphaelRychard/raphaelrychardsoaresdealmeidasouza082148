CREATE TABLE artists
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(200) NOT NULL,
    artist_type VARCHAR(20)  NOT NULL, -- SOLO ou BAND
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_artist_name ON artists (name);
CREATE INDEX idx_artist_type ON artists (artist_type);
