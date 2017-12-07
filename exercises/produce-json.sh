#!/bin/bash

LIMIT_ARG=" LIMIT 20"
while [ "$1" != "" ]
do
    case "$1" in
        "--all")
            LIMIT_ARG=""
            ;;
        *)
            LIMIT_ARG=" LIMIT $1"
            ;;
    esac
    shift
done
    
DB=./bolaget.db
sql()
{
    echo "$*" | sqlite3 $DB
}

init_json()
{
    echo "["
}

end_json()
{
    echo "]"
}

product_json()
{
    CNT=0
    sql "SELECT name, price, alcohol FROM product $LIMIT_ARG" | while read LINE
    do
        if [ "$LINE" = "" ] ; then break ; fi
        if [ $CNT -ne 0 ] ; then echo ","; fi
        echo $LINE | \
            awk 'BEGIN {FS="|"} { printf "  {\n    \"name\" : \"%s\",\n    \"price\" : \"%s\",\n    \"alcohol\" : \"%s\"\n  }", $1, $2, $3}'
        CNT=$((CNT + 1))
    done
    echo
}

#
# Function to output header
#
http_header()
{
    echo "HTTP/1.0 200 OK"
    echo "Connection: close"
    echo "Date: $(LANG=EN date)"
    echo "Server: netcat special deal"
    echo "Content-Type: application/json"
    echo "Cache-Control: max-age=60"
    echo ""
}

http_header
init_json
product_json
end_json

