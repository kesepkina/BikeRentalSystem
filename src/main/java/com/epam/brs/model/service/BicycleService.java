package com.epam.brs.model.service;

import com.epam.brs.model.entity.Bicycle;

import java.util.List;
import java.util.Optional;

public interface BicycleService {

    List<Bicycle> findAll(String bikeType) throws ServiceException;

    Optional<Bicycle> findBicycle(int id) throws ServiceException;

}
