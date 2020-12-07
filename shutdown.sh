#!/bin/zsh

cd docker-compose/local/postgres-docker
docker-compose down --rmi all
cd ../../..
