\connect agt_facturacao;

BEGIN;

INSERT INTO backup_execucao (tenant_id, tipo, destino_cloud, estado)
SELECT t.id, 'INCREMENTAL', 's3://agt-backups/' || t.codigo, 'AGENDADO'
FROM tenant t
WHERE t.activo = TRUE;

UPDATE backup_execucao
SET estado = 'EM_PROCESSAMENTO'
WHERE estado = 'AGENDADO'
  AND executado_em >= NOW() - INTERVAL '5 minutes';

-- Estratégia incremental baseada em alterações auditadas nas últimas 24 horas
SELECT tenant_id, entidade, acao, criado_em
FROM auditoria
WHERE criado_em >= NOW() - INTERVAL '1 day'
ORDER BY criado_em;

COMMIT;
