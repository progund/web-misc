#!/bin/bash

echo -e ".headers on\nSELECT * FROM user;" | sqlite3 myreddit.db
