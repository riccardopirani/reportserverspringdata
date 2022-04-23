#!/bin/bash
#delete old jar file and regenerate jar file
mvn clean package
rm -rf test/*.jar
mvn install
#generate new jar file mvn clean package
mvn eclipse:eclipse
#define docker name
dockername=quote
#Delete all containers and all volumes
#docker system prune -a
#Build of container and image
docker rm $dockername
docker build -t $dockername .
#Run container
docker run --publish 8081:8081 $dockername

