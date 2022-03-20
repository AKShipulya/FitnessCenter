package com.epam.fitness.command;

import com.epam.fitness.dao.DaoHelperFactory;
import com.epam.fitness.service.UserServiceImpl;

public class CommandFactory {

    private DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    public CommandFactory(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Command createCommand(String command) {
        switch (command) {
            case "login":
                return new LoginCommand(new UserServiceImpl(daoHelperFactory));
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
