# repomultilingua

Implementação base multi-módulo para facturação multi-tenant AGT com integração POS, stock, utilizadores e CRM online.

## Estrutura
- `facturacao-*`, `pos-*`, `stock-*`, `users-*`, `crm-*`
- `database/` com scripts PostgreSQL completos em português
- `common/` com serviço de legendas multilíngues PT/EN/FR/ZH
- `scripts/` com setup e backup incremental diário

## Build e testes
```bash
mvn test
```
