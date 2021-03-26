package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LogoutCommand implements Command {

    private static final Logger log = LogManager.getLogger();
    private static final Pattern JSP_PATTERN = Pattern.compile("/WEB-INF/jsp/.*\\.jsp");

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Logged out");
        String fullPagePath = (String) request.getSession().getAttribute(SessionAttribute.CURRENT_PAGE);
        Matcher matcher = JSP_PATTERN.matcher(fullPagePath);
        String page = null;
        if (matcher.find()) {
            page = matcher.group();
        }
        request.getSession().invalidate();
        request.getSession().setAttribute("user.role", "GUEST");
        return page;
    }
}
