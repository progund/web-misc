<!DOCTYPE html>
<html><head><title>Products from SQLite Database</title></head>
<body>

<?php

$username = null;
$password   = null;
$dbname   = "bolaget.db";
$url = "sqlite:" . $dbname;



try {
    $dbh = new PDO($url, $username, $password);
    print "Got connection: " .
        " Server version: " .
        $dbh->getAttribute(constant("PDO::ATTR_SERVER_VERSION")) . "<br />";
    $min_alcohol = $_GET["min_alcohol"];
    if(!isset($min_alcohol)){
        $min_alcohol = "0";
    }
    echo "<p>Minimum alkohol: $min_alcohol</p>\n";
    echo "<table border=\"1\">";
    echo "<tr><th>Varunamn</th><th>Pris</th><th>Alkoholhalt</th></tr>";
    foreach($dbh->query('SELECT name, price, alcohol FROM product WHERE alcohol >= ' . $min_alcohol .' LIMIT 10') as $row) {
        $name = $row["name"];
        $price = $row["price"];
        $alcohol = $row["alcohol"];
        echo "<tr><td>$name</td><td>$price kr</td><td>$alcohol %</td></tr>\n";
    }
    echo "</table>";
/*
// SQL Injection:
// We are using $min_alcohol which is a GET parameter which could contains SQL. Not good.
    
// Prepared statement protects against SQL injections:
// Example with a different table:
    $stmt = $dbh->prepare("SELECT url from links where id = :id");
    $stmt->bindParam(":id", $id);
    $stmt->execute();
    $row=null;
    while ($row = $stmt->fetch()) {
        echo "<br>id: " . $id . "<br>URL: " . $row["url"] . "<br>";
    }
    if ($stmt->rowCount() == 0) {
        echo "<br><b>NO ROWS FOUND</b><br>";
    }
*/
    $dbh = null;
} catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "<br/>"; die();
}
?> </body></html>