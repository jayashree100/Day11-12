package com.bridgelab;

import java.util.Scanner;

public class StockManagement {
    public static void main(String[] args) {
        int flag = 0;
        Scanner sc = new Scanner(System.in);
        StockPortfo p = new StockPortfo();
        while (flag == 0) {
            System.out.println("Enter the details of Stock ");
            StockPortfo.Stock s = p.new Stock();
            System.out.println("Enter Company Name:");
            s.company = sc.nextLine();
            System.out.println("Enter number of share:");
            s.noOfShare = sc.nextInt();
            System.out.println("Enter Price of share:");
            s.price = sc.nextDouble();

            p.portf.add(s);
            System.out.println("Do you want to add more Stocks? enter 0 for YES or 1 for NO!");
            flag = sc.nextInt();
        }
        for (int i = 0; i < p.portf.size(); i++) {
            System.out.println(p.totalValue());

        }
    }


}
