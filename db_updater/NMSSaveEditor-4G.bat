@echo off
:start
::Add --enable-native-access=ALL-UNNAMED to support flatlaf (due to aging API)
java --enable-native-access=ALL-UNNAMED -Xmx4G -jar NMSSaveEditor.jar -autoupdate
if errorLevel 2 (
	move /Y ~NMSSaveEditor.dl NMSSaveEditor.jar
	goto start
)
if errorlevel 1 (
   pause
)