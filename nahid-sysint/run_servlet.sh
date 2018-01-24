#!/bin/bash

javac -cp ./www/WEB-INF/classes/:winstone.jar www/WEB-INF/classes/se/yrgo/schedule/*.java && java -jar winstone.jar --webroot=www
