CREATE TABLE IF NOT EXISTS tb_ig_media_content (
    id                              BIGINT                  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ig_reference_id                 BIGINT                  NOT NULL,
    caption                         TEXT                    NULL,
    media_type                      TEXT                    NOT NULL,
    media_product_type              TEXT                    NOT NULL,
    media_url                       TEXT                    NOT NULL,
    like_count                      INTEGER                 NOT NULL,
    comments_count                  INTEGER                 NOT NULL,
    created_at                      TIMESTAMPTZ             NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_ig_media_content_reference FOREIGN KEY (ig_reference_id)
    REFERENCES tb_ig_reference (id) ON DELETE CASCADE
);