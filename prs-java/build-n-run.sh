#!/bin/bash

#build
mvn clean package

echo ""
echo ""
echo ""

#run the executable
java -jar target/prs-executable.jar