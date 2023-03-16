package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.CommandException;
import com.epam.brs.model.service.BicycleService;
import com.epam.brs.model.service.ReservationService;
import com.epam.brs.model.service.impl.BicycleServiceImpl;
import com.epam.brs.model.service.impl.ReservationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PrintRentContractCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final ReservationService service;

    public PrintRentContractCommand(ReservationServiceImpl service) {
        this.service = service;
    }
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

    }
}
