package com.epam.brs.model.service.impl;

import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.impl.PriceListDaoImpl;
import com.epam.brs.model.entity.PriceList;
import com.epam.brs.model.service.PriceListService;
import com.epam.brs.model.service.ServiceException;

import java.util.Optional;

public class PriceListServiceImpl implements PriceListService {
    @Override
    public Optional<PriceList> findById(int priceListId) throws ServiceException {
        Optional<PriceList> optionalPriceList;
        try {
            optionalPriceList = PriceListDaoImpl.getInstance().find(priceListId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalPriceList;
    }

}
