#!/bin/bash
ssh root@fructa.ga "cd /var/fructa; git pull origin master; docker-compose pull; docker-compose up -d"