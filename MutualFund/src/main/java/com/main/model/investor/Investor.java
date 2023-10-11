package com.main.model.investor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Investor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investor_seq")
	@SequenceGenerator(name = "investor_seq", sequenceName = "INVESTOR_ID_SEQ", allocationSize = 1)
    int investorId;
	
	@Column
	String username, password, firstName, lastName, email;
	
	@Column
	long phoneNumber;

	public Investor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Investor(int investorId, String username, String password, String firstName, String lastName, String email,
			long phoneNumber) {
		super();
		this.investorId = investorId; 
		this.username = username;
		this.password = password;
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.email = email; 
		this.phoneNumber = phoneNumber; 
	}

	public int getInvestorId() {
		return investorId;
	}

	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
