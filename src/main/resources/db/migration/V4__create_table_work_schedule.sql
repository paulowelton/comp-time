CREATE TABLE work_schedules(
   id SERIAL PRIMARY KEY NOT NULL,
   name TEXT NOT NULL,
   start_time TIME NOT NULL,
   end_time TIME NOT NULL,
   break_seconds INTEGER NOT NULL,
   expected_seconds INTEGER NOT NULL,
   active BOOLEAN NOT NULL DEFAULT TRUE
);