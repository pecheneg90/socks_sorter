-- liquibase formatted sql

-- changeset pecheneg:1

CREATE TABLE IF NOT EXISTS socks
(
    id          BIGINT PRIMARY KEY,
    color       VARCHAR(100) NOT NULL,
    cotton_part INTEGER      NOT NULL,
    quantity    BIGINT       NOT NULL
);

create sequence public.hibernate_sequence;