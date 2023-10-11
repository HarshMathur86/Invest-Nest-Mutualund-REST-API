package com.main.model.stock;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name ="Stockprice")
@IdClass(StockPriceId.class)
public class StockPrice {
    @Id
    private int stockId;
    
    @Id
    @Temporal(TemporalType.DATE)
    @Column
    private Date businessDate;
    
    @Column
    private double openingPrice, closingPrice;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stockId", referencedColumnName = "stockId", insertable = false, updatable = false)
    private Stock stock;

    // Constructors, getters, setters
    
    public StockPrice() {
        super();
    }

    public StockPrice(int stockId, Date businessDate, double openingPrice, double closingPrice, Stock stock) {
        super();
        this.stockId = stockId;
        this.businessDate = businessDate;
        this.openingPrice = openingPrice;
        this.closingPrice = closingPrice;
        this.stock = stock;
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

    public double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }
    
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

}



// Without ManytoOne allocation

//@Entity
//@Table(name ="Stockprice")
//@IdClass(StockPriceId.class)
//public class StockPrice {
//    @Id
//    private int stockId;
//    
//    @Id
//    @Temporal(TemporalType.DATE)
//    @Column
//    private Date businessDate;
//    
//    @Column
//    private double openingPrice, closingPrice;
//
//	public StockPrice() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public StockPrice(int stockId, Date businessDate, double openingPrice, double closingPrice) {
//		super();
//		this.stockId = stockId;
//		this.businessDate = businessDate;
//		this.openingPrice = openingPrice;
//		this.closingPrice = closingPrice;
//	}
//
//	public int getStockId() {
//		return stockId;
//	}
//
//	public void setStockId(int stockId) {
//		this.stockId = stockId;
//	}
//
//	public Date getBusinessDate() {
//		return businessDate;
//	}
//
//	public void setBusinessDate(Date businessDate) {
//		this.businessDate = businessDate;
//	}
//
//	public double getOpeningPrice() {
//		return openingPrice;
//	}
//
//	public void setOpeningPrice(double openingPrice) {
//		this.openingPrice = openingPrice;
//	}
//
//	public double getClosingPrice() {
//		return closingPrice;
//	}
//
//	public void setClosingPrice(double closingPrice) {
//		this.closingPrice = closingPrice;
//	}
//    
//    
//    
//}

