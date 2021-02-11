package com.epam.brs.model.dao;

import com.epam.brs.model.dao.exception.DaoException;
import com.epam.brs.model.entity.User;

import java.util.Optional;

public interface UserDao {

    boolean containsUsername(String username) throws InterruptedException, DaoException;

    boolean contains(String username, String password);

}
