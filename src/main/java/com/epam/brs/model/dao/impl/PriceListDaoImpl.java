package com.epam.brs.model.dao.impl;

import com.epam.brs.model.dao.BicycleDao;
import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.PriceListDao;
import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.entity.BicycleType;
import com.epam.brs.model.entity.PriceList;
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
}
