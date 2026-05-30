\connect repomultilingua_agt;

ALTER TABLE empresa
    ADD COLUMN IF NOT EXISTS configuracao_cloud VARCHAR(120) DEFAULT 'AWS_S3';
