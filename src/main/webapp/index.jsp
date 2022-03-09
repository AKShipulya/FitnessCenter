<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./static/css/reset.css">
    <link rel="stylesheet" href="./static/css/main.css">
    <title>Fitness center</title>
</head>
<body>
<header class="header">
    <div class="wrapper">

        <div class="header_wrapper">

            <div class="header_logo">
                <a href="index.jsp" class="header_logo-link">
                    <img src="./static/img/svg/logo_welcome.svg" alt="Fitness center" class="logo_logo-pic">
                </a>
            </div>

            <nav class="header_nav">
                <ul class="header_list">
                    <li class="header_item">
                        <div class="dropdown">
                            <button class="dropbtn">language</button>
                            <div class="dropdown-content">
                                <a href="#">english</a>
                                <a href="#">deutsch</a>
                                <a href="#">russian</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </nav>

        </div>

    </div>
</header>

<main class="wrapper">
    <div class="login-container">
        <div class="error-login">
            ${errorMessage}
        </div>
        <form method="post" action="controller?command=login">
            <div class="login">
                <lable class="login-text" for="login">login:</lable>
                <input class="input" type="text" name="login" placeholder="Your login..."/>
            </div>
            <div class="password">
                <lable class="password-text" for="login">password:</lable>
                <input class="input" type="password" name="password" placeholder="Your password..."/>
            </div>
            <input class="login-button" type="submit" value=login>
        </form>
    </div>
</main>

<footer>
    <div class="footer">
        <img src="./static/img/svg/copyright.svg" alt="Fitness center">
    </div>
</footer>
</body>
</html>