package com.dollarsbank.model;

public class Account {
	private double amount;
	
	public Account(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void deposit(double amount) {
		this.amount += amount;
	}
	
	public boolean withdraw(double amount) {
		if(this.amount >= amount) {
			this.amount -= amount;
			return true;
		}
		return false;
	}
}
