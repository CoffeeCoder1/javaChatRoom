@echo off
mvn install -B
cd target
copy javaChatRoomClient-1.0-SNAPSHOT.jar javaChatRoomClient.jar
pause