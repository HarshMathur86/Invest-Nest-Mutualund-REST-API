package com.main.model.investor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Investortransaction")
public class InvestorTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
	@SequenceGenerator(name = "transaction_id_seq", sequenceName = "INVESTOR_TRANSACTION_ID_SEQ", allocationSize = 1)
    private int transactionId;

    @Column
    private int investorId;
    
    @Column
    private int mfId;
    
    @Column//(name = "transaction_type", length = 4)
    private String transactionType;

    @Column//(name = "units")
    private double units;

    @Column//(name = "transaction_amount", precision = 14, scale = 2)
    private double transactionAmount;

    @Column
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    
    @PrePersist
    protected void onCreate() {
    	// Set the transaction date to the current date when the entity is persisted
        transactionDate = new Date(); 
    }

    public InvestorTransaction() {
        // Default constructor
    }

	public InvestorTransaction(int transactionId, int investorId, int mfId, String transactionType, double units,
			double transactionAmount) {
		super();
		this.transactionId = transactionId;
		this.investorId = investorId;
		this.mfId = mfId;
		this.transactionType = transactionType;
		this.units = units;
		this.transactionAmount = transactionAmount;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getInvestorId() {
		return investorId;
	}

	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}

	public int getMfId() {
		return mfId;
	}

	public void setMfId(int mfId) {
		this.mfId = mfId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getUnits() {
		return units;
	}

	public void setUnits(double units) {
		this.units = units;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
    
}