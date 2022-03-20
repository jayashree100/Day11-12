package com.bridgelab.stockmanagement;

public interface StockAccountManagement {
	void addStocks(Stock share);

	void getStockDetails();

	void calculateShareValue();

	void removeStock(String name);

	void sellStocks(String shareName, int count);

	void buyStocks(String shareName, int count);

}
