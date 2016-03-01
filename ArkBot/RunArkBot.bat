@echo off

set /p vers=<ArkBotFiles/Version/CurrentVersion.txt

echo Starting ArkBot%vers%.jar

start javaw -jar "ArkBot%vers%.jar"