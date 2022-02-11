package com.epam.fitness.command;

import com.epam.fitness.service.UserServiceImpl;

public class CommandFactory {

    public Command createCommand(String command) {
        switch (command) {
            case "login":
                return new LoginCommand(new UserServiceImpl());
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
