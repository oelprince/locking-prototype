#!/bin/zsh

cd docker-compose/local/postgres-docker
docker-compose up -d --build
cd ../../..
