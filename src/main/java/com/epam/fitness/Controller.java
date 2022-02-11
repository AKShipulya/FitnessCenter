package com.epam.fitness;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) {
        process(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        try {
            String commandParam = request.getParameter("command");
            CommandFactory commandFactory = new CommandFactory();
            Command command = commandFactory.createCommand(commandParam);
            String page = command.execute(request, response);
            dispatch(page, request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void dispatch(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
