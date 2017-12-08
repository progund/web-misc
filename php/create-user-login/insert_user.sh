#!/bin/bash

username=$1
email=$2
password=$3
name=$4

if [ $# != 4 ]
then
  echo "Usage: $0 <username> <email> <password> <name>"
  exit 1
fi

echo "insert into user(username, email, password, name) values('"$username"', '"$email"', '"$password"', '"$name"');" | sqlite3 myreddit.db
