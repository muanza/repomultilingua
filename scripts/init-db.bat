@echo off
setlocal

set ROOT_DIR=%~dp0\..
if "%DB_NAME%"=="" set DB_NAME=repomultilingua_dev
if "%DB_USER%"=="" set DB_USER=repomultilingua

psql -U %DB_USER% -d %DB_NAME% -f "%ROOT_DIR%\database\schema-init.sql" || exit /b 1
for %%f in ("%ROOT_DIR%\database\migrations\*.sql") do psql -U %DB_USER% -d %DB_NAME% -f %%f || exit /b 1

echo [init-db] Done.
endlocal
