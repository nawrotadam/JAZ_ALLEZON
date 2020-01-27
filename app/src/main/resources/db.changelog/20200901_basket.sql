CREATE TABLE basket
(
    id BIGSERIAL NOT NULL,
    creationDate TIME NOT NULL,


    owner_id BIGINT NOT NULL,
    auction_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES profile (id),
    FOREIGN KEY (auction_id) REFERENCES auction (id)
);