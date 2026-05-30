#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
DB_NAME="${DB_NAME:-repomultilingua_dev}"
DB_USER="${DB_USER:-repomultilingua}"

echo "[setup] Checking Maven..."
command -v mvn >/dev/null

echo "[setup] Checking PostgreSQL client..."
command -v psql >/dev/null

echo "[setup] Creating database (if needed)..."
createdb "$DB_NAME" -U "$DB_USER" || true

echo "[setup] Applying schema..."
psql -U "$DB_USER" -d "$DB_NAME" -f "$ROOT_DIR/database/schema-init.sql"

for migration in "$ROOT_DIR"/database/migrations/*.sql; do
  [ -e "$migration" ] || continue
  psql -U "$DB_USER" -d "$DB_NAME" -f "$migration"
done

echo "[setup] Building modules..."
cd "$ROOT_DIR"
mvn -Pdev clean test

echo "[setup] Done."
