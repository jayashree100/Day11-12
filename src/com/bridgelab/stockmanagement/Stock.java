package com.bridgelab.stockmanagement;

public class Stock {
	private String shareName;
	private double sharePrice;
	private int shareCount;
	private String symbol;

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "Stock [shareName=" + shareName + ", sharePrice=" + sharePrice + ", shareCount=" + shareCount
				+ ", symbol=" + symbol + "]";
	}

}
