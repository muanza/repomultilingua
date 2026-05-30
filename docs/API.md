# API

## Facturação

- `POST /api/v1/auth/login`
- `POST /api/v1/facturas`
- `GET /api/v1/facturas/{id}`
- `PUT /api/v1/facturas/{id}`
- `DELETE /api/v1/facturas/{id}`
- `GET /api/v1/facturas?empresaId=1`
- `POST /api/v1/clientes`
- `GET /api/v1/clientes?empresaId=1`
- `GET /api/v1/relatorios/vendas?empresaId=1`
- `POST /api/v1/backup/trigger?empresaId=1`
- `GET /api/v1/backup/status?empresaId=1`

## CRM

- `POST /api/v1/auth/login`
- `POST /api/v1/parceiros`
- `GET /api/v1/parceiros/{id}`
- `PUT /api/v1/parceiros/{id}`
- `GET /api/v1/parceiros`
- `POST /api/v1/tenants`
- `GET /api/v1/tenants/{id}`
- `PUT /api/v1/tenants/{id}`
- `GET /api/v1/licencas`
- `POST /api/v1/licencas`
- `DELETE /api/v1/licencas/{id}`
- `GET /api/v1/relatorios/utilizacao`

## Segurança

Ambas as APIs expõem autenticação JWT via `/api/v1/auth/login`.

