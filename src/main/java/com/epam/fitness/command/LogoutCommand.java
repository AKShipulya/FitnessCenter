package com.epam.fitness.command;

import com.epam.fitness.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command{

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.getSession().invalidate();
        return CommandResult.forward("/index.jsp");
    }
}
