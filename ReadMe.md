# Le wild school project appeared.

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

  - /scripts -  All the .bat files
  - /sources
    - /customer - Android Application
    - /employee - Client Application
    - /server - WebService

## How to get started

Make sure `setEnvironment.bat` and (for the pupils) `setPupilID.bat` is set up correctly.

Open the windows command prompt and cd into the `scripts` folder.

**Set Environment Variables**

    setEnvironment.bat
    setPupilID.bat

*Pleace notice the order.*

**Compile**

    generateAll.bat

**Jump into it**

   runApp.bat
   runTool.bat