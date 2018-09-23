#! /bin/bash

set -x
set -e

mvn install dockerfile:build
docker run -p 8082:8082 -t  ee/zyfra-enterprise