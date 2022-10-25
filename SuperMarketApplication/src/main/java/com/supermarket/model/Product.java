package com.supermarket.model;

public class Product {
	
	private int productId;
	private String productName;
	private int productQuantity;
	private float productAmount;
	
	public Product() {
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public float getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(float productAmount) {
		this.productAmount = productAmount;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productQuantity="
				+ productQuantity + ", productAmount=" + productAmount + "]";
	}
	
	

}
