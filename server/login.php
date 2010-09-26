<?php
include 'lib/session.php';

if ($_POST['user'] ) {
	if (check_pw($_POST['user'], $_POST['pw'] ) ) {
		$uid = get_uid($_POST['user']) ;
		create_new_session($uid) ;
	} else { 
		/* Bad password page */
		print 'bad password' ;
	}
} else {
/* Login page */ ?>
<html> <head> <title> Login page </title> </head>
<body> <form action='login.php' method='POST'> User:<input type='text' name='user'/> <br/>Password:<input type='password' name='pw'/> <br/> <input type='submit'/></body> </html>
<? }
?>

