-- liquibase formatted sql

-- changeset nurkatovich:1


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
