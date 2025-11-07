CREATE TABLE IF NOT EXISTS numbers(
    id SERIAL PRIMARY KEY,
    value INT NOT NULL
);

INSERT INTO numbers(value) VALUES (5), (10), (15);