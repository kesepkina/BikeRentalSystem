package com.epam.brs.model.dao;

import com.epam.brs.model.entity.PriceList;

public interface PriceListDao extends BaseDao<Integer, PriceList>{

    boolean add(PriceList priceList) throws DaoException;

    boolean findId(PriceList priceList) throws DaoException;
}
