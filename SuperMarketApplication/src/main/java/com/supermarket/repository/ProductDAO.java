package com.supermarket.repository;

import java.util.List;

import com.supermarket.model.Product;

public interface ProductDAO {
	
	List<Product> getAllProduct();
	Product getProductById(int productId);
	int addProduct(Product product);
	int updateProduct(Product product);
	int deleteProduct(int productId);
	

}
