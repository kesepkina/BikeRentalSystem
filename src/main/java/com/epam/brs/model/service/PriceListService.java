package com.epam.brs.model.service;

import com.epam.brs.model.entity.PriceList;

import java.util.Optional;

public interface PriceListService {

    Optional<PriceList> findById(int priceListId) throws ServiceException;

}
