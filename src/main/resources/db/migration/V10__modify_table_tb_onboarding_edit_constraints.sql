ALTER TABLE tb_creator_profile
    DROP CONSTRAINT chk_creator_profile_status;

ALTER TABLE tb_creator_profile
    ADD CONSTRAINT chk_creator_profile_status
        CHECK (status IN ('PROCESSING', 'COMPLETED', 'FAILED', 'UPDATED'));
