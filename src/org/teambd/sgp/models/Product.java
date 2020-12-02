package org.teambd.sgp.models;

import java.sql.Date;

public class Product {

    private int id;
    private String name;
    private String description;
    private int brandIdFk;
    private int categoryIdFk;
    private Date elaborationDate;
    private Date expirationDate;
    private int grossPrice;
    private int netPrice;
    private int stock;
    private boolean isGreat; // ¿es kchero?
    private boolean isActive;

    public Product() {
    }

    public Product(int id, String name, String description, int brandIdFk, int categoryIdFk,
                   Date elaborationDate, Date expirationDate, int grossPrice, int netPrice, int stock,
                   boolean isGreat, boolean isActive) {
        this.id = id;
        this.setName(name);
        this.description = description;
        this.setBrandIdFk(brandIdFk);
        this.setCategoryIdFk(categoryIdFk);
        this.elaborationDate = elaborationDate;
        this.expirationDate = expirationDate;
        this.setGrossPrice(grossPrice);
        this.setNetPrice(netPrice);
        this.setStock(stock);
        this.isGreat = isGreat;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if ( !name.isBlank() ) {
            this.name = name;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    public int getBrandIdFk() {
        return brandIdFk;
    }

    public void setBrandIdFk(int brandIdFk) {
        if ( brandIdFk > 0 ) {
            this.brandIdFk = brandIdFk;
        }
    }

    public int getCategoryIdFk() {
        return categoryIdFk;
    }

    public void setCategoryIdFk(int categoryIdFk) {
        if ( categoryIdFk > 0 ) {
            this.categoryIdFk = categoryIdFk;
        }
    }

    public Date getElaborationDate() {
        return elaborationDate;
    }

    public void setElaborationDate(Date elaborationDate) {
        this.elaborationDate = elaborationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(int grossPrice) {
        if ( grossPrice > 0 ) {
            this.grossPrice = grossPrice;
        }
    }

    public int getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(int netPrice) {
        if ( netPrice > 0 ) {
            this.netPrice = netPrice;
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if ( stock > 0 ) {
            this.stock = stock;
        }
    }

    public boolean isGreat() {
        return isGreat;
    }

    public void setGreat(boolean great) {
        isGreat = great;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brandIdFk=" + brandIdFk +
                ", categoryIdFk=" + categoryIdFk +
                ", elaborationDate=" + elaborationDate +
                ", expirationDate=" + expirationDate +
                ", grossPrice=" + grossPrice +
                ", netPrice=" + netPrice +
                ", stock=" + stock +
                ", isGreat=" + isGreat +
                ", isActive=" + isActive +
                '}';
    }


}
