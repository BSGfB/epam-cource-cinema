<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cinema login</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="../../../resources/style/login.css">
</head>
<body>
    <form name="f" action="/login" method="POST">
        <div class="header" align="center">
            <h1>Cinema</h1>
            <h3>SPRING ADVANCED #26.2</h3>
        </div>
        <input class="in" type="text" placeholder="username" name="username" required="">
        <input class="in" type="password" placeholder="password" name="password" required="">

        <div class="rememberMeForm">
            <input id="rememberMe" class="rememberMe" type="checkbox" name="remember-me">
            <label for="rememberMe">Remember Me?</label>
        </div>


        <input class="submit" name="submit" type="submit" value="Login">
    </form>
</body>