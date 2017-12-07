<?php
// In PHP versions earlier than 4.1.0, $HTTP_POST_FILES should be used instead
// of $_FILES.

// Fake that we have a session with the username stored:
session_start();
$_SESSION["username"] = "rille";



echo '<pre>';
$error = $_FILES['avatar']['error'];
if ($error != UPLOAD_ERR_OK) {
    echo "Error: $error";
}

// Get the username from the session:
$username = $_SESSION["username"];

// Create the filename for the avatar image
// username.jpg
$uploaddir = 'avatars/';

$original_name = $uploaddir . basename($_FILES['avatar']['name']);
$uploadfile = $uploaddir . basename($username . ".png");


if (move_uploaded_file($_FILES['avatar']['tmp_name'], $uploadfile)) {
    echo "File is valid, and was successfully uploaded.\n";
    

} else {
    echo "Possible file upload attack!\n";
}

echo 'Here is some more debugging info:';
print_r($_FILES);

print "</pre>";

?>