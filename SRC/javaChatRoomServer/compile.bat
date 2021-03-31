@echo off
mvn install -B
cd target
copy javaChatRoomServer-1.0-SNAPSHOT.jar javaChatRoomServer.jar
pause