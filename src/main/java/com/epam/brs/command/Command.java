package com.epam.brs.command;

import com.epam.brs.model.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws CommandException;

    default void refresh() {
    } // return to the same page
}
