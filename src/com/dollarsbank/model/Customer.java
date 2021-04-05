package com.dollarsbank.model;

import java.util.ArrayList;

public class Customer {
	private String name;
	private String address;
	private String contact;
	private String id;
	private String password;
	private Account account;
	private SavingsAccount savings;
	private ArrayList<String> transactions;
	
	public Customer(String name, String address, String contact, String id, String password, double amount) {
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.id = id;
		this.password = password;
		account = new Account(amount);
		savings = new SavingsAccount(0);
		transactions = new ArrayList<String>();
		transactions.add("Initial Deposit Amount in account: "+amount);
	}
	
	public void deposit(double amount) {
		account.deposit(amount);
		transactions.add("Deposited Amount in account: "+amount);
	}
	
	public boolean withdraw(double amount) {
		if(account.getAmount() >= amount) {
			account.withdraw(amount);
			transactions.add("Withdraw Amount from account: "+amount);
			return true;
		}
		return false;
	}
	
	public boolean transfer(double amount) {
		if(account.getAmount() >= amount) {
			account.withdraw(amount);
			savings.deposit(amount);
			transactions.add("Transferred Amount to savings: "+amount);
			return true;
		}
		return false;
	}
	
	public void transactions() {
		int count = 0;
		for(int i = transactions.size()-1; i >= 0 && count < 5; i--) {
			System.out.println(transactions.get(i));
			count++;
		}
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getContact() {
		return contact;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
	public double getAmount() {
		return account.getAmount();
	}
	
	public double getSavings() {
		return savings.getAmount();
	}

	@Override
	public String toString() {
		return "name=" + name + ", address=" + address + ", contact=" + contact + ", id=" + id + ", account="
				+ account.getAmount()+", savings="+savings.getAmount();
	}
	
	
}
