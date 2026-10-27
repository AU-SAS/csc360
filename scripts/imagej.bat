@echo off
rem Starts ImageJ 1.x with the Java installed on this machine, in place of the
rem bundled ImageJ.exe launcher, whose compiled-in -Xincgc option confines it to
rem Java 8. The script belongs in the ImageJ installation directory, or IJ_HOME
rem names that directory. IJ_MEMORY overrides the heap size.
rem The reasoning is recorded in docs\imagej.md.

setlocal

if "%IJ_HOME%"=="" set IJ_HOME=%~dp0
if "%IJ_HOME:~-1%"=="\" set IJ_HOME=%IJ_HOME:~0,-1%
if "%IJ_MEMORY%"=="" set IJ_MEMORY=2048m

set JAVA=
if not "%JAVA_HOME%"=="" if exist "%JAVA_HOME%\bin\javaw.exe" set JAVA=%JAVA_HOME%\bin\javaw.exe
if "%JAVA%"=="" for %%I in (javaw.exe) do if not "%%~$PATH:I"=="" set JAVA=%%~$PATH:I

if "%JAVA%"=="" (
    echo imagej: no Java was found, so ImageJ cannot be started.
    echo imagej: JAVA_HOME is "%JAVA_HOME%", and no javaw.exe is on PATH.
    echo imagej: install a JDK ^(8 or later^) or set JAVA_HOME to one, then run this script again.
    goto fail
)

if not exist "%IJ_HOME%\ij.jar" (
    echo imagej: ij.jar was not found in "%IJ_HOME%", so there is nothing to start.
    echo imagej: place this script in the ImageJ installation directory, or set IJ_HOME to it.
    goto fail
)

cd /d "%IJ_HOME%"
start "ImageJ" "%JAVA%" -Xmx%IJ_MEMORY% -Dplugins.dir="%IJ_HOME%" -cp "%IJ_HOME%\ij.jar" ij.ImageJ %*
endlocal
exit /b 0

rem A script started from Explorer owns its console window, which closes with the
rem script and takes the message above with it; the pause holds it open. In an
rem interactive session cmdcmdline is the shell's own command line, which does not
rem name the script, so nothing waits. Invoking the script as "cmd /c path\imagej.bat"
rem does match, and pauses; with no console to read from, the pause returns at once.
:fail
echo %cmdcmdline% | find /i "%~nx0" > nul && pause
endlocal
exit /b 1
