\connect agt_facturacao;

INSERT INTO tenant (codigo, nome, idioma_padrao) VALUES
('TENANT-001', 'Tenant Demonstração', 'pt');

INSERT INTO utilizador (tenant_id, nome, email, perfil, password_hash)
VALUES
(1, 'Administrador Facturação', 'admin@tenant.local', 'ADMINISTRADOR', 'hash-admin'),
(1, 'Operador Facturação', 'operador@tenant.local', 'OPERADOR', 'hash-operador');

INSERT INTO parceiro_crm (nome, email) VALUES
('Parceiro Principal', 'parceiro@crm.local');

INSERT INTO licenca (tenant_id, parceiro_id, plano, validade, estado, limite_facturas_dia, chave)
VALUES
(1, 1, 'AGT-PRO', CURRENT_DATE + INTERVAL '365 days', 'ATIVA', 1000, 'LIC-AGT-001');
