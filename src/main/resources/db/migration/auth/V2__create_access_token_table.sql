CREATE TABLE IF NOT EXISTS tb_access_token (
    id               BIGINT                         GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    token            VARCHAR(255)                   NOT NULL,
    user_id          BIGINT                         NOT NULL,
    expires_at       TIMESTAMPTZ                    NOT NULL,
    created_at       TIMESTAMPTZ                    DEFAULT NOW(),

    CONSTRAINT       fk_access_token_user           FOREIGN KEY (user_id) REFERENCES tb_user(id) ON DELETE CASCADE,
    CONSTRAINT       uk_access_token_token          UNIQUE (token),
    CONSTRAINT       chk_access_token_expires_at    CHECK (expires_at > created_at)
);