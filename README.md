# repomultilingua

Projeto multi-módulo para software de facturação com suporte multilíngue (PT/EN/FR/ZH), incluindo módulos de POS, Utilizadores, Vendas/Facturação, Stock e CRM.

## 1) Pré-requisitos

### Sistema operacional
- Windows 10+
- Linux (Ubuntu 22.04+ ou equivalente)
- macOS 13+

### Ferramentas
- **JDK 17**
- **Apache Maven 3.9+**
- **PostgreSQL 14+**
- **NetBeans 19+** (com suporte Maven)
- **Docker + Docker Compose** (opcional para deploy containerizado)

Verificação rápida:

```bash
java -version
mvn -version
psql --version
docker --version
```

## 2) Estrutura do projeto

```text
repomultilingua/
├── modules/
│   ├── common/        # Serviços comuns e recursos multilíngues
│   ├── facturacao/    # Vendas/Facturação
│   ├── pos/           # Ponto de Venda
│   ├── stock/         # Gestão de Stock
│   ├── users/         # Gestão de Utilizadores
│   └── crm/           # CRM separado
├── database/
│   ├── schema-init.sql
│   └── migrations/
├── config/
│   ├── application-dev.properties
│   ├── application-staging.properties
│   └── application-production.properties
├── scripts/
│   ├── setup.sh / setup.bat
│   ├── deploy.sh / deploy.bat
│   └── init-db.sh
├── docker/
│   ├── Dockerfile
│   └── docker-compose.yml
├── .nbproject/
├── pom.xml
└── nbactions.xml
```

## 3) Configuração PostgreSQL

1. Criar utilizador e base de dados:

```sql
CREATE USER repomultilingua WITH PASSWORD 'repomultilingua';
CREATE DATABASE repomultilingua_dev OWNER repomultilingua;
CREATE DATABASE repomultilingua_staging OWNER repomultilingua;
CREATE DATABASE repomultilingua OWNER repomultilingua;
```

2. Inicializar schema:

Linux/macOS:
```bash
chmod +x scripts/*.sh
./scripts/init-db.sh
```

Windows:
```bat
psql -U repomultilingua -d repomultilingua_dev -f database\schema-init.sql
for %f in (database\migrations\*.sql) do psql -U repomultilingua -d repomultilingua_dev -f %f
```

## 4) Configuração por ambiente

Os ficheiros em `config/` centralizam:
- **Pool de conexões DB** (`datasource.pool.*`)
- **Logging** (`logging.level.*`, `logging.file.path`)
- **Feature flags** (`app.feature.*`)
- **Email SMTP** (`email.smtp.*`)
- **Serviços externos** (`external.crm.*`)

Ambientes:
- `application-dev.properties`
- `application-staging.properties`
- `application-production.properties`

## 5) Build e execução Maven

Build/teste completo:

```bash
mvn test
```

Build por perfil:

```bash
mvn -Pdev clean package
mvn -Pstaging clean package
mvn -Pproduction clean package
```

Build Docker via Maven:

```bash
mvn -Pdocker -DskipTests package
```

## 6) Setup e deploy automatizados

Linux/macOS:

```bash
chmod +x scripts/*.sh
./scripts/setup.sh
./scripts/deploy.sh production
```

Windows:

```bat
scripts\setup.bat
scripts\deploy.bat production
```

## 7) Docker Compose

Subir PostgreSQL + aplicação:

```bash
docker compose -f docker/docker-compose.yml up --build
```

## 8) NetBeans (Windows/Linux/macOS)

1. Abrir NetBeans.
2. **File > Open Project** e selecionar a raiz `repomultilingua`.
3. NetBeans identifica o projeto Maven multi-módulo (`pom.xml`).
4. Em **Project Properties**, confirmar JDK 17.
5. Usar ações Maven:
   - `Clean and Build`
   - `Test`
   - Perfis: `dev`, `staging`, `production`

Arquivos NetBeans incluídos:
- `.nbproject/project.xml`
- `.nbproject/project.properties`
- `nbactions.xml`

## 9) Módulos

- **common**: recursos multilíngues e componentes partilhados.
- **facturacao**: vendas, faturas, regras de faturação.
- **pos**: ponto de venda.
- **stock**: controlo de inventário.
- **users**: gestão de utilizadores e permissões.
- **crm**: gestão de clientes e relacionamento (separado do core de faturação).

## 10) Contribuição

1. Criar branch de funcionalidade.
2. Manter mudanças pequenas e focadas.
3. Executar testes locais:
   ```bash
   mvn test
   ```
4. Atualizar documentação/configuração quando necessário.
5. Submeter PR com descrição clara.

---

## Comandos rápidos

```bash
# Testes
mvn test

# Setup local
./scripts/setup.sh

# Deploy local com perfil de produção
./scripts/deploy.sh production
```
