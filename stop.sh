#!/bin/bush

# Pull new changes
git pull

# Prepare Jar
mvn clean
mvn package

# Enshure that docker-compose stopped
docker-compose stopped

# Add environment variables
export BOT_NAME=$1
export BOT_TOKEN=$2

# Start new deployment
docker-compose up --build -d