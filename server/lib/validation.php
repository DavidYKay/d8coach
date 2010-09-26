<?php
function valid_name($name) {
if ($name != (mysql_real_escape_string($name) {
return false;
}
if (strlen($name) >= 20) {
return false;
}

}
