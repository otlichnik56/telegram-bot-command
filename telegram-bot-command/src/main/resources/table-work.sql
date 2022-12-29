CREATE TABLE animal
(
    id              INTEGER PRIMARY KEY,
    name            TEXT,
    kind_of_animal  TEXT,
    age             INTEGER,
    invalid         BOOLEAN,
    person_id       INTEGER REFERENCES person(id)
);


CREATE TABLE person
(
    id           INTEGER PRIMARY KEY,
    username     TEXT,
    number_phone TEXT,
    contact_name TEXT,
    status       BOOLEAN,
    start_date   DATE,
    end_date     DATE
);



CREATE TABLE report(
    id              SERIAL PRIMARY KEY,
    username        TEXT,
    message         TEXT,
    photo           OID,
    date_report     DATE
);
