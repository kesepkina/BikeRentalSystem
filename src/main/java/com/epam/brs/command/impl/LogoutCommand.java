package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.PagePath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class LogoutCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.LOGIN;
        request.getSession().invalidate();
        request.getSession().setAttribute("user_role", "GUEST");
        log.debug("Logged out");
        return page;
    }
}
