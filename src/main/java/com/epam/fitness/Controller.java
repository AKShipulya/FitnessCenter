package com.epam.fitness;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandFactory;
import com.epam.fitness.command.CommandResult;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        process(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String command = request.getParameter("command");
        CommandFactory commandFactory = new CommandFactory();
        Command action = commandFactory.createCommand(command);
        try {
            CommandResult result = action.execute(request, response);
            dispatch(request, response, result);
        } catch (Exception exception) {
            request.setAttribute("errorMessage", exception.getMessage());
            dispatch(request, response, CommandResult.forward("/error.jsp"));
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, CommandResult result) throws ServletException, IOException {
        String page = result.getPage();
        if (!result.isRedirect()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(page);
        }
    }
}
