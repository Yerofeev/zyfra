#! /bin/bash

set -x
set -e

mvn install dockerfile:build
docker run -p 8080:8080 -t  ee/zyfra-enterprise