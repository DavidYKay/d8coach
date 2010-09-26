<?php
include '../lib/session.php';

if ($_POST['user'] ) {
	if (check_pw($_POST['user'], $_POST['pw'] ) ) {
		$uid = get_uid($_POST['user']) ;
		$sesshash = create_new_session($uid) ;
		$jsonArray['uid']= $uid;
		$jsonArray['sesshash']= $sesshash;
		echo json_encode($jsonArray) ;
	} else { 
		/* Bad password page */
		print 'bad password' ;
	}
}
?>

