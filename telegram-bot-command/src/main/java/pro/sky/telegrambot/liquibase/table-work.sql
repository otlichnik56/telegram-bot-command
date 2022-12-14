-- liquibase formatted sql

-- changeset nurkatovich:1

-- создание таблиц, пока не уверен, что это окончательный вариант

CREATE TABLE person(
    id              INTEGER PRIMARY KEY,
    chat_id         INTEGER,
    number_phone    TEXT,
    full_name       TEXT,
    status          BOOLEAN
);

CREATE TABLE volunteer(
    id              INTEGER PRIMARY KEY,
    chat_id         INTEGER,
    number_phone    TEXT,
    full_name       TEXT,
    status          BOOLEAN
);

CREATE TABLE report(
    id              INTEGER PRIMARY KEY,
    chat_id         INTEGER,
    message         TEXT
);

