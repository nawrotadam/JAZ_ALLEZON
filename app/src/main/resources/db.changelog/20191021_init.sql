CREATE SEQUENCE hibernate_sequence;


CREATE TABLE profile
(
    id BIGSERIAL NOT NULL,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    firstname VARCHAR NOT NULL,
    lastname VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    dateofbirth VARCHAR NOT NULL,

    PRIMARY KEY (id)
);
