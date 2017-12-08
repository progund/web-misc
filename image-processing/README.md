Small scripts example for image processing etc.

One script using imagemagick to create thumbnails from larger images.

One script for generating an HTML page with image links displaying the thumbnails and linking to the original larger image.

All images (c) Rikard Fröberg.

Usage:

```bash
# Generate thumbs from originals/*.JPG to thumbs/
$ ./create_thumbs.sh

# Generate HTML page:
$ ./gen_image_links.sh
<!DOCTYPE html>
<html><head><title>Thumbnails</title><head>
<body>
<p>Images (c) Rikard Fröberg</p>
<p>
<a href="originals/IMG_0001.JPG"><img src="thumbs/IMG_0001.JPG" /></a><br>
<a href="originals/IMG_0002.JPG"><img src="thumbs/IMG_0002.JPG" /></a><br>
<a href="originals/IMG_0003.JPG"><img src="thumbs/IMG_0003.JPG" /></a><br>
<a href="originals/IMG_0004.JPG"><img src="thumbs/IMG_0004.JPG" /></a><br>
<a href="originals/IMG_0005.JPG"><img src="thumbs/IMG_0005.JPG" /></a><br>
<a href="originals/IMG_0006.JPG"><img src="thumbs/IMG_0006.JPG" /></a><br>
<a href="originals/IMG_0007.JPG"><img src="thumbs/IMG_0007.JPG" /></a><br>
<a href="originals/IMG_0008.JPG"><img src="thumbs/IMG_0008.JPG" /></a><br>
<a href="originals/IMG_0009.JPG"><img src="thumbs/IMG_0009.JPG" /></a><br>
</p>
</body></html>
```
