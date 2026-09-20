@echo off
chcp 65001 >nul
title ZMS
cd /d "%~dp0"
echo.
echo   ZMS систем эхэлж байна...
echo   Энэ цонхыг ХААХГҮЙ байна уу. Жижигрүүлж болно.
echo.
start "" /min cmd /c "timeout /t 25 /nobreak >nul & start http://localhost:8081"
jre\bin\java.exe -Dfile.encoding=UTF-8 -Djava.library.path=libs -Djavax.net.ssl.trustStoreType=Windows-ROOT -Dspring.config.additional-location=file:config/ -jar zms.jar
echo.
echo   ZMS зогслоо. Алдаа гарсан бол дээрх мессежийг зураг авч илгээнэ үү.
pause
