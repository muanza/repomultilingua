\connect repomultilingua_agt;

CREATE TABLE IF NOT EXISTS backup_execucao (
    id BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
    tipo_backup VARCHAR(20) NOT NULL,
    destino_cloud VARCHAR(255) NOT NULL,
    checksum_arquivo VARCHAR(128),
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_backup_execucao_empresa ON backup_execucao (empresa_id, criado_em DESC);

