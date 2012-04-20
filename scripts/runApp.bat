@echo off
set OLDDIR=%CD%

call setEnvironment.bat
call setPupilID.bat

adb install -r "%CUSTOMER_PATH%\bin\%CUSTOMER_BIN_NAME%"
adb start-server
adb wait-for-device
adb shell am start -a android.intent.action.MAIN -n %ANDROID_NAMESPACE%

:: exiting
cd %OLDDIR%