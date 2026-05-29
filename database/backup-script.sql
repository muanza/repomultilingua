\connect agt_facturacao;

INSERT INTO backup_execucao (tenant_id, tipo, destino_cloud, estado)
SELECT t.id, 'INCREMENTAL', 's3://agt-backups/' || t.codigo, 'AGENDADO'
FROM tenant t
WHERE t.activo = TRUE;

-- Exemplo de estratégia incremental (baseada em data de auditoria)
SELECT tenant_id, entidade, acao, criado_em
FROM auditoria
WHERE criado_em >= NOW() - INTERVAL '1 day'
ORDER BY criado_em;
