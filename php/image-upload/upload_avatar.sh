#!/bin/bash

if [ $# != 1 ]
then
    echo "USAGE: $0 <file>"
    exit 1
fi
file=$1
curl http://localhost:1066/avatar_upload.php -F "avatar=@${file}"
