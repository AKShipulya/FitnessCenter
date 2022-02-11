<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Fitness center</title>
        <link rel="stylesheet" href="static/styles/style.css"/>
    </head>
    <body>
    <div class="container">
        <form method="post" action="controller?command=login">
            <lable class="login-row" for="login">Login</lable>
            <input class="login-row" type="text" name="login"/>
            <lable class="login-row" for="login">Password</lable>
            <input class="login-row" type="password" name="password"/>
            <input class="login-row" type="submit"/>
        </form>
        <div style="color: red">${errorMessage}</div>
    </div>
    </body>
</html>