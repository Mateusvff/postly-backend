ALTER TABLE tb_ig_reference
    ADD COLUMN creator_profile_id BIGINT NOT NULL;

ALTER TABLE tb_ig_reference
    ADD CONSTRAINT fk_creator_profile_id FOREIGN KEY (creator_profile_id) REFERENCES tb_creator_profile(id);

ALTER TABLE tb_ig_reference
    DROP CONSTRAINT uk_username;

ALTER TABLE tb_ig_reference
    ADD CONSTRAINT uk_profile_username UNIQUE (creator_profile_id, username);