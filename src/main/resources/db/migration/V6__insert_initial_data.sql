INSERT INTO artists (name, artist_type)
VALUES
    ('Serj Tankian', 'SOLO'),
    ('Mike Shinoda', 'SOLO'),
    ('Michel Teló', 'SOLO'),
    ('Guns N'' Roses', 'BAND');

INSERT INTO albums (title, release_year, cover_image_key)
VALUES
    ('Elect the Dead', 2007, NULL),
    ('Post Traumatic', 2018, NULL),
    ('Aí se eu te pego', 2011, NULL),
    ('Appetite for Destruction', 1987, NULL);

INSERT INTO artist_album (artist_id, album_id)
VALUES ((SELECT id FROM artists WHERE name = 'Serj Tankian'),
        (SELECT id FROM albums WHERE title = 'Elect the Dead'));

INSERT INTO artist_album (artist_id, album_id)
VALUES ((SELECT id FROM artists WHERE name = 'Mike Shinoda'),
        (SELECT id FROM albums WHERE title = 'Post Traumatic'));

INSERT INTO artist_album (artist_id, album_id)
VALUES ((SELECT id FROM artists WHERE name = 'Michel Teló'),
        (SELECT id FROM albums WHERE title = 'Aí se eu te pego'));

INSERT INTO artist_album (artist_id, album_id)
VALUES ((SELECT id FROM artists WHERE name = 'Guns N'' Roses'),
        (SELECT id FROM albums WHERE title = 'Appetite for Destruction'));
