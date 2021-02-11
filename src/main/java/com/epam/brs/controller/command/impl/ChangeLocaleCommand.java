package com.epam.brs.controller.command.impl;

import com.epam.brs.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.brs.controller.command.PagePath.ERROR;

public class ChangeLocaleCommand implements Command {

    private static final String LOCALE = "locale";

    @Override
    public String execute(HttpServletRequest request) {
        String newLocale = request.getParameter(LOCALE);
        //TODO: how to do it correctly?
        Pattern pattern = Pattern.compile("/jsp/");
        String fullPagePath = request.getParameter("currentPage");
        Matcher matcher = pattern.matcher(fullPagePath);
        String page = ERROR;
        if(matcher.find()) {
            page = fullPagePath.substring(matcher.start());
        }
        request.getSession().setAttribute(LOCALE, newLocale);
        return page;
    }
}
