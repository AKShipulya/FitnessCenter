package com.epam.fitness.command;

import com.epam.fitness.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCommand implements Command{

    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        boolean valid = service.login(login, password);
        if (valid) {
            request.getSession().setAttribute("user", "admin");
            return "WEB-INF/view/main.jsp";
        } else {
            request.setAttribute("errorMessage", "Invalid credentials");
            return "index.jsp";
        }
    }
}
