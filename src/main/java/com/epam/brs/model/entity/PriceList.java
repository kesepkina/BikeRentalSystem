package com.epam.brs.model.entity;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class PriceList extends Entity{

    private int priceListId;
    private BigDecimal deposit;
    private BigDecimal pricePerHour;
    private BigDecimal pricePerDay;
    private BigDecimal pricePerWeek;

    public PriceList(BigDecimal deposit, BigDecimal pricePerHour, BigDecimal pricePerDay, BigDecimal pricePerWeek) {
        this.deposit = deposit;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.pricePerWeek = pricePerWeek;
    }

    public PriceList(int priceListId, BigDecimal deposit, BigDecimal pricePerHour, BigDecimal pricePerDay, BigDecimal pricePerWeek) {
        this.priceListId = priceListId;
        this.deposit = deposit;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.pricePerWeek = pricePerWeek;
    }

    public int getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(int priceListId) {
        this.priceListId = priceListId;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public BigDecimal getPricePerWeek() {
        return pricePerWeek;
    }

    public void setPricePerWeek(BigDecimal pricePerWeek) {
        this.pricePerWeek = pricePerWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceList priceList = (PriceList) o;

        if (priceListId != priceList.priceListId) return false;
        if (deposit != null ? !deposit.equals(priceList.deposit) : priceList.deposit != null) return false;
        if (pricePerHour != null ? !pricePerHour.equals(priceList.pricePerHour) : priceList.pricePerHour != null)
            return false;
        if (pricePerDay != null ? !pricePerDay.equals(priceList.pricePerDay) : priceList.pricePerDay != null)
            return false;
        return pricePerWeek != null ? pricePerWeek.equals(priceList.pricePerWeek) : priceList.pricePerWeek == null;
    }

    @Override
    public int hashCode() {
        int result = priceListId;
        result = 31 * result + (deposit != null ? deposit.hashCode() : 0);
        result = 31 * result + (pricePerHour != null ? pricePerHour.hashCode() : 0);
        result = 31 * result + (pricePerDay != null ? pricePerDay.hashCode() : 0);
        result = 31 * result + (pricePerWeek != null ? pricePerWeek.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PriceList.class.getSimpleName() + "[", "]")
                .add("priceListId=" + priceListId)
                .add("deposit=" + deposit)
                .add("pricePerHour=" + pricePerHour)
                .add("pricePerDay=" + pricePerDay)
                .add("pricePerWeek=" + pricePerWeek)
                .toString();
    }
}
