@echo off
setlocal

echo Building Valknut Recovery Extension...

if exist build_recovery rmdir /s /q build_recovery
mkdir build_recovery

dir /s /b src\*.java > sources_recovery_windows.txt
javac -encoding UTF-8 -d build_recovery @sources_recovery_windows.txt
if errorlevel 1 (
    echo Compilation failed.
    pause
    exit /b 1
)

jar --create --file Valknut_Hana_Recovery_Extension_v1.0.jar --main-class me.Main -C build_recovery . -C src resources
if errorlevel 1 (
    echo JAR creation failed.
    pause
    exit /b 1
)

echo Build completed successfully.
echo Created: Valknut_Hana_Recovery_Extension_v1.0.jar
pause
