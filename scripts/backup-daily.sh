#!/usr/bin/env bash
set -e
TS=$(date +%Y%m%d%H%M%S)
mkdir -p backups
pg_dump -U postgres -Fc agt_facturacao > "backups/agt_facturacao_${TS}.dump"
