package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.PagePath;
import com.epam.brs.command.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class LogoutCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute(SessionAttribute.LOCALE);
        request.getSession().invalidate();
        log.debug("Logged out");
        request.getSession().setAttribute(SessionAttribute.USER_ROLE, "GUEST");
        request.getSession().setAttribute(SessionAttribute.LOCALE, locale);
        return PagePath.MAIN;
    }
}
