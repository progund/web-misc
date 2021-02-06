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
    #  All HTTP date/time stamps MUST be represented
    #+ in Greenwich Mean Time (GMT)
    echo "Date: $(TZ=Etc/GMT LC_TIME=C date '+%a, %d %b %Y %T %Z')"
    echo "Server: netcat special deal"
    echo "Content-Length: $LENGTH"
    echo "Content-Type: text/html; charset=utf-8"
    echo "Cache-Control: max-age=60"
    echo ""
}

CONTENT=$(content)
CLENGTH=$(echo -n $CONTENT | wc -c)
LENGTH=$(( $CLENGTH ))
header
echo -n ${CONTENT}
#exec 1>&-
