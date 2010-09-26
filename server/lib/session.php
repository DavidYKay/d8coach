<?php 
include 'db_connect.php';
function is_session_valid() {
$sesshash = mysql_real_escape_string($_COOKIE['sesshash']) ;
$uid = mysql_real_escape_string($_COOKIE['uid']) ; 
$query = "SELECT * FROM `sessions` WHERE sesshash LIKE '" . $sesshash . "';" ;
echo $query;
$res = mysql_query($query);
$array = mysql_fetch_assoc($res);
if ($array['uid'] != $uid) {
echo "Bad UID!";
return false;
}
if ($array['date'] >= time()) {
echo "BAD DATE!";
return false;
}

return true;
}

function create_new_session($uid) {
$date = date('U', time() + 604800) ;
$sesshash = md5($uid . $date ) ;
$query = "INSERT INTO sessions (uid, sesshash, expires) VALUES (" . $uid . ", '" . $sesshash . "' , " . $date . ") ;" ;
$res = mysql_query($query) ;
setcookie('uid', $uid) ;
setcookie('sesshash', $sesshash);
return $sesshash ;
}

function check_pw($user, $pw) {
$passhash = md5($pw) ;
$name = mysql_real_escape_string($user) ;
$query = "SELECT passhash from `login` where name like '" . $name . "' ;" ;
//echo $query . "\n";
$res= mysql_query($query) ;
$array = mysql_fetch_assoc($res) ;
//echo $passhash;
//echo $array['passhash'] ;
if ($array['passhash'] == $passhash ) {
return true;
} else {
return false;
}
}

function get_uid($user) {
$name = mysql_real_escape_string($user) ;
$query = "SELECT uid FROM login WHERE name LIKE '" . $name . "' ;" ;
//echo $query;
$res = mysql_query($query) or die ("could not complete query");
$array = mysql_fetch_assoc($res) ;
$uid = $array['uid'];
return  $uid;
}

/* Returns true if successful at creating a user, false if failed */
function create_player( $user, $pw) {
$passhash = md5($pw) ;
$name = mysql_real_escape_string($user) ;
$query = "SELECT count(name) as count from `login` where name like '" . $name . "' ;" ;
$res= mysql_query($query) ;
$array = mysql_fetch_assoc($res) ;
if ($array['count'] == 0 ) {
$query = " INSERT INTO `login` (name, passhash) VALUES ('" . $name . "' , '" . $passhash ."' ) ;" ;
echo $query;
mysql_query($query);
return true;
} else {
return false;
}
}

create_player('gw','foo');
?>
