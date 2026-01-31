CREATE TABLE albums
(
    id              BIGSERIAL PRIMARY KEY,
    title           VARCHAR(300) NOT NULL,
    release_year    INTEGER,
    cover_image_key VARCHAR(500), -- Chave do MinIO
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_album_title ON albums (title);
