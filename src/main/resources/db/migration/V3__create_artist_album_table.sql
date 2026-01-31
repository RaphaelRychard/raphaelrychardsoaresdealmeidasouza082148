CREATE TABLE artist_album
(
    artist_id BIGINT NOT NULL REFERENCES artists (id) ON DELETE CASCADE,
    album_id  BIGINT NOT NULL REFERENCES albums (id) ON DELETE CASCADE,
    PRIMARY KEY (artist_id, album_id)
);
CREATE INDEX idx_artist_album_artist ON artist_album (artist_id);
CREATE INDEX idx_artist_album_album ON artist_album (album_id);
