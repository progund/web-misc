#!/bin/bash

user=$1
pw=$2
if [ $# != 2 ]
then
    echo "USAGE: $0 <user> <password>";
    exit 1
fi
status=$(lwp-request -m HEAD http://localhost:1066/login.php | head -1)
if [ "$status" = "200 OK" ]
then
    echo "Server up, 200 OK";
else
    echo "Bad response: $status";
    exit 2;
fi

curl -s "http://localhost:1066/login.php?username=${user}&password=${pw}" | grep successful &> /dev/null
if [ $? = 0 ]
then
    echo "Login successful";
    exit 0;
else
    echo "Login unsuccessful";
    exit 1;
fi
