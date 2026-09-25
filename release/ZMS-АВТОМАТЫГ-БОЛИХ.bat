@echo off
chcp 65001 >nul
title ZMS - автоматыг болих
set "STARTUP=%APPDATA%\Microsoft\Windows\Start Menu\Programs\Startup"
set "FOUND="

if exist "%STARTUP%\ZMS.bat" (
  del /q "%STARTUP%\ZMS.bat"
  set "FOUND=1"
)
if exist "%STARTUP%\ZMS.lnk" (
  del /q "%STARTUP%\ZMS.lnk"
  set "FOUND=1"
)

if defined FOUND (
  echo.
  echo   БЭЛЭН. Компьютер асахад ZMS автоматаар эхлэхээ болилоо.
  echo   ZMS-ЭХЛҮҮЛЭХ.bat дээр хоёр дарж гараар эхлүүлж болно.
  echo.
) else (
  echo.
  echo   Автомат асаалт тохирогдоогүй байна. Өөрчлөлт хийсэнгүй.
  echo.
)
pause
