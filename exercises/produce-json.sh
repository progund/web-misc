#!/bin/bash

# Without argument, you will get 20 products
LIMIT_ARG=" LIMIT 20"

# If there's an argument to the script,
#+then parse the argument and update the
#+limit string.
if [[ $# == 1 ]]
then
    LIMIT=$1
    case "$LIMIT" in
        "--all")
            LIMIT_ARG=""
            ;;
        *)
            LIMIT_ARG="LIMIT $LIMIT"
            ;;
    esac
fi
    
DB=./bolaget.db
sql()
{
    echo -e "$*" | sqlite3 $DB
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
    # No comma before the first line
    COMMA=false
    SQLITE_DIRECTIVES=".headers off\n.mode list\n"
    sql "${SQLITE_DIRECTIVES}SELECT name, price, alcohol FROM product $LIMIT_ARG;" | while read -r LINE
    do
        # If we are on the last line, then just exit the loop
        if [ "$LINE" = "" ] ; then break ; fi
        
        # If we are after the first json object, add a comma and newline
        if $COMMA ; then echo ","; fi

        # Print json object wihthout newline
        echo "$LINE" | \
            awk 'BEGIN {FS="|"} { printf "  {\n    \"name\" : \"%s\",\n    \"price\" : \"%s\",\n    \"alcohol\" : \"%s\"\n  }", $1, $2, $3}'
        
        # from here on, we want a comma and newline before the next object
        COMMA=true 
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

# MAIN - script begins here

http_header   # HTTP headers showing that the content is JSON
init_json     # output a [
product_json  # output a JSON object for each row in the database
end_json      # output a ]

