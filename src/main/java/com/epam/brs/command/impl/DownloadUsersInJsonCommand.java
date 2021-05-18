package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.CommandException;
import com.epam.brs.command.PagePath;
import com.epam.brs.model.service.ReservationService;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.UserService;
import com.epam.brs.model.service.impl.ReservationServiceImpl;
import com.epam.brs.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DownloadUsersInJsonCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final UserService service;

    public DownloadUsersInJsonCommand(UserServiceImpl service) {
        this.service = service;
    }
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        boolean downloaded;
        try {
            downloaded = service.downloadTableAsJSON();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (downloaded) {
            request.setAttribute("successDownload", "yes!");
        } else {
            request.setAttribute("successDownload", "no");
        }
        return PagePath.USERS;
    }
}
