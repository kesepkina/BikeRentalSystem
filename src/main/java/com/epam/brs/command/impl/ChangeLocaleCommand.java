package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.brs.command.PagePath.ERROR;

public class ChangeLocaleCommand implements Command {

    private static final String LOCALE = "locale";
    private static final String LANGUAGE = "lang";

    @Override
    public String execute(HttpServletRequest request) {
        String newLocale = request.getParameter(SessionAttribute.LOCALE);
        request.getSession().setAttribute(LOCALE, newLocale);
        String language = newLocale.substring(0,2);
        request.getSession().setAttribute(LANGUAGE, language);
        //TODO: how to do it correctly?
        Pattern pattern = Pattern.compile("/jsp/");
        String fullPagePath = request.getParameter("currentPage");
        Matcher matcher = pattern.matcher(fullPagePath);
        String page = ERROR;
        if(matcher.find()) {
            page = fullPagePath.substring(matcher.start());
        }
        return page;
    }
}
