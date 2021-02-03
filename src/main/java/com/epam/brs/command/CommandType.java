package com.epam.brs.command;

import com.epam.brs.command.impl.*;
import com.epam.brs.service.impl.UserServiceImpl;

public enum CommandType {
    SIGNUP(new LogInCommand(new UserServiceImpl())),
    LOGIN(new SignUpCommand(new UserServiceImpl())),
    LOGOUT(new LogoutCommand()),
    SHOW_INFO(new ShowInfoCommand()),
    MAIN_PAGE(new PageCommand(PagePath.MAIN)),
    LOGIN_PAGE(new PageCommand(PagePath.LOGIN)),
    SIGNUP_PAGE(new PageCommand(PagePath.SIGNUP)),
    CHANGE_LOCALE(new ChangeLocaleCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
