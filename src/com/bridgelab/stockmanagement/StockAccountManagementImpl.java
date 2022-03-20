package com.bridgelab.stockmanagement;

import java.util.LinkedList;

public class StockAccountManagementImpl implements StockAccountManagement {

	LinkedList<Stock> sharesList = new LinkedList<Stock>();

	public void addStocks(Stock share) {
		if (sharesList.contains(share)) {
			System.err.println("Inventory Item Exists");
		} else {
			sharesList.add(share);
		}

	}

	@Override
	public void getStockDetails() {
		for (Stock stock : sharesList) {
			System.out.println(stock);
		}
	}

	@Override
	public void calculateShareValue() {
		double totalvalue = 0;
		for (Stock stock : sharesList) {
			double value = stock.getShareCount() * stock.getSharePrice();
			System.out.println("Value of " + stock.getShareName() + " is " + value);
			totalvalue = totalvalue + value;
		}

	}

	@Override
	public void removeStock(String shareName) {
		for (Stock stock : sharesList) {
			if (stock.getShareName().equals(shareName)) {
				stock.setShareCount(0);
				stock.setShareName(null);
				stock.setSharePrice(0);

			}
		}

	}

	@Override
	public void sellStocks(String shareName, int count) {
		for (Stock stock : sharesList) {
			if (stock.getShareName().equals(shareName)) {
				int number = stock.getShareCount() + count;
				stock.setShareCount(number);
			}
		}
	}

	@Override
	public void buyStocks(String shareName, int count) {
		for (Stock stocks : sharesList) {
			if (stocks.getShareName().equals(shareName)) {
				double value = (double) stocks.getSharePrice() * count;
				System.out.println("The" + shareName + "total price is : " + value);
				int stockCount = stocks.getShareCount() - count;
				stocks.setShareCount(stockCount);
			}
		}
	}
}
