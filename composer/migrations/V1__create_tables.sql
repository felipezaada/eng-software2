CREATE TABLE tb_state (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    abbreviation VARCHAR(255) NOT NULL
);

CREATE TABLE tb_city (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    state_id UUID NOT NULL,
    CONSTRAINT fk_city_state FOREIGN KEY (state_id) REFERENCES tb_state (id) ON DELETE CASCADE
);

CREATE TABLE tb_client (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city_id UUID NOT NULL,
    active BOOLEAN NOT NULL,
    birthday TIMESTAMP NOT NULL,
    CONSTRAINT fk_client_city FOREIGN KEY (city_id) REFERENCES tb_city (id) ON DELETE CASCADE
);

CREATE TABLE tb_child (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    client_id UUID NOT NULL,
    CONSTRAINT fk_child_client FOREIGN KEY (client_id) REFERENCES tb_client (id) ON DELETE CASCADE
);


CREATE TABLE tb_users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL
);
