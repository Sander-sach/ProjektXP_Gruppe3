
-- =========================================
-- BIOPROJECT - SEED DATA
-- =========================================

-- THEATERS

INSERT INTO theater (theater_id, name)
VALUES
    (1, 'Theater 1'),
    (2, 'Theater 2');


-- =========================================
-- SEATS
-- 40 seats per theater
-- Rows A-E, seats 1-8
-- =========================================

INSERT INTO seat (row_label, seat_number, theater_id)
VALUES

-- Theater 1
('A',1,1),('A',2,1),('A',3,1),('A',4,1),
('A',5,1),('A',6,1),('A',7,1),('A',8,1),

('B',1,1),('B',2,1),('B',3,1),('B',4,1),
('B',5,1),('B',6,1),('B',7,1),('B',8,1),

('C',1,1),('C',2,1),('C',3,1),('C',4,1),
('C',5,1),('C',6,1),('C',7,1),('C',8,1),

('D',1,1),('D',2,1),('D',3,1),('D',4,1),
('D',5,1),('D',6,1),('D',7,1),('D',8,1),

('E',1,1),('E',2,1),('E',3,1),('E',4,1),
('E',5,1),('E',6,1),('E',7,1),('E',8,1),

-- Theater 2
('A',1,2),('A',2,2),('A',3,2),('A',4,2),
('A',5,2),('A',6,2),('A',7,2),('A',8,2),

('B',1,2),('B',2,2),('B',3,2),('B',4,2),
('B',5,2),('B',6,2),('B',7,2),('B',8,2),

('C',1,2),('C',2,2),('C',3,2),('C',4,2),
('C',5,2),('C',6,2),('C',7,2),('C',8,2),

('D',1,2),('D',2,2),('D',3,2),('D',4,2),
('D',5,2),('D',6,2),('D',7,2),('D',8,2),

('E',1,2),('E',2,2),('E',3,2),('E',4,2),
('E',5,2),('E',6,2),('E',7,2),('E',8,2);


-- =========================================
-- MOVIES
-- Enum values match your Java enums
-- =========================================

INSERT INTO movie
(movie_id, movie_title, description, genre, age_limit, duration)
VALUES

    (1, 'Interstellar',
     'A team of explorers travels beyond this galaxy.',
     'SCIENCE_FICTION', 'AGE_ELEVEN', 169),

    (2, 'The Dark Knight',
     'Batman faces the Joker in Gotham City.',
     'ACTION', 'AGE_FIFTEEN', 152),

    (3, 'Inception',
     'A thief enters dreams to steal information.',
     'SCIENCE_FICTION', 'AGE_ELEVEN', 148),

    (4, 'The Conjuring',
     'Paranormal investigators examine a haunted farmhouse.',
     'HORROR', 'AGE_FIFTEEN', 112),

    (5, 'The Shawshank Redemption',
     'Two prisoners form a lasting friendship.',
     'DRAMA', 'AGE_FIFTEEN', 142),

    (6, 'Toy Story',
     'A group of toys comes to life when humans are absent.',
     'ANIMATION', 'AGE_SEVEN', 81),

    (7, 'The Hangover',
     'Three friends search for their missing friend in Las Vegas.',
     'COMEDY', 'AGE_ELEVEN', 100),

    (8, 'The Matrix',
     'A hacker discovers the truth about his reality.',
     'SCIENCE_FICTION', 'AGE_FIFTEEN', 136);


-- =========================================
-- SCREENINGS
-- Multiple dates and both theaters
-- =========================================

INSERT INTO screening
(movie_id, theater_id, start_time)
VALUES

-- September 27
(1, 1, '2026-09-27 12:00:00'),
(2, 2, '2026-09-27 12:30:00'),
(3, 1, '2026-09-27 15:30:00'),
(4, 2, '2026-09-27 16:00:00'),
(5, 1, '2026-09-27 19:00:00'),
(8, 2, '2026-09-27 19:30:00'),

-- September 28
(6, 1, '2026-09-28 10:00:00'),
(7, 2, '2026-09-28 11:00:00'),
(1, 1, '2026-09-28 13:00:00'),
(3, 2, '2026-09-28 14:00:00'),
(2, 1, '2026-09-28 17:00:00'),
(4, 2, '2026-09-28 18:00:00'),
(8, 1, '2026-09-28 20:00:00'),

-- September 29
(5, 1, '2026-09-29 11:00:00'),
(6, 2, '2026-09-29 11:30:00'),
(7, 1, '2026-09-29 14:00:00'),
(1, 2, '2026-09-29 14:30:00'),
(3, 1, '2026-09-29 17:00:00'),
(2, 2, '2026-09-29 18:30:00'),
(4, 1, '2026-09-29 20:00:00'),

-- September 30
(8, 1, '2026-09-30 12:00:00'),
(5, 2, '2026-09-30 12:30:00'),
(6, 1, '2026-09-30 15:00:00'),
(7, 2, '2026-09-30 16:00:00'),
(1, 1, '2026-09-30 18:00:00'),
(3, 2, '2026-09-30 19:00:00'),

-- October 1
(2, 1, '2026-10-01 12:00:00'),
(4, 2, '2026-10-01 12:30:00'),
(8, 1, '2026-10-01 15:00:00'),
(6, 2, '2026-10-01 15:30:00'),
(5, 1, '2026-10-01 18:00:00'),
(7, 2, '2026-10-01 19:00:00');
