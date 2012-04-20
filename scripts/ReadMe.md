# Programming project scripts description

## Dependencies

 - .NET Framework 4
 - Apache Ant
 - Java JDK 1.6
 - Android SDK
 - IIS Running
 - Microsoft SQL Server

## Setup

IIS should be running and set up. You should have connected one adb device (either a virtual machine or a physical one).

## Structure

  - `/scripts` -  All the .bat files
  - `/sources`
    - `/customer` - Android Application
    - `/employee` - Client Application
    - `/server` - WebService

## How to get started

**Teacher and Student:** Open `/scripts/setEnvironment.bat` and change what you can change.

**Student:** Open `/scripts/setPupilID.bat` and set your pupil ID.

## Build and Play

Double click `/scripts/generateAll.bat` to build and deploy. (Make sure you run it with administrator rights)

Double click `/scripts/runApp.bat` to install the .apk on your device and open it. (It should automatically open an emulator)

Double click `/scripts/runTool.bat` to run the employee tool.