#!/bin/bash

PATHSEP=":"
if [[ $OS == "Windows_NT" ]] || [[ $OSTYPE == "cygwin" ]]
then
    PATHSEP=";"
fi

javac -cp "./www/WEB-INF/classes/${PATHSEP}winstone.jar" www/WEB-INF/classes/se/yrgo/schedule/*.java && java -jar winstone.jar --webroot=www
