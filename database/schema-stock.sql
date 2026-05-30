DROP DATABASE IF EXISTS agt_facturacao;
CREATE DATABASE agt_facturacao;
\c agt_facturacao;

CREATE TABLE empresa (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  nif VARCHAR(50) UNIQUE NOT NULL,
  ativa BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE produto (
  id BIGSERIAL PRIMARY KEY,
  empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
  nome VARCHAR(150) NOT NULL,
  codigo_barras VARCHAR(100),
  categoria VARCHAR(100),
  preco_base NUMERIC(18,2) NOT NULL DEFAULT 0
);

CREATE TABLE cliente (
  id BIGSERIAL PRIMARY KEY,
  empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
  nome VARCHAR(150) NOT NULL,
  email VARCHAR(150)
);

CREATE TABLE fatura (
  id BIGSERIAL PRIMARY KEY,
  empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
  cliente_id BIGINT REFERENCES cliente(id),
  numero_serie VARCHAR(30) NOT NULL,
  numero_sequencial BIGINT NOT NULL,
  idioma VARCHAR(5) NOT NULL DEFAULT 'pt',
  total NUMERIC(18,2) NOT NULL,
  valor_imposto NUMERIC(18,2) NOT NULL DEFAULT 0,
  valor_desconto NUMERIC(18,2) NOT NULL DEFAULT 0,
  hash_assinatura VARCHAR(300),
  codigo_qr TEXT,
  data_emissao TIMESTAMP NOT NULL DEFAULT NOW(),
  UNIQUE (empresa_id, numero_serie, numero_sequencial)
);

CREATE TABLE item_fatura (
  id BIGSERIAL PRIMARY KEY,
  fatura_id BIGINT NOT NULL REFERENCES fatura(id) ON DELETE CASCADE,
  produto_id BIGINT NOT NULL REFERENCES produto(id),
  quantidade NUMERIC(18,3) NOT NULL,
  preco_unitario NUMERIC(18,2) NOT NULL,
  desconto NUMERIC(18,2) NOT NULL DEFAULT 0
);

CREATE TABLE terminal_pos (
  id BIGSERIAL PRIMARY KEY,
  empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
  numero_serie VARCHAR(100) NOT NULL,
  descricao VARCHAR(200),
  localizacao VARCHAR(200),
  status VARCHAR(30) NOT NULL DEFAULT 'ATIVO',
  ip_address VARCHAR(50),
  data_cadastro TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE caixa (
  id BIGSERIAL PRIMARY KEY,
  terminal_id BIGINT NOT NULL REFERENCES terminal_pos(id),
  operador_id BIGINT,
  data_abertura TIMESTAMP NOT NULL,
  saldo_inicial NUMERIC(18,2) NOT NULL,
  total_entradas NUMERIC(18,2) NOT NULL DEFAULT 0,
  total_saidas NUMERIC(18,2) NOT NULL DEFAULT 0,
  diferenca_caixa NUMERIC(18,2) NOT NULL DEFAULT 0,
  data_encerramento TIMESTAMP,
  observacoes TEXT
);

CREATE TABLE movimento_caixa (
  id BIGSERIAL PRIMARY KEY,
  caixa_id BIGINT NOT NULL REFERENCES caixa(id) ON DELETE CASCADE,
  tipo_movimento VARCHAR(30) NOT NULL,
  valor NUMERIC(18,2) NOT NULL,
  descricao VARCHAR(255),
  data_movimento TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE venda_pos (
  id BIGSERIAL PRIMARY KEY,
  empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
  operador_id BIGINT,
  cliente_id BIGINT REFERENCES cliente(id),
  terminal_id BIGINT NOT NULL REFERENCES terminal_pos(id),
  data_venda TIMESTAMP NOT NULL DEFAULT NOW(),
  valor_total NUMERIC(18,2) NOT NULL,
  valor_desconto NUMERIC(18,2) NOT NULL DEFAULT 0,
  valor_imposto NUMERIC(18,2) NOT NULL DEFAULT 0,
  meio_pagamento VARCHAR(50) NOT NULL,
  numero_nfc VARCHAR(100),
  status_nf VARCHAR(30)
);

CREATE TABLE item_venda_pos (
  id BIGSERIAL PRIMARY KEY,
  venda_pos_id BIGINT NOT NULL REFERENCES venda_pos(id) ON DELETE CASCADE,
  produto_id BIGINT NOT NULL REFERENCES produto(id),
  quantidade NUMERIC(18,3) NOT NULL,
  preco_unitario NUMERIC(18,2) NOT NULL,
  valor_desconto_item NUMERIC(18,2) NOT NULL DEFAULT 0,
  valor_total NUMERIC(18,2) NOT NULL
);

CREATE TABLE armazem (
  id BIGSERIAL PRIMARY KEY,
  empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
  nome VARCHAR(120) NOT NULL,
  descricao VARCHAR(255),
  endereco VARCHAR(255),
  ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE produto_stock (
  id BIGSERIAL PRIMARY KEY,
  produto_id BIGINT NOT NULL REFERENCES produto(id) ON DELETE CASCADE,
  armazem_id BIGINT NOT NULL REFERENCES armazem(id) ON DELETE CASCADE,
  quantidade_atual NUMERIC(18,3) NOT NULL DEFAULT 0,
  quantidade_minima NUMERIC(18,3) NOT NULL DEFAULT 0,
  quantidade_maxima NUMERIC(18,3),
  quantidade_reservada NUMERIC(18,3) NOT NULL DEFAULT 0,
  data_atualizacao TIMESTAMP NOT NULL DEFAULT NOW(),
  UNIQUE (produto_id, armazem_id)
);

CREATE TABLE movimentacao_stock (
  id BIGSERIAL PRIMARY KEY,
  empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
  produto_id BIGINT NOT NULL REFERENCES produto(id),
  armazem_id BIGINT NOT NULL REFERENCES armazem(id),
  tipo_movimento VARCHAR(30) NOT NULL,
  quantidade NUMERIC(18,3) NOT NULL,
  preco_unitario NUMERIC(18,2),
  valor_total NUMERIC(18,2),
  numero_documento VARCHAR(100),
  data_movimento TIMESTAMP NOT NULL DEFAULT NOW(),
  operador_id BIGINT,
  observacoes TEXT
);

CREATE TABLE utilizador (
  id BIGSERIAL PRIMARY KEY,
  empresa_id BIGINT NOT NULL REFERENCES empresa(id) ON DELETE CASCADE,
  nome_completo VARCHAR(150) NOT NULL,
  email VARCHAR(150) NOT NULL,
  username VARCHAR(80) NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  two_fa_enabled BOOLEAN NOT NULL DEFAULT FALSE,
  two_fa_secret VARCHAR(255),
  ultimo_login TIMESTAMP,
  data_criacao TIMESTAMP NOT NULL DEFAULT NOW(),
  status VARCHAR(20) NOT NULL DEFAULT 'ATIVO',
  data_bloqueio TIMESTAMP,
  tentativas_falhas INT NOT NULL DEFAULT 0,
  UNIQUE (empresa_id, username)
);

CREATE TABLE perfil_usuario (
  id BIGSERIAL PRIMARY KEY,
  nome_perfil VARCHAR(100) NOT NULL,
  descricao VARCHAR(255),
  empresa_id BIGINT REFERENCES empresa(id) ON DELETE CASCADE,
  eh_padrao BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE permissao (
  id BIGSERIAL PRIMARY KEY,
  codigo_permissao VARCHAR(120) UNIQUE NOT NULL,
  descricao VARCHAR(255),
  categoria VARCHAR(60)
);

CREATE TABLE perfil_permissao (
  id BIGSERIAL PRIMARY KEY,
  perfil_id BIGINT NOT NULL REFERENCES perfil_usuario(id) ON DELETE CASCADE,
  permissao_id BIGINT NOT NULL REFERENCES permissao(id) ON DELETE CASCADE,
  UNIQUE (perfil_id, permissao_id)
);

CREATE TABLE auditoria_usuario (
  id BIGSERIAL PRIMARY KEY,
  usuario_id BIGINT NOT NULL REFERENCES utilizador(id) ON DELETE CASCADE,
  acao VARCHAR(40) NOT NULL,
  tabela_afetada VARCHAR(120),
  registro_id VARCHAR(120),
  dados_anteriores JSONB,
  dados_novos JSONB,
  data_hora TIMESTAMP NOT NULL DEFAULT NOW(),
  endereco_ip VARCHAR(50),
  user_agent VARCHAR(255)
);

CREATE TABLE banner_publicidade (
  id BIGSERIAL PRIMARY KEY,
  titulo VARCHAR(150) NOT NULL,
  descricao TEXT,
  tipo_midia VARCHAR(30) NOT NULL,
  url_midia TEXT,
  url_destino TEXT,
  data_inicio_vigencia DATE,
  data_fim_vigencia DATE,
  ativo BOOLEAN NOT NULL DEFAULT TRUE,
  segmentacao VARCHAR(40),
  idiomas VARCHAR(50),
  ordem_apresentacao INT,
  data_criacao TIMESTAMP NOT NULL DEFAULT NOW(),
  criado_por BIGINT
);

CREATE TABLE licenca_melhorada (
  id BIGSERIAL PRIMARY KEY,
  codigo_licenca VARCHAR(100) UNIQUE NOT NULL,
  tenant_id BIGINT NOT NULL,
  parceiro_id BIGINT,
  tipo_licenca VARCHAR(30) NOT NULL,
  data_inicio DATE NOT NULL,
  data_expiracao DATE,
  renovacao_automatica BOOLEAN NOT NULL DEFAULT FALSE,
  numero_usuarios INT,
  limite_facturas_dia INT,
  limite_produtos INT,
  limite_clientes INT,
  status_licenca VARCHAR(30) NOT NULL,
  preco_mensal NUMERIC(18,2),
  data_proximo_pagamento DATE,
  historico_pagamentos JSONB
);

CREATE INDEX idx_fatura_data_emissao ON fatura(data_emissao);
CREATE INDEX idx_venda_pos_data ON venda_pos(data_venda);
CREATE INDEX idx_movimentacao_stock_data ON movimentacao_stock(data_movimento);
CREATE INDEX idx_utilizador_status ON utilizador(status);
