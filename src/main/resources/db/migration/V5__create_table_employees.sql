CREATE TABLE employees(
    id SERIAL PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    sector_id INTEGER NOT NULL REFERENCES sectors(id),
    job_position_id INTEGER NOT NULL REFERENCES job_positions(id),
    active BOOLEAN NOT NULL DEFAULT TRUE
);