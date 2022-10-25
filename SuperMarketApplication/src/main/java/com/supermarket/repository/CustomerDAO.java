package com.supermarket.repository;

import java.util.List;

import com.supermarket.model.BillGenerater;
import com.supermarket.model.Customer;
import com.supermarket.model.Product;

public interface CustomerDAO {
	
	int updateCustomerDetails(Customer customer);
	int addIntoBill(BillGenerater billGenerater);
	List<Product> getCartList(int customerId);
	int removeFromCart(int productId);
	List<Product> billPrint(int customerId);
	Customer getCustomerDetailsById(int customerId);
}
