<?php
include '../lib/session.php' ;

if (is_session_valid()) {
$jsonArray['valid'] = 1;
} else {
$jsonArray['valid'] = 0;
}
echo json_encode($jsonArray);
?>
