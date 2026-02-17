@echo off

SET "JAR_FILE=NMSSaveEditor.jar"
SET "SOURCE_PATH=nomanssave"

_7z.exe u "%JAR_FILE%" "%SOURCE_PATH%" -tzip -r -y

echo The JAR file %JAR_FILE% has been updated.
pause