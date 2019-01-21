#!/usr/bin/env bash

#creates a docker image with review service on port 8080
mvn -f reviews/pom.xml -Dmaven.test.skip=true clean package dockerfile:build

#creates a docker image with the establishment service on port 8180
mvn -f establishments/pom.xml -Dmaven.test.skip=true clean package dockerfile:build