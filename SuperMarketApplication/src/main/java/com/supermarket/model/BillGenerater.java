package com.supermarket.model;

public class BillGenerater {
	private int productId;
	private int customerId;
	private int orderQuantity;
	private float amount;
	
	
	public BillGenerater() {
		super();
	}

	public BillGenerater(int productId, int customerId, int orderQuantity, float amount) {
		super();
		this.productId = productId;
		this.customerId = customerId;
		this.orderQuantity = orderQuantity;
		this.amount = amount;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BillGenerater [productId=" + productId + ", customerId=" + customerId + ", orderQuantity="
				+ orderQuantity + ", amount=" + amount + "]";
	}
	
	
	
}
