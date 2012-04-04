@echo off

:: Android
set ANDROID_NAMESPACE=com.philippspiess.hotel/.CustomerActivity

:: Projects
set SERVER_PATH=%SOURCES_PATH%\server\server
set SERVER_PROJ=server.csproj

set EMPLOYEE_PATH=%SOURCES_PATH%\sources\employee\employee
set EMPLOYEE_PROJ=employee.csproj
set EMPLOYEE_BIN_NAME=employee.exe

set CUSTOMER_PATH=%SOURCES_PATH%\sources\customer
set CUSTOMER_BIN_NAME=CustomerActivity-debug.apk
::   Will be automatically named after your main Activity