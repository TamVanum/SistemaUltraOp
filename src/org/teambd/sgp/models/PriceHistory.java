package org.teambd.sgp.models;

import java.sql.Date;

public class PriceHistory {

    private int id;
    private int productIdFk;
    private int actualPrice;
    private int newPrice;
    private Date updateDate;

    public PriceHistory() {
    }

    public PriceHistory(int id, int productIdFk, int actualPrice, int newPrice, Date updateDate) {
        this.id = id;
        this.setProductIdFk(productIdFk);
        this.setActualPrice(actualPrice);
        this.setNewPrice(newPrice);
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public int getProductIdFk() {
        return productIdFk;
    }

    public void setProductIdFk(int productIdFk) {
        if ( productIdFk > 0 ) {
            this.productIdFk = productIdFk;
        } else {
            throw new IllegalArgumentException("User id negative!");
        }
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        if ( actualPrice > 0 ) {
            this.actualPrice = actualPrice;
        } else {
            throw new IllegalArgumentException("price negative!");
        }
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        if ( newPrice > 0 ) {
            this.newPrice = newPrice;
        } else {
            throw new IllegalArgumentException("price negative!");
        }
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "PriceHistory{" +
                "id=" + id +
                ", productIdFk=" + productIdFk +
                ", actualPrice=" + actualPrice +
                ", newPrice=" + newPrice +
                ", updateDate=" + updateDate +
                '}';
    }
}
