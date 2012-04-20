@echo off
set OLDDIR=%CD%

call setPupilID.bat
call setEnvironment.bat

:::::::::::::::
:: SERVER
:::::::::::::::

:: build
echo %SERVER_PATH%
cd %SERVER_PATH%
MSBuild "%SERVER_PROJ%" /t:rebuild /p:Configuration=Release

:: prepare deploy directory
rmdir /s /q %DEPLOY_TO_PATH%
mkdir "%DEPLOY_TO_PATH%"
mkdir "%DEPLOY_TO_PATH%\bin"

:: DEPLOY HARD!
cd %OLDDIR%
copy "%SERVER_PATH%\bin\*" "%DEPLOY_TO_PATH%\bin\"
copy "%SERVER_PATH%\*.asmx" "%DEPLOY_TO_PATH%\"
copy "%SERVER_PATH%\*.config" "%DEPLOY_TO_PATH%\"

:: restart IIS / not necessary but perhaps it fixes some problems
iisreset /restart


:::::::::::::::
:: EMPLOYEE
:::::::::::::::

:: build
cd %OLDDIR%
cd %EMPLOYEE_PATH%
MSBuild "%EMPLOYEE_PROJ%" /t:rebuild /p:Configuration=Release


:::::::::::::::
:: CUSTOMER
:::::::::::::::

:: generating build.xml for apache ant
cd %OLDDIR%
cd %CUSTOMER_PATH%
call android.bat update project --path .

:: building
call ant.bat debug


:: exiting
cd %OLDDIR%