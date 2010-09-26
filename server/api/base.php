<?php

function check_session($uid, $sesshash) {
$query = "SELECT * FROM `sessions` WHERE sesshash LIKE '" . $sesshash . "';" ;
//echo $query;
$res = mysql_query($query);
$array = mysql_fetch_assoc($res);
if ($array['uid'] != $uid) {
//echo "Bad UID!";
return false;
}
if ($array['date'] >= time()) {
//echo "BAD DATE!";
return false;
}

function loginredir() {
echo "Error: Not logged in!";
die();
}
$jsonArray = json_decode($_POST['authJson']);
$uid = $jsonArray['uid'] ;
$sesshash = $jsonArray['sesshash'] ;
if (! check_session($uid, $sesshash) ) {
loginredir();
}
