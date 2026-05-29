# repomultilingua

Solução multi-tenant de facturação e CRM para AGT.

## Módulos
- `facturacao-api`: backend da facturação (JPA/JAX-RS)
- `facturacao-web`: frontend JSF/PrimeFaces para facturação
- `crm-api`: backend CRM para licenciamento
- `crm-web`: frontend JSF/PrimeFaces para CRM
- `common`: componentes partilhados (i18n, utilitários, backup)

## Build
```bash
mvn test
```
