#!/usr/bin/env bash
mvn clean package
docker build -t oklimenko/api-oauth-server .
docker tag oklimenko/api-oauth-server oklimenko/api-oauth-server:0.5.0
#docker push oklimenko/api-oauth-server:0.4.0