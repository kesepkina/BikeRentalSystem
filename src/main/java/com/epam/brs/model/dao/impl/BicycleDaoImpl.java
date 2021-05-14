package com.epam.brs.model.dao.impl;

import com.epam.brs.model.dao.BicycleDao;
import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.entity.BicycleType;
import com.epam.brs.model.pool.ConnectionPool;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BicycleDaoImpl implements BicycleDao {

    private static final BicycleDao instance = new BicycleDaoImpl();

    @Language("MySQL")
    private static final String FIND_ALL_SQL_QUERY = "SELECT id_bicycle, brand, model, type, image_path, id_point, id_price_list FROM bicycles";
    @Language("MySQL")
    private static final String FIND_BY_TYPE_SQL_QUERY = "SELECT id_bicycle, brand, model, type, image_path, id_point, id_price_list FROM bicycles WHERE type=?";
    @Language("MySQL")
    private static final String FIND_BY_ID_SQL_QUERY = "SELECT brand, model, release_year, purchase_year, description, type, image_path, id_point, id_price_list FROM bicycles WHERE id_bicycle=?";


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
            bicycleList = completeList(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return bicycleList;
    }

    @Override
    public List<Bicycle> findByFilters(BicycleType type) throws DaoException {
        List<Bicycle> bicycleList = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_TYPE_SQL_QUERY)) {
            statement.setString(1, type.toString());
            ResultSet resultSet = statement.executeQuery();
            bicycleList = completeList(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return bicycleList;
    }

    private List<Bicycle> completeList(ResultSet bicycles) throws SQLException {
        List<Bicycle> bicycleList = new ArrayList<>();
        while(bicycles.next()) {
            int bicycleId = bicycles.getInt(1);
            String brand = bicycles.getNString(2);
            String model = bicycles.getNString(3);
            BicycleType bicycleType = BicycleType.valueOf(bicycles.getNString(4));
            String imagePath = bicycles.getNString(5);
            int rentalPointId = bicycles.getInt(6);
            int priceListId = bicycles.getInt(7);
            Bicycle bicycle = new Bicycle(bicycleId, brand, model, bicycleType, imagePath, rentalPointId, priceListId);
            bicycleList.add(bicycle);
        }
        return bicycleList;
    }

    @Override
    public Optional<Bicycle> find(Integer id) throws DaoException {
        Optional<Bicycle> optionalBicycle;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL_QUERY)) {
            statement.setInt(1, id);
            ResultSet bicycleData = statement.executeQuery();
            if (bicycleData.next()) {
                String brand = bicycleData.getNString(1);
                String model = bicycleData.getNString(2);
                int releaseYear = bicycleData.getInt(3);
                int purchaseYear = bicycleData.getInt(4);
                String description = bicycleData.getNString(5);
                BicycleType type = BicycleType.valueOf(bicycleData.getNString(6));
                String imagePath = bicycleData.getNString(7);
                int rentalPointId = bicycleData.getInt(8);
                int priceListId = bicycleData.getInt(9);
                Bicycle bicycle = new Bicycle(id, brand, model, releaseYear, purchaseYear, description, type, imagePath, rentalPointId, priceListId);
                optionalBicycle = Optional.of(bicycle);
            } else {
                optionalBicycle = Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalBicycle;
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
}
