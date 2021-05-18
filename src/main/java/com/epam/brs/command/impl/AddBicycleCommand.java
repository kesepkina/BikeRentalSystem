package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.CommandException;
import com.epam.brs.command.RequestParameter;
import com.epam.brs.command.SessionAttribute;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.BicycleServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.epam.brs.command.PagePath.*;
import static com.epam.brs.command.BicycleDataMapKeyword.*;

public class AddBicycleCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final BicycleServiceImpl service;
    private static final String DASH = "-";
    private static final String SLASH = "/";

    public AddBicycleCommand(BicycleServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
        String brand = request.getParameter(RequestParameter.BRAND);
        String model = request.getParameter(RequestParameter.MODEL);
        String type = request.getParameter(RequestParameter.TYPE);
        String pricePerHour = request.getParameter(RequestParameter.PRICE_PER_HOUR);
        String pricePerDay = request.getParameter(RequestParameter.PRICE_PER_DAY);
        String pricePerWeek = request.getParameter(RequestParameter.PRICE_PER_WEEK);
        String deposit = request.getParameter(RequestParameter.DEPOSIT);
        String releaseYear = request.getParameter(RequestParameter.RELEASE_YEAR);
        String purchaseYear = request.getParameter(RequestParameter.PURCHASE_YEAR);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        Map<String, String> bicycleData = new HashMap<>();
        bicycleData.put(BRAND, brand);
        bicycleData.put(MODEL, model);
        bicycleData.put(TYPE, type);
        bicycleData.put(PRICE_PER_HOUR, pricePerHour);
        bicycleData.put(PRICE_PER_DAY, pricePerDay);
        bicycleData.put(PRICE_PER_WEEK, pricePerWeek);
        bicycleData.put(DEPOSIT, deposit);
        bicycleData.put(RELEASE_YEAR, releaseYear);
        bicycleData.put(PURCHASE_YEAR, purchaseYear);
        bicycleData.put(DESCRIPTION, description);
        boolean signedUpSuccessfully;
        String page = ADDING_BICYCLE;
        try {
            signedUpSuccessfully = service.addBicycle(bicycleData);
            if (signedUpSuccessfully) {
                page = ADDING_BICYCLE_PHOTO;
            } else {
                request.setAttribute("success", "no");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
