@echo off
setlocal

set ROOT_DIR=%~dp0\..
set TARGET_ENV=%1
if "%TARGET_ENV%"=="" set TARGET_ENV=production

echo [deploy] Running package for environment: %TARGET_ENV%
cd /d "%ROOT_DIR%"
call mvn -P%TARGET_ENV% clean package || exit /b 1

where docker >nul 2>nul
if %ERRORLEVEL%==0 (
  echo [deploy] Running docker package build...
  call mvn -Pdocker -DskipTests package || exit /b 1
)

echo [deploy] Done.
endlocal
