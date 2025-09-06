#!/bin/bash
# exec > script.log 2>&1

DB_CONTAINER="budg-db"
DB_NETWORK="budg-net"
PASSWORD="pass"
MYSQL_IMAGE="mysql:lts"

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
# If on Windows Git Bash, convert to /c/... style
HOST_DIR=$(echo "$SCRIPT_DIR/data" | sed 's#^/c#C:#')


# Safe network creation
docker network create $DB_NETWORK || true

if [ "$(docker ps -aq -f status=exited -f name=$DB_CONTAINER)" ]; then
    # cleanup
    docker rm $DB_CONTAINER
fi

if [ ! "$(docker ps -a -q -f name=$DB_CONTAINER)" ]; then
    echo "$HOST_DIR:/var/lib/mysql"
    docker run --name $DB_CONTAINER --network $DB_NETWORK \
            -v "$HOST_DIR:/var/lib/mysql" \
            -e MYSQL_ROOT_PASSWORD=$PASSWORD -d $MYSQL_IMAGE
fi

echo "Checking if server active..."
until docker exec $DB_CONTAINER mysqladmin ping -h$DB_CONTAINER --silent; do
    sleep 2
done
echo "Ready."

# Runs the command line client
winpty docker run \
    -it --rm \
    --network $DB_NETWORK $MYSQL_IMAGE \
    mysql -h$DB_CONTAINER -uroot -p$PASSWORD

# https://hub.docker.com/_/mysql