package com.epam.brs.command.impl;

import com.epam.brs.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command {

    private static final String LOCALE = "locale";

    @Override
    public String execute(HttpServletRequest request) {
        String newLocale = request.getParameter(LOCALE);
        String page = request.getParameter("currentPage");
        request.getSession().setAttribute(LOCALE, newLocale);
        return page;
    }
}
