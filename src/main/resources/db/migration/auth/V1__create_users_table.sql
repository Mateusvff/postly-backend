CREATE TABLE IF NOT EXISTS tb_user (
    id                 BIGINT                  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email              VARCHAR(255)            NOT NULL,
    password_hash      VARCHAR(72)             NOT NULL,
    role               VARCHAR(20)             NOT NULL,
    created            TIMESTAMPTZ             NOT NULL DEFAULT NOW(),
    deleted            TIMESTAMPTZ             NULL,

    CONSTRAINT         uk_users_email          UNIQUE (email),
    CONSTRAINT         chk_users_role          CHECK (role IN ('ADMIN', 'USER')),
    CONSTRAINT         chk_users_deleted       CHECK (deleted IS NULL OR deleted > created)
);
