@echo off
setlocal

set ROOT_DIR=%~dp0\..
if "%DB_NAME%"=="" set DB_NAME=repomultilingua_dev
if "%DB_USER%"=="" set DB_USER=repomultilingua

echo [setup] Checking Maven...
where mvn >nul 2>nul || (echo Maven not found & exit /b 1)

echo [setup] Checking PostgreSQL client...
where psql >nul 2>nul || (echo psql not found & exit /b 1)

echo [setup] Applying schema...
psql -U %DB_USER% -d %DB_NAME% -f "%ROOT_DIR%\database\schema-init.sql" || exit /b 1
for %%f in ("%ROOT_DIR%\database\migrations\*.sql") do psql -U %DB_USER% -d %DB_NAME% -f %%f || exit /b 1

echo [setup] Building modules...
cd /d "%ROOT_DIR%"
call mvn -Pdev clean test || exit /b 1

echo [setup] Done.
endlocal
