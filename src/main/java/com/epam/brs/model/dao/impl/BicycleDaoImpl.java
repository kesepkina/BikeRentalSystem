package com.epam.brs.model.dao.impl;

import com.epam.brs.model.dao.BicycleDao;
import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.entity.enumType.BicycleType;
import com.epam.brs.model.pool.ConnectionPool;
import org.intellij.lang.annotations.Language;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BicycleDaoImpl implements BicycleDao {

    private static final BicycleDao instance = new BicycleDaoImpl();

    private static final String FILE_PATH_BASE = "C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/bicycles";

    @Language("MySQL")
    private static final String FIND_ALL_SQL_QUERY = "SELECT id_bicycle, brand, model, type, image_path, id_price_list FROM bicycles";
    @Language("MySQL")
    private static final String FIND_BY_TYPE_SQL_QUERY = "SELECT id_bicycle, brand, model, type, image_path, id_price_list FROM bicycles WHERE type=?";
    @Language("MySQL")
    private static final String FIND_BY_ID_SQL_QUERY = "SELECT brand, model, release_year, purchase_year, description, type, image_path, id_price_list FROM bicycles WHERE id_bicycle=?";
    @Language("MySQL")
    private static final String ADD_BICYCLE_SQL_QUERY = "INSERT INTO bicycles(brand, model, release_year, purchase_year, description, id_price_list, image_path, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    @Language("MySQL")
    private static final String DOWNLOAD_IN_JSON_SQL_QUERY = "SELECT\n" +
            "    CONCAT('[',\n" +
            "           GROUP_CONCAT(\n" +
            "                   CONCAT(\"{id_bicycle:'\",id_bicycle,\"'\"),\n" +
            "                   CONCAT(\",brand:'\",brand,\"'\"),\n" +
            "                   CONCAT(\",model:'\",model,\"'\"),\n" +
            "                   CONCAT(\",type:'\",type,\"'\"),\n" +
            "                   CONCAT(\",image_path:'\",image_path,\"'\"),\n" +
            "                   CONCAT(\",id_price_list:'\",id_price_list,\"'\"),\n" +
            "                   CONCAT(\",description:'\",description,\"'\"),\n" +
            "                   CONCAT(\",release_year:'\",release_year,\"'\"),\n" +
            "                   CONCAT(\",purchase_year:'\",purchase_year,\"'}\")\n" +
            "               )\n" +
            "        ,\"]\")\n" +
            "        " +
            "AS json FROM bicycles\n" +
            "INTO OUTFILE ?";

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
    public boolean downloadTableAsJSON() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(DOWNLOAD_IN_JSON_SQL_QUERY)) {
            String filePath = FILE_PATH_BASE + LocalDateTime.now().hashCode() + ".json";
            statement.setString(1, filePath);
            statement.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public boolean addBicycle(Bicycle bicycle) throws DaoException {
        boolean addedSuccessfully;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_BICYCLE_SQL_QUERY)) {
            String brand = bicycle.getBrand();
            String model = bicycle.getModel();
            int releaseYear = bicycle.getReleaseYear();
            int purchaseYear = bicycle.getPurchaseYear();
            String description = bicycle.getDescription();
            int priceListId = bicycle.getPriceListId();
            String imagePath = bicycle.getImagePath();
            BicycleType bicycleType = bicycle.getType();
            statement.setString(1, brand);
            statement.setString(2, model);
            statement.setInt(3, releaseYear);
            statement.setInt(4, purchaseYear);
            statement.setString(5, description);
            statement.setInt(6, priceListId);
            statement.setString(7, imagePath);
            statement.setString(8, bicycleType.toString());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                addedSuccessfully = true;
                logger.info("Bicycle added successfully");
            } else {
                logger.error("Bicycle {} wasn't added", bicycle);
                addedSuccessfully = false;
            }
        } catch (SQLException e) {
            logger.error("Exception while adding new bicycle", e);
            throw new DaoException("Exception while adding new bicycle", e);
        }
        return addedSuccessfully;
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
            int priceListId = bicycles.getInt(6);
            Bicycle bicycle = new Bicycle(bicycleId, brand, model, bicycleType, imagePath, priceListId);
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
                int priceListId = bicycleData.getInt(8);
                Bicycle bicycle = new Bicycle(id, brand, model, releaseYear, purchaseYear, description, type, imagePath, priceListId);
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
