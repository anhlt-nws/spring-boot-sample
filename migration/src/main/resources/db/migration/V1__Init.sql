CREATE TABLE auth
(
    id bigserial NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    role text NOT NULL,
    status text NOT NULL,
    last_login_time timestamp without time zone,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    PRIMARY KEY (id),
    CONSTRAINT uq_username UNIQUE (username)
);

CREATE TABLE users
(
    id bigserial NOT NULL,
    fullname text NOT NULL,
    email text NOT NULL,
    status text NOT NULL,
    auth_id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    PRIMARY KEY (id),
    CONSTRAINT fk_auth_id FOREIGN KEY (auth_id)
        REFERENCES auth (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

-- seed
INSERT INTO auth (id, username, password, role, status, created_at, updated_at) VALUES (1, 'admin', '$2b$10$kXGT2pbyoEU57dHBZ2maHO/JyCN07B65E1XrKIPLxqZ0403audcS.', 'ROLE_ADMIN', 'ACTIVE', now(), now());
INSERT INTO users (fullname, email, status, auth_id, created_at, updated_at) VALUES ('Administrator', 'email@test.com', 'ACTIVE', 1, now(), now());