# Repomultilingua AGT

Implementação base multi-módulo para um software de facturação multi-tenant licenciado pela AGT, com CRM separado, APIs REST, páginas JSF, scripts PostgreSQL e suporte multilíngue (PT, EN, FR, ZH).

## Módulos

- `common`: modelos comuns, respostas de API e suporte i18n
- `facturacao-core`: entidades, DTOs, serviços e contratos de repositório da facturação
- `facturacao-api`: API REST da facturação com JWT, RBAC, relatório e backup
- `facturacao-web`: páginas JSF/PrimeFaces e managed beans da facturação
- `crm-api`: API REST do CRM com gestão de parceiros, tenants e licenças
- `crm-web`: páginas JSF/PrimeFaces e managed beans do CRM
- `database`: scripts PostgreSQL completos em português
- `docs`: documentação de API e deployment

## Build

```bash
mvn test
```

## Conteúdo funcional entregue

- Estrutura Maven multi-módulo
- Endpoints REST para facturas, clientes, backups, parceiros, tenants e licenças
- Segurança JWT com perfis `ADMINISTRADOR`, `OPERADOR`, `ADMINISTRADOR_CRM` e `PARCEIRO`
- Recursos JSF `.xhtml` para dashboards e gestão principal
- Scripts SQL com `DROP DATABASE`, `CREATE DATABASE`, tabelas, índices, views e triggers
- Documentação de setup, API e deployment
