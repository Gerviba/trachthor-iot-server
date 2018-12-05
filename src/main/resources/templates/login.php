<?php
      $msg = 'login....';
      
      if(isset($_POST['submit'])){
          if(isset($_POST['username']) && isset($_POST['password'])){
              $username = trim($_POST['username']);
              $password = trim($_POST['password']);
              if($username == 'admin' && $password == 'abcd1234'){
                  session_start();
                  $_SESSION['username'] = $username;
                  $_SESSION['logged'] = true;
                  $msg = 'logged in!';
                  header('Location: map.php');
                  exit;
              }
              else{
                  $msg = "wrong psw";
                  header('Location: index.php');
                  exit;
              }
          }
      }
            
    
         ?>