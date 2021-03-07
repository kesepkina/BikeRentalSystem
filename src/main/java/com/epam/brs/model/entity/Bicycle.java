package com.epam.brs.model.entity;

import java.util.StringJoiner;

public class Bicycle extends Entity {

    private int bicycleId;
    private String brand;
    private String model;
    private int releaseYear;
    private int purchaseYear;
    private int speedAmount;
    private String description;
    private BicycleType type;
    private String imagePath;
    private int rentalPointId;
    private int priceListId;

    public Bicycle(int bicycleId, String brand, String model, int releaseYear, int purchaseYear, int speedAmount, String description, BicycleType type, String imagePath, int rentalPointId, int priceListId) {
        this.bicycleId = bicycleId;
        this.brand = brand;
        this.model = model;
        this.releaseYear = releaseYear;
        this.purchaseYear = purchaseYear;
        this.speedAmount = speedAmount;
        this.description = description;
        this.type = type;
        this.imagePath = imagePath;
        this.rentalPointId = rentalPointId;
        this.priceListId = priceListId;
    }

    public Bicycle(int bicycleId, String brand, String model, BicycleType type, String imagePath, int rentalPointId, int priceListId) {
        this.bicycleId = bicycleId;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.imagePath = imagePath;
        this.rentalPointId = rentalPointId;
        this.priceListId = priceListId;
    }

    public int getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(int bicycleId) {
        this.bicycleId = bicycleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getPurchaseYear() {
        return purchaseYear;
    }

    public void setPurchaseYear(int purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    public int getSpeedAmount() {
        return speedAmount;
    }

    public void setSpeedAmount(int speedAmount) {
        this.speedAmount = speedAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type.getValue();
    }

    public void setType(BicycleType type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getRentalPointId() {
        return rentalPointId;
    }

    public void setRentalPointId(int rentalPointId) {
        this.rentalPointId = rentalPointId;
    }

    public int getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(int priceListId) {
        this.priceListId = priceListId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bicycle bicycle = (Bicycle) o;

        if (bicycleId != bicycle.bicycleId) return false;
        if (releaseYear != bicycle.releaseYear) return false;
        if (purchaseYear != bicycle.purchaseYear) return false;
        if (speedAmount != bicycle.speedAmount) return false;
        if (rentalPointId != bicycle.rentalPointId) return false;
        if (priceListId != bicycle.priceListId) return false;
        if (brand != null ? !brand.equals(bicycle.brand) : bicycle.brand != null) return false;
        if (model != null ? !model.equals(bicycle.model) : bicycle.model != null) return false;
        if (description != null ? !description.equals(bicycle.description) : bicycle.description != null) return false;
        if (type != bicycle.type) return false;
        return imagePath != null ? imagePath.equals(bicycle.imagePath) : bicycle.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = bicycleId;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + releaseYear;
        result = 31 * result + purchaseYear;
        result = 31 * result + speedAmount;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + rentalPointId;
        result = 31 * result + priceListId;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Bicycle.class.getSimpleName() + "[", "]")
                .add("bicycleId=" + bicycleId)
                .add("brand='" + brand + "'")
                .add("model='" + model + "'")
                .add("releaseYear=" + releaseYear)
                .add("purchaseYear=" + purchaseYear)
                .add("speedAmount=" + speedAmount)
                .add("description='" + description + "'")
                .add("type=" + type)
                .add("imagePath='" + imagePath + "'")
                .add("rentalPointId=" + rentalPointId)
                .add("priceListId=" + priceListId)
                .toString();
    }
}

