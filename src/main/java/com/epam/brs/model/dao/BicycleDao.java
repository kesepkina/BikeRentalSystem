package com.epam.brs.model.dao;

import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.entity.BicycleType;

import java.util.List;

public interface BicycleDao extends BaseDao<Integer, Bicycle> {

    List<Bicycle> findByFilters(BicycleType type) throws DaoException;

}
