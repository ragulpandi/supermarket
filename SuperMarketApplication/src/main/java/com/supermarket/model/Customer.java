package com.supermarket.model;

public class Customer {
	private int customerId;
	private String customerName;
	private String customerEmail;
	private String customerPhn;
	private String userName;
	private String userPassword;
	
	
	
	public Customer() {
	}

	public Customer(int customerId, String customerName, String customerEmail, String customerPhn, String userName,
			String userPassword) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhn = customerPhn;
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhn() {
		return customerPhn;
	}
	public void setCustomerPhn(String customerPhn) {
		this.customerPhn = customerPhn;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerPhn=" + customerPhn + ", userName=" + userName + ", userPassword="
				+ userPassword + "]";
	}
	
}
