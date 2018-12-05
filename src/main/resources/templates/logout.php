<?php 
    session_start();
unset($_SESSION['username']);
unset($_SESSION['password']);
$_SESSION['logged'] = false;
$msg = 'logged out';
header("Location: index.php");
?>