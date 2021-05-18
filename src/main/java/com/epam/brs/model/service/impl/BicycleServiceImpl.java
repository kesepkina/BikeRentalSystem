package com.epam.brs.model.service.impl;

import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.impl.BicycleDaoImpl;
import com.epam.brs.model.dao.impl.PriceListDaoImpl;
import com.epam.brs.model.dao.impl.UserDaoImpl;
import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.entity.PriceList;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.entity.enumType.BicycleType;
import com.epam.brs.model.service.BicycleService;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.util.Encryptor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.epam.brs.command.BicycleDataMapKeyword.*;

public class BicycleServiceImpl implements BicycleService {

    @Override
    public boolean addBicycle(Map<String, String> bicycleData) throws ServiceException {
        boolean successfullyAdded = true;
        String brand = bicycleData.get(BRAND);
        String model = bicycleData.get(MODEL);
        BicycleType type = BicycleType.valueOf(bicycleData.get(TYPE));
        BigDecimal pricePerHour = BigDecimal.valueOf(Long.parseLong(bicycleData.get(PRICE_PER_HOUR)));
        BigDecimal pricePerDay = BigDecimal.valueOf(Long.parseLong(bicycleData.get(PRICE_PER_DAY)));
        BigDecimal pricePerWeek = BigDecimal.valueOf(Long.parseLong(bicycleData.get(PRICE_PER_WEEK)));
        BigDecimal deposit = BigDecimal.valueOf(Long.parseLong(bicycleData.get(DEPOSIT)));
        int releaseYear = Integer.parseInt(bicycleData.get(RELEASE_YEAR));
        int purchaseYear = Integer.parseInt(bicycleData.get(PURCHASE_YEAR));
        String imagePath = null;
        String description = bicycleData.get(DESCRIPTION);
        PriceList priceList = new PriceList(deposit, pricePerHour, pricePerDay, pricePerWeek);
        try {
            successfullyAdded = PriceListDaoImpl.getInstance().add(priceList);
            if (successfullyAdded) {
                successfullyAdded = PriceListDaoImpl.getInstance().findId(priceList);
                if(successfullyAdded) {
                    Bicycle bicycle = new Bicycle(brand, model, releaseYear, purchaseYear, description, type, imagePath, priceList.getPriceListId());
                    successfullyAdded = BicycleDaoImpl.getInstance().addBicycle(bicycle);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return successfullyAdded;
    }

    @Override
    public List<Bicycle> findAll(String bikeType) throws ServiceException {
        List<Bicycle> bicycleList;
        try {
            if (bikeType.equals("all")) {
                bicycleList = BicycleDaoImpl.getInstance().findAll();
            } else {
                BicycleType type = BicycleType.valueOf(bikeType);
                bicycleList = BicycleDaoImpl.getInstance().findByFilters(type);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bicycleList;
    }

    @Override
    public Optional<Bicycle> findBicycle(int id) throws ServiceException {
        Optional<Bicycle> optionalBicycle;
        try {
            optionalBicycle = BicycleDaoImpl.getInstance().find(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalBicycle;
    }
}
