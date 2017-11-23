#!/bin/bash



product_json()
{
    echo "["
    echo "  {"
    echo "    \"name\" : \"Johanneshof Reinisch Pinot Noir\","
    echo "    \"price\" : \"143.0\","
    echo "    \"alcohol\" : \"13.0\""
    echo "  },"
    echo "  {"
    echo "    \"name\" : \"Dobogó Tokaji Furmint\","
    echo "    \"price\" : \"187.0\","
    echo "    \"alcohol\" : \"13.5\""
    echo "  }"
    echo "]"
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
product_json

