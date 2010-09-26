<?php
include 'lib/session.php' ;

if (is_session_valid()) {
echo "Valid Session!" ;
} else {
echo "Bad Session!" ;
}
?>
