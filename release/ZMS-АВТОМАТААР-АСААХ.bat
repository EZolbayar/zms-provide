@echo off
chcp 65001 >nul
title ZMS - автоматаар асаах
cd /d "%~dp0"
set "STARTUP=%APPDATA%\Microsoft\Windows\Start Menu\Programs\Startup"
set "ENTRY=%STARTUP%\ZMS.bat"

if not exist "%~dp0zms-run.bat" (
  echo.
  echo   [АЛДАА] zms-run.bat олдсонгүй.
  echo   Энэ файлыг ZMS хавтас доторх байрлалаас нь ажиллуулна уу.
  echo.
  pause
  exit /b 1
)

rem Хавтасны зам кирилл үсэгтэй байж болох тул богино (8.3) замыг ашиглана.
set "SHORTDIR=%~sdp0"
set "SHORTDIR=%SHORTDIR:~0,-1%"

> "%ENTRY%" echo @echo off
>>"%ENTRY%" echo start "ZMS" /min /d "%SHORTDIR%" "%SHORTDIR%\zms-run.bat"

if not exist "%ENTRY%" goto :error

rem Хуучин хувилбарын богино холбоос үлдсэн бол устгана.
if exist "%STARTUP%\ZMS.lnk" del /q "%STARTUP%\ZMS.lnk"

echo.
echo ==========================================================
echo   БЭЛЭН. Компьютер асах бүрд ZMS автоматаар эхэлнэ.
echo.
echo   - Хэрэглэгч нэвтэрч орсны дараа эхэлнэ.
echo   - Цонх жижигрүүлсэн байдлаар нээгдэнэ, ХААХГҮЙ байна уу.
echo   - Хөтөч дээр http://localhost:8081 гэж орно.
echo.
echo   Болих бол: ZMS-АВТОМАТЫГ-БОЛИХ.bat дээр хоёр дарна.
echo ==========================================================
echo.
pause
exit /b 0

:error
echo.
echo   [АЛДАА] Автомат асаалт тохирогдсонгүй.
echo   Энэ цонхны зургийг авч илгээнэ үү.
echo.
pause
exit /b 1
