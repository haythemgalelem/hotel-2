@echo off
set OLDDIR=%CD%

:::::::::::::::
:: SERVER
:::::::::::::::

:: build
cd %SERVER_PATH%
"%MSBUILD_PATH%\MSBuild.exe" "%SERVER_PROJ%" /t:rebuild /p:Configuration=Release

:: prepare deploy directory
rmdir /s /q %DEPLOY_TO_PATH%
mkdir "%DEPLOY_TO_PATH%"
mkdir "%DEPLOY_TO_PATH%\bin"

:: DEPLOY HARD!
copy "%SERVER_PATH%\bin\*" "%DEPLOY_TO_PATH%\bin\"
copy "%SERVER_PATH%\*.asmx" "%DEPLOY_TO_PATH%\"
copy "%SERVER_PATH%\*.config" "%DEPLOY_TO_PATH%\"

:: restart IIS / not necessary but perhaps it fixes some problems
iisreset /restart


:::::::::::::::
:: EMPLOYEE
:::::::::::::::

:: build
cd "%EMPLOYEE_PATH%"
"%MSBUILD_PATH%\MSBuild.exe" "%EMPLOYEE_PROJ%" /t:rebuild /p:Configuration=Release


:::::::::::::::
:: CUSTOMER
:::::::::::::::

:: generating build.xml for apache ant
cd "%CUSTOMER_PATH%"
call "%ANDROID_TOOLS_PATH%\android.bat" update project --path .

:: building
call "%ANT_PATH%\ant.bat" debug


:: exiting
cd %OLDDIR%