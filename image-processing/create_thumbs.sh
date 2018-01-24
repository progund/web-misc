#!/bin/bash

# Creates thumbnails of 150x150 px in thumbnails/ from
# all original images (*.JPG) in originals/

mkdir -p thumbs

for orig in originals/*.JPG
do
    convert $orig -resize 150x150 thumbs/$(basename $orig)
done
