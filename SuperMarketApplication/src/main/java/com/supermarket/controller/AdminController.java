package com.supermarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.model.Product;
import com.supermarket.repository.ProductDAOImpl;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
//@RequestMapping("/user")
public class AdminController {
	private ProductDAOImpl productDAO = new ProductDAOImpl();
	
	
//	public ResponseEntity<List<Product>> getAllProduct(@CookieValue(required=false, name = "customerId") String cookiecustomerId)
//	{
////		if(cookiecustomerId != "")
////		{
//		List<Product> products = productDAO.getAllProduct() ;
//		return ResponseEntity.ok(products);
////		}
////		return ResponseEntity.status(401).build();
//	}
	
	@RequestMapping(value ="/product", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, List<Product>>> getAllProduct(@CookieValue(required=false, name = "customerId") String cookiecustomerId)
	{
//		if(cookiecustomerId != "")
//		{
		List<Product> products = productDAO.getAllProduct() ;
		Map<String, List<Product>> map = new HashMap<>();
		map.put("productList", products);
		return ResponseEntity.ok(map);
//		}
//		return ResponseEntity.status(401).build();
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = "application/json")

	public ResponseEntity<Product> getProductById(@PathVariable int id, @CookieValue(required=false, name = "userRoll") String cookieUserRoll)
	{	
//		if(cookieUserRoll == "admin")
//		{
		Product product = productDAO.getProductById(id);
		return ResponseEntity.ok(product);
//		}
//		return ResponseEntity.status(401).build();
	}
	
	
	@RequestMapping(value ="/product", method = RequestMethod.POST)
	public ResponseEntity<String> addProduct(@RequestBody Product product,@CookieValue(required=false, name = "userRoll") String cookieUserRoll)
	{
//		if(cookieUserRoll == "admin")
//		{	
//		System.out.println(product.getProductAmount());
//		System.out.println(product.getProductName());
			int rowAffected = productDAO.addProduct(product);
			if(rowAffected > 0)
				return ResponseEntity.ok("successfully added");
			return ResponseEntity.internalServerError().build();
//		}
//			
//		return ResponseEntity.status(401).build();
	}
	
	@PutMapping("/product/{productId}")
	public ResponseEntity<String> updateProduct(@RequestBody Product product , @PathVariable int productId, @CookieValue(required=false, name = "userRoll") String cookieUserRoll)
	{
//		if(cookieUserRoll == "admin")
//		{
			product.setProductId(productId);
			int rowAffected = productDAO.updateProduct(product);
			if(rowAffected > 0)
				return ResponseEntity.ok("successfully updated");
			
			return ResponseEntity.internalServerError().build();
			
//		}
//		return ResponseEntity.status(401).build();
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id, @CookieValue(required=false, name = "userRoll") String cookieUserRoll)
	{
//		if(cookieUserRoll == "admin")
//		{
			int rowAffected = productDAO.deleteProduct(id);
			if(rowAffected > 0)
				return ResponseEntity.ok("successfully deleted");
		
			return ResponseEntity.internalServerError().build();
//		}
//		
//		return ResponseEntity.status(401).build();
	}

}
