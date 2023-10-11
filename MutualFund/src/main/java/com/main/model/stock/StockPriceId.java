package com.main.model.stock;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class StockPriceId implements Serializable {
    int stockId;
    Date businessDate;

    public StockPriceId() {
        // Default constructor
    }

    public StockPriceId(int stockId, Date businessDate) {
        this.stockId = stockId;
        this.businessDate = businessDate;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockPriceId that = (StockPriceId) o;
        return Objects.equals(stockId, that.stockId) &&
               Objects.equals(businessDate, that.businessDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockId, businessDate);
    }
}
