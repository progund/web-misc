Small example on how one could upload an image to be used as the profile image on a php web site.

The image will be named username.png (where the username comes from the session object - the login script should add "username" to the session object).

There's also a script using curl to POST upload an image to the php script:

```bash
$ ./upload_avatar.sh
USAGE: ./upload_avatar.sh <file>

# Successful run:
$ ./upload_avatar.sh IMG_0039.JPG
<pre>File is valid, and was successfully uploaded.
Here is some more debugging info:Array
(
    [avatar] => Array
       (
          [name] => IMG_0039.JPG
          [type] => image/jpeg
          [tmp_name] => /tmp/phpbj3HcO
          [error] => 0
          [size] => 1939424
        )

)
</pre>
```

Stuff to think about: There's a max size for uploads set by both the HTML (don't know if all browsers care about that, but the php file seems to detect it) and also by the PHP server (php.ini). That max size will prevent too large images to be uploaded.

Also, the script asumes that the file is a png file (which in the example above, it isn't). So you should rather find out what type the image has, and from that information, create a file name with the correct suffix!

You might also want to check out ImageMagick for PHP, and see how you can convert the uploaded file to a format of your liking (like e.g. PNG).
