#!/usr/bin/env bash
docker build -t oklimenko/api-oauth-server .
docker tag oklimenko/api-oauth-server oklimenko/api-oauth-server:0.2.0