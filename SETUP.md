# Setup

## Requisitos

- Java 11+
- Maven 3.8+
- PostgreSQL 13+

## Base de dados

Execute `/tmp/workspace/muanza/repomultilingua/database/schema.sql` com `psql`, depois carregue dados de exemplo com `data-inicial.sql`.

## Execução local

```bash
mvn test
mvn -pl facturacao-api spring-boot:run
mvn -pl crm-api spring-boot:run
```

## Perfis iniciais

- Facturação: `admin/admin123`, `operador/operador123`
- CRM: `crmadmin/admin123`, `parceiro/parceiro123`
