package com.epam.fitness.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The interface Client Command
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
