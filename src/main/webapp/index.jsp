<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fitness center</title>
    <link rel="stylesheet" href="static/styles/style.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap" rel="stylesheet">
</head>

<body>

<header class="header">
    <h1 class="header-title">Fitness center</h1>
    <p class="header-subtitle">${welcome}</p>
    <div class="dropdown">
        <button class="dropbtn">${language}</button>
        <div class="dropdown-content">
            <a href="#">${russian}</a>
            <a href="#">${english}</a>
            <a href="#">${deutsch}</a>
        </div>
    </div>
</header>

<main>
    <section class="content">
        <div class="login-container">
            <div class="error-login">
                ${errorMessage}
            </div>
            <form method="post" action="controller?command=login">
                <div class="login">
                    <lable class="login-text" for="login">${login}</lable>
                    <input class="input" type="text" name="login" placeholder="Your login.."/>
                </div>
                <div class="password">
                    <lable class="password-text" for="login">${password}</lable>
                    <input class="input" type="password" name="password" placeholder="Your password.."/>
                </div>
                <input class="login-button" type="submit" value=${login}>
            </form>
        </div>
    </section>
</main>

<footer class="footer">
    <div class="content">
        <div class="footer-row">
            <div class="footer-copyright footer-col">
                <div class="fotter-copyright-name">© Fitness center</div>
                <p>City City 1000 Street st. 11-11</p>
            </div>

            <div class="footer-contacts footer-col">
                <a href="fitness_center@gmail.com" class="footer-button">
                    ${contact}
                </a>
            </div>

        </div>
    </div>
</footer>

</body>
</html>