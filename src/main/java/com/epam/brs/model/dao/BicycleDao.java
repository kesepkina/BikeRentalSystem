package com.epam.brs.model.dao;

import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.entity.enumType.BicycleType;

import java.util.List;

public interface BicycleDao extends BaseDao<Integer, Bicycle> {

    boolean addBicycle(Bicycle bicycle) throws DaoException;

    List<Bicycle> findByFilters(BicycleType type) throws DaoException;

}
