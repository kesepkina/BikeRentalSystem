package com.epam.brs.command;

import com.epam.brs.command.impl.*;
import com.epam.brs.model.service.impl.BicycleServiceImpl;
import com.epam.brs.model.service.impl.UserServiceImpl;

public enum CommandType {
    SIGNUP(new SignUpCommand(new UserServiceImpl())),
    LOGIN(new LogInCommand(new UserServiceImpl())),
    LOGOUT(new LogoutCommand()),
    SHOW_INFO(new ShowInfoCommand()),
    TO_MAIN(new ToPageCommand(PagePath.MAIN)),
    TO_LOGIN(new ToPageCommand(PagePath.LOGIN)),
    TO_SIGNUP(new ToPageCommand(PagePath.SIGNUP)),
    TO_PROFILE(new ToPageCommand(PagePath.PROFILE)),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    DISPLAY_BICYCLES(new DisplayBicyclesListCommand(new BicycleServiceImpl()));

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
