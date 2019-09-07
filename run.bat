echo off
xcopy out\artifacts\KDecryptorNG_jar\KDecryptorNG.jar /Y
echo "Loading...";
echo on
java -jar out\artifacts\KDecryptorNG_jar\KDecryptorNG.jar
