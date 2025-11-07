CREATE TABLE IF NOT EXISTS tb_creator_profile(
    id                      BIGINT                                  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id                 BIGINT                                  NOT NULL,
    ig_username             VARCHAR(30)                             NOT NULL,
    niche                   VARCHAR(50)                             NOT NULL,
    main_goal               VARCHAR(50)                             NOT NULL,
    post_per_week           INT                                     NOT NULL,
    ig_references           JSONB                                   NOT NULL,
    status                  VARCHAR(20)                             NOT NULL DEFAULT 'DRAFT',
    comments                VARCHAR(255)                            NULL,
    created_at              TIMESTAMP                               NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP                               NULL,

    CONSTRAINT              fk_creator_profile_user_id              FOREIGN KEY (user_id) REFERENCES tb_user (id),
    CONSTRAINT              uk_creator_profile_ig_username          UNIQUE (ig_username),
    CONSTRAINT              chk_creator_profile_post_per_week       CHECK (post_per_week BETWEEN 1 AND 14),
    CONSTRAINT              chk_creator_profile_status              CHECK (status IN ('DRAFT', 'FILLED', 'UPDATED')),
    CONSTRAINT              chk_creator_profile_created_at          CHECK (created_at <= NOW()),
    CONSTRAINT              chk_creator_profile_updated_at          CHECK (updated_at > created_at OR updated_at IS NULL)
)