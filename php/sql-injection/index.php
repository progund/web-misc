<!DOCTYPE html>
<html><head><title>Products from SQLite Database</title></head>
<body>

<?php

$username = null;
$password   = null;
$dbname   = "cars.db";
$url = "sqlite:" . $dbname;



try {
    $dbh = new PDO($url, $username, $password);
    print "Got connection: " .
        " Server version: " .
        $dbh->getAttribute(constant("PDO::ATTR_SERVER_VERSION")) . "<br />";
    $make = $_GET["make"];
    if(!isset($make)){
        $make = "";
    }
    echo "<p>Searched for Make: $make</p>\n";
    echo "<table border=\"1\">";
    echo "<tr><th>id</th><th>make</th><th>license</th></tr>";
    //Vulnerable code - uses a variable inside the SQL string:
    foreach($dbh->query("SELECT * FROM cars WHERE make = '$make' LIMIT 10") as $row) {
        $id = $row["id"];
        $theMake = $row["make"];
        $license = $row["license"];
        echo "<tr><td>$id</td><td>$theMake</td><td>$license</td></tr>\n";
    }
    $stmt = null;
/*    
// Prepared statement protects against SQL injections (and is more efficient):
    $stmt = $dbh->prepare("SELECT * from cars where make = :make LIMIT 10");
    $stmt->bindParam(":make", $make);
    $stmt->execute();
    $row=null;
    while ($row = $stmt->fetch()) {
        $id = $row["id"];
        $theMake = $row["make"];
        $license = $row["license"];
        echo "<tr><td>$id</td><td>$theMake</td><td>$license</td></tr>\n";
    }
*/
    echo "</table>";

    if ($stmt != null && $stmt->rowCount() == 0) {
        echo "<br><b>NO ROWS FOUND</b><br>";
    }

    $dbh = null;
} catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "<br/>"; die();
}
?> </body></html>