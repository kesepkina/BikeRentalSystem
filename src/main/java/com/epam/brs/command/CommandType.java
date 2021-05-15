package com.epam.brs.command;

import com.epam.brs.command.impl.*;
import com.epam.brs.model.service.impl.BicycleServiceImpl;
import com.epam.brs.model.service.impl.ReservationServiceImpl;
import com.epam.brs.model.service.impl.UserServiceImpl;

public enum CommandType {
    SIGNUP(new SignUpCommand(new UserServiceImpl())),
    LOGIN(new LogInCommand(new UserServiceImpl())),
    LOGOUT(new LogoutCommand()),
    TO_MAIN(new ToPageCommand(PagePath.MAIN)),
    TO_LOGIN(new ToPageCommand(PagePath.LOGIN)),
    TO_SIGNUP(new ToPageCommand(PagePath.SIGNUP)),
    TO_PROFILE(new ToPageCommand(PagePath.PROFILE)),
    TO_BICYCLE(new ToBicycleInfoCommand(new BicycleServiceImpl())),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    DISPLAY_BICYCLES(new DisplayBicyclesListCommand(new BicycleServiceImpl())),
    RENT(new AddReservationCommand(new ReservationServiceImpl())),
    TO_ORDERS(new ToOrdersCommand(new ReservationServiceImpl())),
    TO_BICYCLES(new DisplayBicyclesListCommand(new BicycleServiceImpl())),
    TO_USERS(new DisplayUsersListCommand(new UserServiceImpl())),
    TO_ADMIN_MAIN(new ToPageCommand(PagePath.ADMIN_MAIN));

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
