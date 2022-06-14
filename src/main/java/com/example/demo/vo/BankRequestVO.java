package com.example.demo.vo;

import lombok.Data;

//@Data
public class BankRequestVO {
	
	private String accountNumber;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
