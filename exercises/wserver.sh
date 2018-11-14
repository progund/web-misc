#!/bin/bash


content()
{
    echo "<!DOCTYPE html>"
    echo "<html>"
    echo ""
    echo "<head>"
    echo "<title>Page Title</title>"
    echo "</head>"
    echo ""
    echo "<body>"
    echo "Current date is: $(date)"
    echo "</body>"
    echo ""
    echo "</html>"
}

header()
{
    echo "HTTP/1.1 200 OK"
    echo "Connection: close"
    echo "Date: $(date)"
    echo "Server: netcat special deal"
    echo "Content-Length: $LENGTH"
    echo "Content-Type: text/html; charset=utf-8"
    echo "Cache-Control: max-age=60"
    echo ""
    echo ""
    echo ""
}

CONTENT=$(content)
CLENGTH=$(echo $CONTENT | wc -c)
LENGTH=$(( $CLENGTH + 1 ))
header
echo ${CONTENT}
exec 1>&-

