package com.epam.brs.model.service;

import com.epam.brs.model.entity.Bicycle;

import java.util.List;

public interface BicycleService {

    List<Bicycle> findAll() throws ServiceException;

}
