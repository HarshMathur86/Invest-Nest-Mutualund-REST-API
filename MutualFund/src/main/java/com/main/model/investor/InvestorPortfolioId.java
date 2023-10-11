package com.main.model.investor;

import java.io.Serializable;
import java.util.Objects;

public class InvestorPortfolioId implements Serializable {

    private int investorId;
    private int mfId;

    // Constructors, getters, setters, and equals/hashCode methods...

    public InvestorPortfolioId() {
        super();
    }

    public InvestorPortfolioId(int investorId, int mfId) {
        this.investorId = investorId;
        this.mfId = mfId;
    }

    // Implement equals and hashCode methods...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvestorPortfolioId that = (InvestorPortfolioId) o;
        return investorId == that.investorId &&
                mfId == that.mfId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(investorId, mfId);
    }

    // Getters and setters...

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
}
