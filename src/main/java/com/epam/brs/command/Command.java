package com.epam.brs.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request);

    default void refresh() {
    } // return to the same page
}
