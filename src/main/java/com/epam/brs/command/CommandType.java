package com.epam.brs.command;

import com.epam.brs.command.impl.*;
import com.epam.brs.model.service.impl.BicycleServiceImpl;
import com.epam.brs.model.service.impl.PriceListServiceImpl;
import com.epam.brs.model.service.impl.ReservationServiceImpl;
import com.epam.brs.model.service.impl.UserServiceImpl;

public enum CommandType {
    SIGNUP(new SignUpCommand(new UserServiceImpl())),
    LOGIN(new LogInCommand(new UserServiceImpl())),
    LOGOUT(new LogoutCommand()),
    TO_MAIN(new ToPageCommand(PagePath.MAIN)),
    TO_LOGIN(new ToPageCommand(PagePath.LOGIN)),
    TO_SIGNUP(new ToPageCommand(PagePath.SIGNUP)),
    TO_PROFILE(new DisplayUserOrdersCommand(new ReservationServiceImpl())),
    TO_BICYCLE(new ToBicycleInfoCommand(new BicycleServiceImpl(), new PriceListServiceImpl())),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    DISPLAY_BICYCLES(new DisplayBicyclesListCommand(new BicycleServiceImpl())),
    RENT(new AddReservationCommand(new ReservationServiceImpl())),
    TO_ORDERS(new ToOrdersCommand(new ReservationServiceImpl())),
    TO_BICYCLES(new DisplayBicyclesListCommand(new BicycleServiceImpl())),
    TO_USERS(new DisplayUsersListCommand(new UserServiceImpl())),
    TO_ADMIN_MAIN(new ToPageCommand(PagePath.ADMIN_MAIN)),
    DELETE_ORDER(new DeleteReservationCommand(new ReservationServiceImpl())),
    CHANGE_ORDER_STATUS(new ChangeReservationStatusCommand(new ReservationServiceImpl())),
    TO_ADMINSIGNUP(new ToPageCommand(PagePath.ADMIN_SIGNUP)),
    DOWNLOAD_ORDERS(new DownloadOrdersInJsonCommand(new ReservationServiceImpl())),
    DOWNLOAD_USERS(new DownloadUsersInJsonCommand(new UserServiceImpl())),
    DOWNLOAD_BICYCLES(new DownloadBicyclesInJsonCommand(new BicycleServiceImpl())),
    TO_ADDING_BICYCLE(new ToPageCommand(PagePath.ADDING_BICYCLE)),
    ADD_BICYCLE(new AddBicycleCommand(new BicycleServiceImpl()));

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
