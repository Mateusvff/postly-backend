CREATE TABLE IF NOT EXISTS tb_meta_token (
    id                              BIGINT                  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    page_access_token               TEXT                    NOT NULL,
    ig_user_id                      VARCHAR(50)             NOT NULL,
    created_at                      TIMESTAMPTZ             NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMPTZ             NULL,

    CONSTRAINT uk_meta_token_ig_user_id UNIQUE (ig_user_id)
);