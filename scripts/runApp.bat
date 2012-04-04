@echo off
set OLDDIR=%CD%

"%ANDROID_PLATFORM_TOOL_PATH%\adb" install -r "%CUSTOMER_PATH%\bin\%CUSTOMER_BIN_NAME%"
"%ANDROID_PLATFORM_TOOL_PATH%\adb" start-server
"%ANDROID_PLATFORM_TOOL_PATH%\adb" wait-for-device
"%ANDROID_PLATFORM_TOOL_PATH%\adb" shell am start -a android.intent.action.MAIN -n %ANDROID_NAMESPACE%

:: exiting
cd %OLDDIR%