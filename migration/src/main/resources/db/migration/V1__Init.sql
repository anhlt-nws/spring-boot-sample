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

CREATE TABLE "user"
(
    id bigint NOT NULL,
    fullname text NOT NULL,
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
)