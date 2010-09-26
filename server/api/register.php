<?php
include "../lib/db_connect.php";
include "../lib/validation.php";

$jsonArray = json_decode($_POST['jsonReg']);
if (valid_name($jsonArray['name'] )) {
$passhash = md5($jsonArray['pw']) ;
$query = "INSERT INTO login (name, passhash) VALUES ('" . $name . "','" . $passhash . "') ;" ;
mysql_query( $query) ;
$res = mysql_affected_rows();
}
?>
