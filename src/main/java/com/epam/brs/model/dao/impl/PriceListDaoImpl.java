package com.epam.brs.model.dao.impl;

import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.PriceListDao;
import com.epam.brs.model.entity.PriceList;
import com.epam.brs.model.entity.enumType.UserRole;
import com.epam.brs.model.pool.ConnectionPool;
import org.intellij.lang.annotations.Language;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PriceListDaoImpl implements PriceListDao {

    private static final PriceListDao instance = new PriceListDaoImpl();

    @Language("MySQL")
    private static final String FIND_BY_ID_SQL_QUERY = "SELECT deposit, price_per_hour, price_per_day, price_per_week FROM price_list WHERE id_price_list=?";
    @Language("MySQL")
    private static final String ADD_LIST_SQL_QUERY = "INSERT INTO price_list(deposit, price_per_hour, price_per_day, price_per_week) VALUES (?, ?, ?, ?)";
    @Language("MySQL")
    private static final String FIND_ID_SQL_QUERY = "SELECT id_price_list from price_list WHERE deposit=? AND price_per_hour=? AND price_per_day=? AND price_per_week=?";

    private PriceListDaoImpl() {
    }

    public static PriceListDao getInstance() {
        return instance;
    }

    @Override
    public List<PriceList> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<PriceList> find(Integer id) throws DaoException {
        Optional<PriceList> optionalPriceList;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL_QUERY)) {
            statement.setInt(1, id);
            ResultSet priceListData = statement.executeQuery();
            if (priceListData.next()) {
                BigDecimal deposit = priceListData.getBigDecimal(1);
                BigDecimal pricePerHour = priceListData.getBigDecimal(2);
                BigDecimal pricePerDay = priceListData.getBigDecimal(3);
                BigDecimal pricePerWeek = priceListData.getBigDecimal(4);

                PriceList priceList = new PriceList(id, deposit, pricePerHour, pricePerDay, pricePerWeek);
                optionalPriceList = Optional.of(priceList);
            } else {
                optionalPriceList = Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalPriceList;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(PriceList entity) {
        return false;
    }

    @Override
    public PriceList update(PriceList entity) {
        return null;
    }

    @Override
    public boolean add(PriceList priceList) throws DaoException {
        boolean addedSuccessfully;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_LIST_SQL_QUERY)) {
            BigDecimal deposit = priceList.getDeposit();
            BigDecimal pricePerHour = priceList.getPricePerHour();
            BigDecimal pricePerDay = priceList.getPricePerDay();
            BigDecimal pricePerWeek = priceList.getPricePerWeek();
            statement.setBigDecimal(1, deposit);
            statement.setBigDecimal(2, pricePerHour);
            statement.setBigDecimal(3, pricePerDay);
            statement.setBigDecimal(4, pricePerWeek);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                addedSuccessfully = true;
                logger.info("Price list added successfully");
            } else {
                logger.error("Price list {} wasn't added", priceList);
                addedSuccessfully = false;
            }
        } catch (SQLException e) {
            logger.error("Exception while adding new price list", e);
            throw new DaoException("Exception while adding new price list", e);
        }
        return addedSuccessfully;
    }

    @Override
    public boolean findId(PriceList priceList) throws DaoException {
        boolean found = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ID_SQL_QUERY)) {
            statement.setBigDecimal(1, priceList.getDeposit());
            statement.setBigDecimal(2, priceList.getPricePerHour());
            statement.setBigDecimal(3, priceList.getPricePerDay());
            statement.setBigDecimal(4, priceList.getPricePerWeek());
            ResultSet priceListData = statement.executeQuery();
            if (priceListData.next()) {
                int priceListId = priceListData.getInt(1);
                priceList.setPriceListId(priceListId);
                found = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return found;
    }
}
