package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.CommandException;
import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.impl.PriceListDaoImpl;
import com.epam.brs.model.entity.PriceList;
import com.epam.brs.model.service.ReservationService;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.ReservationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class CalculatePriceCommand implements Command {

    private ReservationService service;

    public CalculatePriceCommand() {
        this.service = new ReservationServiceImpl();
    }

    public double calcPrice(String pickUpRange, int priceListId) throws ServiceException {
        BigDecimal pricePerUnit;
        LocalDateTime returnTime;
        LocalDateTime pickUpTime;
        BigDecimal calculatedCost = BigDecimal.valueOf(0.0);
        try {
            Optional<PriceList> optionalPriceList = PriceListDaoImpl.getInstance().find(priceListId);
            if (optionalPriceList.isPresent()) {
                String[] dateRange = pickUpRange.split(" - ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy H:mm");
                pickUpTime = LocalDateTime.parse(dateRange[0], formatter);
                returnTime = LocalDateTime.parse(dateRange[1], formatter);
                LocalDateTime tempDateTime = LocalDateTime.from( pickUpTime );

                long weeks = tempDateTime.until( returnTime, ChronoUnit.WEEKS );
                tempDateTime = tempDateTime.plusWeeks( weeks );
                if (weeks > 0) {
                    pricePerUnit = optionalPriceList.get().getPricePerWeek();
                    calculatedCost = calculatedCost.add(pricePerUnit.multiply(BigDecimal.valueOf(weeks)));
                }

                long days = tempDateTime.until( returnTime, ChronoUnit.DAYS );
                tempDateTime = tempDateTime.plusDays( days );
                if (days > 0) {
                    pricePerUnit = optionalPriceList.get().getPricePerDay();
                    calculatedCost = calculatedCost.add(pricePerUnit.multiply(BigDecimal.valueOf(days)));
                }

                long hours = tempDateTime.until( returnTime, ChronoUnit.HOURS );
                if (hours > 0) {
                    pricePerUnit = optionalPriceList.get().getPricePerHour();
                    calculatedCost = calculatedCost.add(pricePerUnit.multiply(BigDecimal.valueOf(hours)));
                }
            } else {
                throw new ServiceException("Couldn't find price list");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return calculatedCost.doubleValue();
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
        return null;
    }
}
