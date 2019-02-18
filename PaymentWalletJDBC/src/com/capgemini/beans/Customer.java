package com.capgemini.beans;

public class Customer {
	private String name;
	private String mobileNumber;
	private long wallet;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public long getWallet() {
		return wallet;
	}
	public void setWallet(long wallet) {
		this.wallet = wallet;
	}
	public Customer() {
		super();
	}
	public Customer(String name, String mobileNumber, long wallet) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.wallet = wallet;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileNumber=" + mobileNumber + ", wallet=" + wallet + "]";
	}
}
