package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.RequestParameter;
import com.epam.brs.command.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeLocaleCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String newLocale = request.getParameter(RequestParameter.LOCALE);
        request.getSession().setAttribute(SessionAttribute.LOCALE, newLocale);
        String language = newLocale.substring(0,2);
        logger.debug("Locale changed to {}", newLocale);
        request.getSession().setAttribute(SessionAttribute.LANGUAGE, language);
        logger.debug("Set language = {}", language);
        //TODO: how to do it correctly?
        Pattern pattern = Pattern.compile("/WEB-INF/jsp/.*\\.jsp");
        String fullPagePath = request.getParameter("currentPage");
        Matcher matcher = pattern.matcher(fullPagePath);
        String page = null;
        if (matcher.find()) {
            page = matcher.group();
        }
        return page;
    }
}
