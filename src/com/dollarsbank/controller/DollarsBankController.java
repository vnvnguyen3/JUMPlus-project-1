package com.dollarsbank.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.dollarsbank.model.Customer;

public class DollarsBankController {

	private ArrayList<Customer> customers;
	
	public DollarsBankController() {
		customers = new ArrayList<Customer>();
	}
	
	public void welcome() {
		System.out.println("\n");
		System.out.println("+---------------------------+");
		System.out.println("| DOLLARSBANK Welcomes You! |");
		System.out.println("+---------------------------+");
		System.out.println("1. Create New Account");
		System.out.println("2. Login");
		System.out.println("3. Exit. \n");
		System.out.println("Enter Choice (1,2 or 3) :");
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		switch(choice) {
			case 1:
				createAccount();
				break;
			case 2:
				login();
				break;
			case 3: 
				System.out.println("Have a nice day!");
				break;
		}
	}
	
	public void createAccount() {
		Scanner in = new Scanner(System.in);
		System.out.println("\n");
		System.out.println("+-------------------------------+");
		System.out.println("| Enter Details For New Account |");
		System.out.println("+-------------------------------+");
		System.out.println("Customer Name:");
		String name = in.nextLine();
		System.out.println("Customer Address:");
		String address = in.nextLine();
		System.out.println("Customer Contact Number:");
		String contact = in.next();
		System.out.println("User Id :");
		String id = in.next();
		boolean pass = false;
		String password = "";
		while(!pass) {			
			System.out.println("Password : 8 Characters With Lower,Upper & Special");
			password = in.next();
			pass = checkPass(password);
		}
		System.out.println("Initial Deposit Amount");
		double amount = in.nextDouble();
		Customer c = new Customer(name, address, contact, id, password, amount);
		customers.add(c);
		System.out.println("Account Successfully Created");
		welcome();
	}
	
	public boolean checkPass(String pass) {
		if(pass.length() < 8) {		
			System.out.println("Needs at least 8 characters");
			return false;
		}
		boolean lower = false;
		boolean upper = false;
		boolean special = false;
		for(int i = 0; i < pass.length(); i++) {
			char c = pass.charAt(i);
			if(Character.isUpperCase(c)) {
				upper = true;
			}
			else if(Character.isLowerCase(c)) {
				lower = true;
			}else if(Character.isDigit(c) || c==' ') {
				
			}else {
				special = true;
			}
		}
		if(!lower) {
			System.out.println("Needs a lowercase letter");
			return lower;
		}
		else if(!upper) {
			System.out.println("Needs an uppercase letter");
			return upper;
		}
		else if(!special) {
			System.out.println("Needs a special character");
			return special;
		}
		return true;
	}
	
	public void login() {
		Scanner in = new Scanner(System.in);
		System.out.println("\n");
		System.out.println("+---------------------+");
		System.out.println("| Enter Login Details |");
		System.out.println("+---------------------+");
		boolean loggedIn = false;
		while(!loggedIn) {			
			System.out.println("User Id :");
			String id = in.next();
			System.out.println("Password :");
			String password = in.next();
			for(Customer c: customers) {
				if(c.getId().equals(id) && c.getPassword().equals(password)) {
					loggedIn = true;
					menu(c);
				}
			}
			if(!loggedIn) {
				System.out.println("Invalid Credentials. Try Again!");
			}
		}
	}
	
	public void menu(Customer c) {
		Scanner in = new Scanner(System.in);
		System.out.println("\n");
		System.out.println("+------------------+");
		System.out.println("| WELCOME Customer |");
		System.out.println("+------------------+");
		System.out.println("1. Deposit Amount");
		System.out.println("2. Withdraw Amount");
		System.out.println("3. Funds Transfer");
		System.out.println("4. View 5 Recent Transactions");
		System.out.println("5. Display Customer Information");
		System.out.println("6. Sign Out\n");
		System.out.println("Enter Choice (1,2,3,4,5 or 6) :");
		int choice = in.nextInt();
		switch(choice) {
			case 1:
				deposit(c);
				break;
			case 2:
				withdraw(c);
				break;
			case 3: 
				transfer(c);
				break;
			case 4:
				transactions(c);
				break;
			case 5:
				info(c);
				break;
			case 6:
				System.out.println("User Successfully Logged Out");
				welcome();
				break;
		}
	}
	
	public void deposit(Customer c) {
		System.out.println("\n");
		System.out.println("+----------------+");
		System.out.println("| Deposit Amount |");
		System.out.println("+----------------+");
		System.out.println("Your account currently has "+c.getAmount()+". How much would you like to deposit?");
		Scanner in = new Scanner(System.in);
		double amount = in.nextDouble();
		c.deposit(amount);
		System.out.println("Successfully deposited. Your account now has "+c.getAmount());
		menu(c);
	}
	
	public void withdraw(Customer c) {
		System.out.println("\n");
		System.out.println("+-----------------+");
		System.out.println("| Withdraw Amount |");
		System.out.println("+-----------------+");
		System.out.println("Your account currently has "+c.getAmount()+". How much would you like to withdraw?");
		Scanner in = new Scanner(System.in);
		boolean done = false;
		while(!done) {			
			double amount = in.nextDouble();
			done = c.withdraw(amount);
			if(!done) {
				System.out.println("Insufficient Funds");
			}
		}
		System.out.println("Successfully withdrew. Your account now has "+c.getAmount());
		menu(c);
	}
	
	public void transfer(Customer c) {
		System.out.println("\n");
		System.out.println("+----------------+");
		System.out.println("| Funds Transfer |");
		System.out.println("+----------------+");
		System.out.println("Your account currently has "+c.getAmount()+" and your savings currently has "+c.getSavings()+". How much would you like to transfer?");
		Scanner in = new Scanner(System.in);
		boolean done = false;
		while(!done) {			
			double amount = in.nextDouble();
			done = c.transfer(amount);
			if(!done) {
				System.out.println("Insufficient Funds");
			}
		}
		System.out.println("Successfully withdrew. Your account now has "+c.getAmount()+" and your savings now has "+c.getSavings());
		menu(c);
	}
	
	public void transactions(Customer c) {
		System.out.println("\n");
		System.out.println("+-----------------------+");
		System.out.println("| 5 Recent Transactions |");
		System.out.println("+-----------------------+");
		c.transactions();
		menu(c);
	}
	
	public void info(Customer c) {
		System.out.println("\n");
		System.out.println("+----------------------+");
		System.out.println("| Customer Information |");
		System.out.println("+----------------------+");
		System.out.println(c.toString());
		menu(c);
	}
}
