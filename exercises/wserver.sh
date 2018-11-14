#!/bin/bash



#
# Function to output header
#
header()
{
    echo "HTTP/1.0 200 OK"
    echo "Connection: close"
    echo "Date: $(date)"
    echo "Server: netcat special deal"
    echo "Content-Type: text/html; charset=utf-8"
    echo "Cache-Control: max-age=60"
    echo ""
    echo ""
}

#
# Function to outout content 
#
content()
{
    echo "<html>"
    echo "<head><title>Some web page title</title></head>"
    echo "<body>"
    echo "<p>Hello HTTP</p>"
    echo "</body>"
    echo "</html>"
}

CONTENT=$(content)
CLENGTH=$(echo $CONTENT | wc -c)
LENGTH=$(( $CLENGTH + 4  ))
header
# close with EOF
exec 1>&- 
