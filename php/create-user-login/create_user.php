<!DOCTYPE html>
<html><head><title>Create user</title></head>
<body>

<?php

$password = null;
$username = null;
$dbname   = "myreddit.db";
$url = "sqlite:" . $dbname;



try {
    $dbh = new PDO($url, $username, $password);
    $user = $_GET["username"];
    $num=0;
    $sth =
        $dbh->prepare("SELECT count(*) as num FROM user WHERE username='$user';");
    $sth->execute();
    $result = $sth->fetch(PDO::FETCH_OBJ);
    $num = $result->num;
    $stmt = null;

    if($num != 0) {
        echo "Username taken";
    } else {
        echo "Welcome $user !<br>\n";
        echo "Please enter your information below...<br>\n";
        echo "...here's a good place to put a form for name, email etc<br>\n";
    }

    $dbh = null;
} catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "<br/>"; die();
}
?> </body></html>