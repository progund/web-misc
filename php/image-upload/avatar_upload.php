<?php
// In PHP versions earlier than 4.1.0, $HTTP_POST_FILES should be used instead
// of $_FILES.

// Most of the below is taken without shame from php.net and adopted
// for this small example

// Fake that we have a session with the username stored:
session_start();
$_SESSION["username"] = "rille";

//////////// Page below here //////////////
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

// Try to move the temporary file to the correct place and name:
if (move_uploaded_file($_FILES['avatar']['tmp_name'], $uploadfile)) {
    echo "File is valid, and was successfully uploaded.\n";
} else {
    echo "Possible file upload attack!\n"; // No idea what this really means ;-)
}

echo 'Here is some more debugging info:';
print_r($_FILES); // There's more info in the $_FILES variable...
print "</pre>";

?>