\connect repomultilingua_agt;

INSERT INTO empresa (id, nome, nif, email) VALUES
    (1, 'AGT Demo', '500000001', 'demo@agt.ao');

INSERT INTO cliente (empresa_id, nome, nif, email) VALUES
    (1, 'Cliente Exemplo', '900000001', 'cliente@demo.ao');

INSERT INTO configuracao_imposto (empresa_id, tipo_imposto, percentual, data_vigencia) VALUES
    (1, 'IVA', 14.00, CURRENT_DATE);

INSERT INTO produto (empresa_id, codigo, descricao, preco_venda, imposto_id, ativo) VALUES
    (1, 'LIC-001', 'Licença AGT Standard', 10000.00, 1, TRUE);

INSERT INTO parceiro (id, nome, email, telefone) VALUES
    (1, 'Parceiro Principal', 'parceiro@agt.ao', '+244900000001');

INSERT INTO tenant_licenca (parceiro_id, empresa_id, data_inicio, data_expiracao, numero_usuarios, status_licenca, limite_facturas) VALUES
    (1, 1, CURRENT_DATE, CURRENT_DATE + INTERVAL '365 days', 10, 'ACTIVA', 1000);

