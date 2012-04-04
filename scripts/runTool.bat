@echo off
set OLDDIR=%CD%


cd "%EMPLOYEE_PATH%"
call "bin\Release\%EMPLOYEE_BIN_NAME%"


:: exiting
cd %OLDDIR%