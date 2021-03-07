package com.epam.brs.model.service.impl;

import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.impl.BicycleDaoImpl;
import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.service.BicycleService;
import com.epam.brs.model.service.ServiceException;

import java.util.List;

public class BicycleServiceImpl implements BicycleService {
    @Override
    public List<Bicycle> findAll() throws ServiceException {
        List<Bicycle> bicycleList;
        try {
            bicycleList = BicycleDaoImpl.getInstance().findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bicycleList;
    }
}
