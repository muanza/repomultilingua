#!/usr/bin/env bash
set -e
psql -U postgres -f database/schema-complete.sql
psql -U postgres -d agt_facturacao -f database/data-inicial.sql
