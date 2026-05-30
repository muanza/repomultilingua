#!/usr/bin/env bash
set -euo pipefail
ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
psql -U "${DB_USER:-repomultilingua}" -d "${DB_NAME:-repomultilingua_dev}" -f "$ROOT_DIR/database/schema-init.sql"
for migration in "$ROOT_DIR"/database/migrations/*.sql; do
  [ -e "$migration" ] || continue
  psql -U "${DB_USER:-repomultilingua}" -d "${DB_NAME:-repomultilingua_dev}" -f "$migration"
done
