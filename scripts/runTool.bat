@echo off
set OLDDIR=%CD%

call setEnvironment.bat
call setPupilID.bat

cd %EMPLOYEE_PATH%
call "bin\Release\%EMPLOYEE_BIN_NAME%"

:: exiting
cd %OLDDIR%