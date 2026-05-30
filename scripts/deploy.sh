#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
TARGET_ENV="${1:-production}"

echo "[deploy] Running package for environment: $TARGET_ENV"
cd "$ROOT_DIR"
mvn -P"$TARGET_ENV" clean package

echo "[deploy] Optional container build"
if command -v docker >/dev/null 2>&1; then
  mvn -Pdocker -DskipTests package
fi

echo "[deploy] Done."
