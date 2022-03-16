package com.epam.fitness.command;

import com.epam.fitness.entity.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class LoginCommand implements Command{

    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<User> user = service.login(login, password);
        CommandResult result;
        if (user.isPresent()) {
            request.getSession().setAttribute("user", user.get());
            result = CommandResult.redirect("controller?command=mainPage");
        } else {
            request.setAttribute("errorMessage", "Invalid login or password");
            result = CommandResult.forward("/index.jsp");
        }
        return result;
    }
}
