#!/bin/bush

# Enshure that docker-compose stopped
docker-compose stopped

# Enshure that old application won't be deployed again
mvn clean