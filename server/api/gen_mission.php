<?php
include 'base.php';

$query = "SELECT * FROM objectives ORDER BY random LIMIT 5" ;
$res = mysql_query($query) ;
while(
