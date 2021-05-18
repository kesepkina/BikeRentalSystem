package com.epam.brs.model.service;

import com.epam.brs.model.entity.Bicycle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BicycleService {

    boolean addBicycle(Map<String, String> bicycleData) throws ServiceException;

    List<Bicycle> findAll(String bikeType) throws ServiceException;

    Optional<Bicycle> findBicycle(int id) throws ServiceException;

}
