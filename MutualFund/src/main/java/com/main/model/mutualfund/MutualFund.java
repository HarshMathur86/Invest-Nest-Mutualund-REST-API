package com.main.model.mutualfund;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Mutualfund")
public class MutualFund {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mutualfund_id")
    @SequenceGenerator(name = "mutualfund_id", sequenceName = "mf_id_seq", allocationSize = 1)
    private int mfId;

    @Column
    private String mfName;

    @Column
    private double cashBalance;

    @Column
    private double entryLoad;

    @Column
    private double exitLoad;

    @Column
    private double expenseRatio;

    @Column
    private double totalCorpus;
    
    @Column
    private double units;
    
    @Column
    private double nav;
    
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "mfId", referencedColumnName = "mfId", insertable = false, updatable = false)
    private List<Portfolio> Portfolios;

	public MutualFund() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MutualFund(int mfId, String mfName, double cashBalance, double entryLoad, double exitLoad,
			double expenseRatio, double totalCorpus, double units, double nav, List<Portfolio> portfolios) {
		super();
		this.mfId = mfId;
		this.mfName = mfName;
		this.cashBalance = cashBalance;
		this.entryLoad = entryLoad;
		this.exitLoad = exitLoad;
		this.expenseRatio = expenseRatio;
		this.totalCorpus = totalCorpus;
		this.units = units;
		this.nav = nav;
		Portfolios = portfolios;
	}

	public int getMfId() {
		return mfId;
	}

	public void setMfId(int mfId) {
		this.mfId = mfId;
	}

	public String getMfName() {
		return mfName;
	}

	public void setMfName(String mfName) {
		this.mfName = mfName;
	}

	public double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}

	public double getEntryLoad() {
		return entryLoad;
	}

	public void setEntryLoad(double entryLoad) {
		this.entryLoad = entryLoad;
	}

	public double getExitLoad() {
		return exitLoad;
	}

	public void setExitLoad(double exitLoad) {
		this.exitLoad = exitLoad;
	}

	public double getExpenseRatio() {
		return expenseRatio;
	}

	public void setExpenseRatio(double expenseRatio) {
		this.expenseRatio = expenseRatio;
	}

	public double getTotalCorpus() {
		return totalCorpus;
	}

	public void setTotalCorpus(double totalCorpus) {
		this.totalCorpus = totalCorpus;
	}

	public double getUnits() {
		return units;
	}

	public void setUnits(double units) {
		this.units = units;
	}

	public double getNav() {
		return nav;
	}

	public void setNav(double nav) {
		this.nav = nav;
	}

	public List<Portfolio> getPortfolios() {
		return Portfolios;
	}

	public void setPortfolios(List<Portfolio> portfolios) {
		Portfolios = portfolios;
	}
    
}
