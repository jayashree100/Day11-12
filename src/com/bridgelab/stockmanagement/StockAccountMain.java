package com.bridgelab.stockmanagement;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.*;

class Transaction {
	int id;
	String Customer_name;
	LocalDateTime Transaction_time;
	String Sharename;
	int sharecount;
	String ordertype;

	public Transaction(int id, String Customer_name, String Sharename, int sharecount, String ordertype) {
		this.id = id;
		this.Customer_name = Customer_name;
		this.Transaction_time = LocalDateTime.now();
		this.Sharename = Sharename;
		this.sharecount = sharecount;
		this.ordertype = ordertype;
	}

	public void display() {
		System.out.println(this.id + "\t" + this.Customer_name + "\t\t" + this.Transaction_time + "\t\t"
				+ this.Sharename + "\t\t" + this.sharecount + "\t\t" + this.ordertype);
	}

}

public class StockAccountMain {

	public static void main(String[] args) {
		StockAccountManagement stockservice = new StockAccountManagementImpl();
		// initialized queue with linkedlist
		Queue<Transaction> Transaction_queue = new LinkedList<>();
		// initialization of customer details by HashMap
		HashMap<String, HashMap<String, Integer>> customers = new HashMap<>();
		int transaction_id = 0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("welcome to Stock management");
		System.out.println("Enter the num of shares to add to inventory : ");
		int numOfShares = scanner.nextInt();

		for (int index = 0; index < numOfShares; index++) {

			Stock newShare = new Stock();

			System.out.println("Enter Share name: ");
			newShare.setShareName(scanner.next());
			System.out.println("Enter share Price: ");
			newShare.setSharePrice(scanner.nextDouble());
			System.out.println("Total number of shares : ");
			newShare.setShareCount(scanner.nextInt());
			stockservice.addStocks(newShare);
		}
		stockservice.getStockDetails();
		while (true) {
			System.out.println(
					"\n1.Print Stocks  \n2.Calculate the value \n3.Remove stock \n4.Add \n5.buy shares \n6.sell shares\n7.Display Transactions \n8.Add Customer\n9.quit");
			int key = scanner.nextInt();
			switch (key) {
			case 1:
				stockservice.getStockDetails();
				break;
			case 2:
				stockservice.calculateShareValue();
				break;
			case 3:
				System.out.println("Enter stock name to remove");
				String shareName = scanner.next();
				stockservice.removeStock(shareName);
				stockservice.getStockDetails();
				break;
			case 4:
				System.out.println("Enter the num of shares to add: ");
				int numOfShare = scanner.nextInt();

				for (int index = 0; index < numOfShare; index++) {

					Stock newShares = new Stock();

					System.out.println("Enter Share name: ");
					newShares.setShareName(scanner.next());
					System.out.println("Enter share Price: ");
					newShares.setSharePrice(scanner.nextDouble());
					System.out.println("Enter no. of share taken: ");
					newShares.setShareCount(scanner.nextInt());
					stockservice.addStocks(newShares);
				}
				stockservice.getStockDetails();
				break;
			case 5:
				System.out.println("Enter your Name");
				String Customer_Name = scanner.next();
				// checking if name exists in custmer list
				if (!customers.containsKey(Customer_Name)) {
					System.out.println("You are not a Valid User");
					break;
				}
				System.out.println("Enter the stock you want to buy :");
				String stockName = scanner.next();
				System.out.println("Enter the count you want to buy : ");
				int count = scanner.nextInt();
				stockservice.buyStocks(stockName, count);
				// crearing separate hashmap for finding how many shares
				HashMap<String, Integer> temp_hash = new HashMap<String, Integer>();
				temp_hash = customers.get(Customer_Name);
				// adding stock name and count to hasmap
				if (temp_hash == null) {
					temp_hash = new HashMap<String, Integer>();
					temp_hash.put(stockName, count);
				}
				// adding existing stock to new stock
				else if (temp_hash.containsKey(stockName)) {
					int Stock_count = temp_hash.get(stockName);
					temp_hash.put(stockName, count + Stock_count);
				}
				// adding share
				else {
					temp_hash.put(stockName, count);
				}
				customers.put(Customer_Name, temp_hash);
				stockservice.getStockDetails();
				transaction_id++;
				Transaction temp = new Transaction(transaction_id, Customer_Name, stockName, count, "Buy");
				Transaction_queue.add(temp);
				break;
			case 6:
				System.out.println("Enter your Name");
				String Customer_Name1 = scanner.next();
				if (!customers.containsKey(Customer_Name1)) {
					System.out.println("You are not a Valid User");
					break;
				}
				HashMap<String, Integer> temp_hash2 = new HashMap<String, Integer>();
				temp_hash2 = customers.get(Customer_Name1);
				System.out.println("Enter the stock you want to sell :");
				String stockName1 = scanner.next();
				if (!temp_hash2.containsKey(stockName1)) {
					System.out.println("You Did not have this Stock so you cannot Sell");
					break;
				}
				System.out.println("Enter the count you want to sell : ");
				// if count is more than existing stock
				int count1 = scanner.nextInt();
				if (temp_hash2.get(stockName1) < count1) {
					System.out.println("You Did not have Enough Stock count so you cannot Sell");
					break;
				}
				stockservice.sellStocks(stockName1, count1);
				stockservice.getStockDetails();
				transaction_id++;
				Transaction temp1 = new Transaction(transaction_id, Customer_Name1, stockName1, count1, "Sell");
				Transaction_queue.add(temp1);
				break;
			case 7:
				// displaying transaction details
				System.out.println("Id\tCustomerName\t\tDateandTime\t\tStockName\t\tCount\t\ttransaction");
				Iterator iterator = Transaction_queue.iterator();
				while (iterator.hasNext()) {
					Transaction element = (Transaction) iterator.next();
					element.display();
				}
				break;
			case 8:
				System.out.println("Enter the Name:");
				customers.put(scanner.next(), null);
				break;
			case 9:
				break;
			}
		}
	}

}
