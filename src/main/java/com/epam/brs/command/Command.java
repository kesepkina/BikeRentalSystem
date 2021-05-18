package com.epam.brs.command;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Command {
    String execute(HttpServletRequest request) throws CommandException, IOException, ServletException;

    default void refresh() {
    } // return to the same page
}
