DROP DATABASE IF EXISTS agt_facturacao;
CREATE DATABASE agt_facturacao;

\connect agt_facturacao;

CREATE TABLE tenant (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(64) UNIQUE NOT NULL,
    nome VARCHAR(200) NOT NULL,
    idioma_padrao VARCHAR(5) NOT NULL DEFAULT 'pt',
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE utilizador (
    id BIGSERIAL PRIMARY KEY,
    tenant_id BIGINT NOT NULL REFERENCES tenant(id),
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    perfil VARCHAR(20) NOT NULL CHECK (perfil IN ('ADMINISTRADOR','OPERADOR','ADMINISTRADOR_CRM','PARCEIRO')),
    password_hash VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (tenant_id, email)
);

CREATE TABLE cliente (
    id BIGSERIAL PRIMARY KEY,
    tenant_id BIGINT NOT NULL REFERENCES tenant(id),
    nome VARCHAR(200) NOT NULL,
    nif VARCHAR(50) NOT NULL,
    email VARCHAR(200),
    telefone VARCHAR(50),
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (tenant_id, nif)
);

CREATE TABLE fornecedor (
    id BIGSERIAL PRIMARY KEY,
    tenant_id BIGINT NOT NULL REFERENCES tenant(id),
    nome VARCHAR(200) NOT NULL,
    nif VARCHAR(50) NOT NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (tenant_id, nif)
);

CREATE TABLE inventario (
    id BIGSERIAL PRIMARY KEY,
    tenant_id BIGINT NOT NULL REFERENCES tenant(id),
    referencia VARCHAR(80) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    preco_unitario NUMERIC(15,2) NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 0,
    UNIQUE (tenant_id, referencia)
);

CREATE TABLE imposto (
    id BIGSERIAL PRIMARY KEY,
    tenant_id BIGINT NOT NULL REFERENCES tenant(id),
    nome VARCHAR(100) NOT NULL,
    taxa NUMERIC(5,2) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE factura (
    id BIGSERIAL PRIMARY KEY,
    tenant_id BIGINT NOT NULL REFERENCES tenant(id),
    cliente_id BIGINT NOT NULL REFERENCES cliente(id),
    numero VARCHAR(64) NOT NULL,
    data_emissao DATE NOT NULL,
    idioma VARCHAR(5) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    subtotal NUMERIC(15,2) NOT NULL,
    total_imposto NUMERIC(15,2) NOT NULL,
    total NUMERIC(15,2) NOT NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (tenant_id, numero)
);

CREATE TABLE item_factura (
    id BIGSERIAL PRIMARY KEY,
    factura_id BIGINT NOT NULL REFERENCES factura(id) ON DELETE CASCADE,
    descricao VARCHAR(255) NOT NULL,
    quantidade NUMERIC(10,2) NOT NULL,
    preco_unitario NUMERIC(15,2) NOT NULL,
    taxa_imposto NUMERIC(5,2) NOT NULL,
    total_linha NUMERIC(15,2) NOT NULL
);

CREATE TABLE auditoria (
    id BIGSERIAL PRIMARY KEY,
    tenant_id BIGINT NOT NULL REFERENCES tenant(id),
    entidade VARCHAR(100) NOT NULL,
    acao VARCHAR(50) NOT NULL,
    utilizador VARCHAR(200) NOT NULL,
    dados JSONB,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE parceiro_crm (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(200) UNIQUE NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE licenca (
    id BIGSERIAL PRIMARY KEY,
    tenant_id BIGINT NOT NULL REFERENCES tenant(id),
    parceiro_id BIGINT REFERENCES parceiro_crm(id),
    plano VARCHAR(100) NOT NULL,
    validade DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    limite_facturas_dia INTEGER NOT NULL DEFAULT 1000,
    chave VARCHAR(128) UNIQUE NOT NULL
);

CREATE TABLE backup_execucao (
    id BIGSERIAL PRIMARY KEY,
    tenant_id BIGINT REFERENCES tenant(id),
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('INCREMENTAL','COMPLETO')),
    destino_cloud VARCHAR(255) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    executado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_factura_tenant_data ON factura(tenant_id, data_emissao);
CREATE INDEX idx_auditoria_tenant_data ON auditoria(tenant_id, criado_em);

CREATE VIEW vw_relatorio_vendas AS
SELECT
    f.tenant_id,
    f.data_emissao,
    COUNT(*) AS total_facturas,
    SUM(f.total) AS valor_total
FROM factura f
GROUP BY f.tenant_id, f.data_emissao;

CREATE VIEW vw_legenda_factura AS
SELECT 'pt' AS idioma, 'Cliente' AS cliente, 'Número' AS numero, 'Data' AS data, 'Total' AS total
UNION ALL SELECT 'en', 'Customer', 'Number', 'Date', 'Total'
UNION ALL SELECT 'fr', 'Client', 'Numéro', 'Date', 'Total'
UNION ALL SELECT 'zh', '客户', '号码', '日期', '总计';
