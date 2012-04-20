@echo off

:: Binary paths
:: Set path to .NET Framework, Android SDK tools and platform-tools, Apache Ant
set PATH=%PATH%;C:\Windows\Microsoft.NET\Framework\v4.0.30319;C:\Program Files\Android\android-sdk\tools;C:\Program Files\Android\android-sdk\platform-tools;C:\Program Files\apache-ant-1.8.3\bin

:: Required by ANT
set JAVA_HOME=C:\Program Files\Java\jdk1.6.0_31

:: IIS
set DEPLOY_TO_PATH=C:\webservice\hotel
	
:: Database
setx HOTEL_DB_ADDR localhost /m
setx HOTEL_DB_NAME hotel /m
setx HOTEL_DB_PWD testtest /m
setx HOTEL_DB_USER test /m
:: Has to be set global, otherwise IIS user won't have any access. Requires admin rights.

:: Android
set ANDROID_NAMESPACE=com.philippspiess.hotel/.CustomerActivity

:: Projects
set SERVER_PATH=..\sources\server\server
set SERVER_PROJ=server.csproj

set EMPLOYEE_PATH=..\sources\employee\employee
set EMPLOYEE_PROJ=employee.csproj
set EMPLOYEE_BIN_NAME=employee.exe

set CUSTOMER_PATH=..\sources\customer
set CUSTOMER_BIN_NAME=CustomerActivity-debug.apk
::   Will be automatically named after your main Activity
