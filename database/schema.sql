DROP DATABASE IF EXISTS repomultilingua_agt;
CREATE DATABASE repomultilingua_agt WITH ENCODING 'UTF8';
\connect repomultilingua_agt;

CREATE TABLE empresa (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    nif VARCHAR(30) NOT NULL UNIQUE,
    endereco VARCHAR(255),
    telefone VARCHAR(30),
    email VARCHAR(120),
    idioma_padrao VARCHAR(5) NOT NULL DEFAULT 'pt',
    zona_horaria VARCHAR(80) NOT NULL DEFAULT 'Africa/Luanda',
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cliente (
    id BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
    nome VARCHAR(200) NOT NULL,
    nif VARCHAR(30),
    endereco VARCHAR(255),
    telefone VARCHAR(30),
    email VARCHAR(120),
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE fornecedor (
    id BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
    nome VARCHAR(200) NOT NULL,
    nif VARCHAR(30),
    endereco VARCHAR(255),
    telefone VARCHAR(30),
    email VARCHAR(120),
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE configuracao_imposto (
    id BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
    tipo_imposto VARCHAR(50) NOT NULL,
    percentual NUMERIC(10,2) NOT NULL,
    data_vigencia DATE NOT NULL
);

CREATE TABLE produto (
    id BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
    codigo VARCHAR(40) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    preco_venda NUMERIC(18,2) NOT NULL,
    imposto_id BIGINT REFERENCES configuracao_imposto(id),
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE fatura (
    id BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
    cliente_id BIGINT NOT NULL REFERENCES cliente(id),
    numero_documento VARCHAR(60) NOT NULL,
    data_emissao DATE NOT NULL,
    data_vencimento DATE,
    valor_total NUMERIC(18,2) NOT NULL DEFAULT 0,
    status VARCHAR(30) NOT NULL,
    idioma_documento VARCHAR(5) NOT NULL DEFAULT 'pt',
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_fatura_documento UNIQUE (empresa_id, numero_documento)
);

CREATE TABLE item_fatura (
    id BIGSERIAL PRIMARY KEY,
    fatura_id BIGINT NOT NULL REFERENCES fatura(id) ON DELETE CASCADE,
    produto_id BIGINT NOT NULL REFERENCES produto(id),
    quantidade NUMERIC(18,2) NOT NULL,
    preco_unitario NUMERIC(18,2) NOT NULL,
    percentual_desconto NUMERIC(10,2) NOT NULL DEFAULT 0,
    valor_total_liquido NUMERIC(18,2) NOT NULL,
    valor_imposto NUMERIC(18,2) NOT NULL
);

CREATE TABLE pagamento (
    id BIGSERIAL PRIMARY KEY,
    fatura_id BIGINT NOT NULL REFERENCES fatura(id) ON DELETE CASCADE,
    valor NUMERIC(18,2) NOT NULL,
    data_pagamento DATE NOT NULL,
    metodo_pagamento VARCHAR(40) NOT NULL
);

CREATE TABLE backup_registro (
    id BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
    data_backup TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tamanho_mb NUMERIC(12,2) NOT NULL DEFAULT 0,
    localizacao_cloud VARCHAR(255) NOT NULL,
    status VARCHAR(30) NOT NULL,
    data_restauracao TIMESTAMP
);

CREATE TABLE parceiro (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(120) NOT NULL,
    telefone VARCHAR(30),
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tenant_licenca (
    id BIGSERIAL PRIMARY KEY,
    parceiro_id BIGINT NOT NULL REFERENCES parceiro(id),
    empresa_id BIGINT NOT NULL REFERENCES empresa(id),
    data_inicio DATE NOT NULL,
    data_expiracao DATE NOT NULL,
    numero_usuarios INTEGER NOT NULL,
    status_licenca VARCHAR(30) NOT NULL,
    limite_facturas INTEGER NOT NULL
);

CREATE TABLE auditoria_acesso (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT,
    tenant_id BIGINT REFERENCES tenant_licenca(id),
    acao VARCHAR(100) NOT NULL,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    endereco_ip VARCHAR(60),
    resultado VARCHAR(40) NOT NULL
);

CREATE INDEX idx_cliente_empresa ON cliente(empresa_id);
CREATE INDEX idx_produto_empresa ON produto(empresa_id);
CREATE INDEX idx_fatura_empresa_data ON fatura(empresa_id, data_emissao);
CREATE INDEX idx_item_fatura_fatura ON item_fatura(fatura_id);
CREATE INDEX idx_tenant_licenca_parceiro ON tenant_licenca(parceiro_id);
CREATE INDEX idx_backup_registro_empresa ON backup_registro(empresa_id, data_backup DESC);

CREATE OR REPLACE FUNCTION actualizar_total_fatura() RETURNS TRIGGER AS $$
BEGIN
    UPDATE fatura
    SET valor_total = (
        SELECT COALESCE(SUM(valor_total_liquido + valor_imposto), 0)
        FROM item_fatura
        WHERE fatura_id = NEW.fatura_id
    )
    WHERE id = NEW.fatura_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_actualizar_total_fatura
AFTER INSERT OR UPDATE ON item_fatura
FOR EACH ROW EXECUTE FUNCTION actualizar_total_fatura();

CREATE OR REPLACE VIEW vw_relatorio_vendas AS
SELECT f.empresa_id,
       COUNT(f.id) AS total_facturas,
       COALESCE(SUM(f.valor_total), 0) AS valor_total
FROM fatura f
GROUP BY f.empresa_id;

