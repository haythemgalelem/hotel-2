@echo off

:: Binary paths
set MSBUILD_PATH=C:\Windows\Microsoft.NET\Framework\v4.0.30319
set ANDROID_TOOLS_PATH=C:\Program Files\Android\android-sdk\tools
set ANDROID_PLATFORM_TOOL_PATH=C:\Program Files\Android\android-sdk\platform-tools
set ANT_PATH=C:\Program Files\apache-ant-1.8.3\bin

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

set SOURCES_PATH=C:\Users\philipp\dev\hotel\sources
