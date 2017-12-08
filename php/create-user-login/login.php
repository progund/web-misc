<!DOCTYPE html>
<html><head><title>Login</title></head>
<body>

<?php

$password = null;
$username = null;
$dbname   = "myreddit.db";
$url = "sqlite:" . $dbname;



try {
    $dbh = new PDO($url, $username, $password);
    $user = $_GET["username"];
    $pw = $_GET["password"];
    if (!isset($user) || !isset($pw)){
        die ("Missing parameters.");
    }
    $num=0;
    // Fix the below to use place holders, to protect agains sql injection!
    $sth =
        $dbh->prepare("SELECT count(*) as num FROM user WHERE username='" . $user . "' and password='" . $pw . "';");
    $sth->execute();
    $result = $sth->fetch(PDO::FETCH_OBJ);
    $num = $result->num;
    $stmt = null;
    
    if($num == 0) {
        echo "Wrong username or password. <a href=\"login.html\">Try again</a><br>\n";
    } else {
        echo "Welcome $username !<br>\n";
        echo "Login successful!<br>\n";
    }

    $dbh = null;
} catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "<br/>"; die();
}
?> </body></html>