#!/bin/bash

## Creates links over img tags with the thumbnail, leading
## to the orginial image

echo '<!DOCTYPE html>'
echo '<html><head><title>Thumbnails</title><head>'
echo '<body>'
echo '<p>Images (c) Rikard Fröberg</p>'
echo '<p>'
for image in thumbs/*.JPG
do
    echo "<a href=\"originals/$(basename $image)\"><img src=\"thumbs/$(basename $image)\" /></a><br>"
done
echo '</p>'
echo '</body></html>'
