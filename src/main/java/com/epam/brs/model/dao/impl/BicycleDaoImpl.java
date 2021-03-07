package com.epam.brs.model.dao.impl;

import com.epam.brs.model.dao.BicycleDao;
import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.entity.BicycleType;
import com.epam.brs.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BicycleDaoImpl implements BicycleDao {

    private static final Logger logger = LogManager.getLogger();

    private static final BicycleDao instance = new BicycleDaoImpl();

    @Language("MySQL")
    private static final String FIND_ALL_SQL_QUERY = "SELECT id_bicycle, brand, model, type, image_path, id_point, id_price_list FROM bicycles";

    private BicycleDaoImpl() {
    }

    public static BicycleDao getInstance() {
        return instance;
    }

    @Override
    public List<Bicycle> findAll() throws DaoException {
        List<Bicycle> bicycleList = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL_QUERY);
            while(resultSet.next()) {
                int bicycleId = resultSet.getInt(1);
                String brand = resultSet.getNString(2);
                String model = resultSet.getNString(3);
                BicycleType bicycleType = BicycleType.valueOf(resultSet.getNString(4));
                String imagePath = resultSet.getNString(5);
                int rentalPointId = resultSet.getInt(6);
                int priceListId = resultSet.getInt(7);
                Bicycle bicycle = new Bicycle(bicycleId, brand, model, bicycleType, imagePath, rentalPointId, priceListId);
                bicycleList.add(bicycle);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return bicycleList;
    }

    @Override
    public Bicycle find(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Bicycle entity) {
        return false;
    }

    @Override
    public Bicycle update(Bicycle entity) {
        return null;
    }

    @Override
    public List<Bicycle> findByFilters(BicycleType type) {
        return null;
    }
}
