package com.epam.brs.command;

import com.epam.brs.command.impl.*;
import com.epam.brs.model.service.impl.UserServiceImpl;

public enum CommandType {
    SIGNUP(new LogInCommand(new UserServiceImpl())),
    LOGIN(new SignUpCommand(new UserServiceImpl())),
    LOGOUT(new LogoutCommand()),
    SHOW_INFO(new ShowInfoCommand()),
    TO_MAIN(new ToPageCommand(PagePath.MAIN)),
    TO_LOGIN(new ToPageCommand(PagePath.LOGIN)),
    TO_SIGNUP(new ToPageCommand(PagePath.SIGNUP)),
    CHANGE_LOCALE(new ChangeLocaleCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
