@echo off

:: Binary paths
set MSBUILD_PATH=C:\Windows\Microsoft.NET\Framework\v4.0.30319
set ANDROID_TOOLS_PATH=C:\Program Files\Android\android-sdk\tools
set ANDROID_PLATFORM_TOOL_PATH=C:\Program Files\Android\android-sdk\platform-tools
set ANT_PATH=C:\Program Files\apache-ant-1.8.3\bin

:: Required by ANT
set JAVA_HOME=C:\Program Files\Java\jdk1.6.0_31

:: Android
set ANDROID_NAMESPACE=com.philippspiess.hotel/.CustomerActivity

:: IIS
set DEPLOY_TO_PATH=C:\webservice\hotel

:: Database
set HOTEL_DB_ADDR=localhost
set HOTEL_DB_NAME=hotel
set HOTEL_DB_PWD=testtest
set HOTEL_DB_USER=test

set SOURCES_PATH=C:\Users\philipp\dev\hotel\sources
