@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion
cd /d "%~dp0"

echo ==========================================================
echo   ZMS bagts garah
echo ==========================================================
echo.

rem "build-release.bat legacy" geheer huuchin SQL Server (2008 / 2008 R2)-d zoriulsan
rem JDBC draiver (7.4)-tei bagts ugsarna. Argumentgui bol shine draiver (13.4).
set "MVNPROFILE="
set "DRIVERNOTE=SQL Server 2012+ (JDBC 13.4)"
if /i "%~1"=="legacy" (
  set "MVNPROFILE=-Plegacy-sqlserver"
  set "DRIVERNOTE=SQL Server 2008 / 2008 R2 (JDBC 7.4)"
)
echo   Draiver: !DRIVERNOTE!
echo.

set "JDK=%JAVA_HOME%"
if not exist "!JDK!\bin\jlink.exe" set "JDK=C:\Program Files\Amazon Corretto\jdk21.0.12_9"
if not exist "!JDK!\bin\jlink.exe" goto :nojdk

echo [1/5] Frontend build...
pushd frontend
set "NEXT_PUBLIC_API_BASE_URL="
set "NEXT_PUBLIC_USE_DUMMY_DATA=false"
call npx next build
set "RC=!errorlevel!"
popd
if not "!RC!"=="0" goto :error

echo [2/5] Frontend -^> backend...
robocopy "frontend\out" "src\main\resources\static" /mir /nfl /ndl /njh /njs /nc /ns >nul
if errorlevel 8 goto :error

echo [3/5] Backend package...
call "%~dp0mvnw.cmd" -o clean package -DskipTests !MVNPROFILE!
if errorlevel 1 goto :error

echo [4/5] Bagts ugsrah...
if not exist "dist\ZMS\config" mkdir "dist\ZMS\config"
if not exist "dist\ZMS\libs" mkdir "dist\ZMS\libs"
if exist "dist\ZMS\zms.jar" del /q "dist\ZMS\zms.jar"
if exist "dist\ZMS\jre\bin\java.exe" goto :havejre

echo      Java runtime garch baina, heden minut bolno...
if exist "dist\ZMS\jre" rmdir /s /q "dist\ZMS\jre"
"!JDK!\bin\jlink.exe" --module-path "!JDK!\jmods" --add-modules ALL-MODULE-PATH --strip-debug --no-header-files --no-man-pages --compress zip-6 --output "dist\ZMS\jre"
if errorlevel 1 goto :error
goto :jredone

:havejre
echo      Java runtime bii, algasav.

:jredone
for %%F in ("target\*.war") do copy /y "%%F" "dist\ZMS\zms.jar" >nul
if not exist "dist\ZMS\zms.jar" goto :nowar
copy /y "libs\*.dll" "dist\ZMS\libs\" >nul
copy /y "src\main\resources\application.properties" "dist\ZMS\config\application.properties" >nul
copy /y "release\db.properties" "dist\ZMS\config\db.properties" >nul
copy /y "release\legacy-tls.security" "dist\ZMS\config\legacy-tls.security" >nul
copy /y "release\*.bat" "dist\ZMS\" >nul
copy /y "release\*.txt" "dist\ZMS\" >nul

echo [5/5] ZIP...
if exist "dist\ZMS.zip" del /q "dist\ZMS.zip"
powershell -NoProfile -Command "Compress-Archive -Path 'dist\ZMS' -DestinationPath 'dist\ZMS.zip' -Force"
if errorlevel 1 goto :error

echo.
echo ==========================================================
echo   BELEN:  dist\ZMS.zip
echo   Turshih: dist\ZMS\ZMS-ehluuleh.bat
echo ==========================================================
echo.
pause
exit /b 0

:nojdk
echo [ALDAA] JDK oldsongui. JAVA_HOME-g tohiruulna uu.
goto :error

:nowar
echo [ALDAA] target hawtsand .war fail oldsongui.
goto :error

:error
echo.
echo ==========================================================
echo   AMJILTGUI. Deerh aldaanuudiig shalgana uu.
echo ==========================================================
echo.
pause
exit /b 1
