package com.bridgelab;

import java.util.ArrayList;

public class StockPortfo {
	int noOfStock = 0;
	ArrayList<Stock> portf = new ArrayList<Stock>();
    double totalValue = 0;

    class Stock {
        int noOfShare;
        String company;
        double price;
    }

    public double totalValue() {
        for (int i = 0; i < portf.size(); i++) {
            totalValue += portf.get(i).price * portf.get(i).noOfShare;
        }
        return totalValue;
    }

}
