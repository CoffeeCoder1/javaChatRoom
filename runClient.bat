::@echo off

setlocal
:: Set variables for date
for /f "skip=8 tokens=2,3,4,5,6,7,8 delims=: " %%D in ('robocopy /l * \ \ /ns /nc /ndl /nfl /np /njh /XF * /XD *') do (
  set "dow=%%D"
  set "month=%%E"
  set "day=%%F"
  set "HH=%%G"
  set "MM=%%H"
  set "SS=%%I"
  set "year=%%J"
)

:: Set variable for port and hostname
echo Setting variables...
set hostname=localhost
set port=8022
::set newdate="%SS%-%MM%-%HH%-%dow%-%day%-%month%-%year%"
set newdate=test

echo Finding location to put logfile...
echo Trying %appdata%\CoffeeCoder\logs\javaChatRoom\Client\%newdate%\log.txt
::IF EXIST "%appdata%\CoffeeCoder\logs\javaChatRoom\Client\%newdate%\log.txt"
  echo Found location!
::) ELSE (
  echo Failed to start: date already exists as a logfile (you can probably just run this program again so the time will be slightly different).
  ::goto end
::)

echo Fixing some variables??? (Not supported yet....)

echo Starting program...
echo Clearing screen...
::cls

:: Run the program
java -jar javaChatRoomClient.jar localhost 8022>"%appdata%\CoffeeCoder\logs\javaChatRoom\Client\%newdate%\log.txt"

::end