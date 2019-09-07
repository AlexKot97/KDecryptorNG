@echo off
xcopy out\artifacts\KDecryptorNG_jar\KDecryptorNG.jar /Y > nul
java -jar out\artifacts\KDecryptorNG_jar\KDecryptorNG.jar
@echo on
