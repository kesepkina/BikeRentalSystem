package com.epam.brs.model.service.impl;

import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.impl.BicycleDaoImpl;
import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.entity.BicycleType;
import com.epam.brs.model.service.BicycleService;
import com.epam.brs.model.service.ServiceException;

import java.util.List;
import java.util.Optional;

public class BicycleServiceImpl implements BicycleService {

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
