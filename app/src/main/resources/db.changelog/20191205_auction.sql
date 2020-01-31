CREATE TABLE category
(
    id BIGSERIAL NOT NULL,
    name varchar NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE section
(
    id BIGSERIAL NOT NULL,
    name varchar NOT NULL,

    category_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE auction
(
    id BIGSERIAL NOT NULL,
    title VARCHAR NOT NULL,
    description VARCHAR,
    price numeric NOT NULL,

    owner_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES profile (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE photo
(
    id BIGSERIAL NOT NULL,
    link varchar NOT NULL,

    auction_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (auction_id) REFERENCES auction (id)
);

CREATE TABLE parameter
(
    id BIGSERIAL NOT NULL,
    name VARCHAR NOT NULL,

    PRIMARY KEY (id)
);


CREATE TABLE auctionParameter
(
    value VARCHAR NOT NULL,

    auction_id BIGINT NOT NULL,
    parameter_id BIGINT NOT NULL,

    PRIMARY KEY (auction_id, parameter_id),
    FOREIGN KEY (auction_id) REFERENCES auction (id),
    FOREIGN KEY (parameter_id) REFERENCES parameter (id)
);