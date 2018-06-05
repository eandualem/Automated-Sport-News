<?php
/* sql connection settings */
$host = 'localhost:8889';
$user = 'root';
$pass = 'root';
$db = 'news';
$mysqli = new mysqli($host,$user,$pass,$db);
// Check connection
if ($mysqli->connect_error) {
    die("Connection failed: " . $mysqli->connect_error);
}
