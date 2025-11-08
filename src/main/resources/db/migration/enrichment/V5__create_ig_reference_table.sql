CREATE TABLE IF NOT EXISTS tb_ig_reference (
    id                              BIGINT                  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username                        VARCHAR(30)             NOT NULL,
    biography                       VARCHAR(255)            NULL,
    profile_picture_url             VARCHAR(255)            NULL,
    created_at                      TIMESTAMPTZ             NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMPTZ             NULL,

    CONSTRAINT uk_username UNIQUE (username)
);