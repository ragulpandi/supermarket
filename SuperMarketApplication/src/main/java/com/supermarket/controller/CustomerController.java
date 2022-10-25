package com.supermarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.model.BillGenerater;
import com.supermarket.model.Customer;
import com.supermarket.model.Product;
import com.supermarket.repository.CustomerDAOImpl;

@CrossOrigin(origins = "http://127.0.0.1:5500")

@RestController
//@RequestMapping("/user")
public class CustomerController {
		
	@Autowired
	private CustomerDAOImpl customerDAO;
	
	@GetMapping("/user/{customerId}")
	public ResponseEntity<Customer> getCustomerDetailsById(@PathVariable int customerId)
	{
		Customer customer = customerDAO.getCustomerDetailsById(customerId);
		return ResponseEntity.ok(customer);
	}
	
	@RequestMapping(value ="/reset/{customerId}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> resetData(@PathVariable int customerId, @RequestBody Customer customer, @CookieValue(required=false, name = "userRoll") String cookieUserRoll)
	{
//		if(cookieUserRoll == "Customer")
//		{
			customer.setCustomerId(customerId);
			if(customerDAO.updateCustomerDetails(customer) > 0)
				return ResponseEntity.ok("Update successfully");
			if(customerDAO.updateCustomerDetails(customer) == -1)
				return ResponseEntity.ok("userName already exist");
			return ResponseEntity.internalServerError().build();
//		}
		
		//return ResponseEntity.status(401).build();
	}
	
//	@GetMapping("/product")
//	public ResponseEntity<List<Product>> getAllProduct()
//	{
//		List<Product> products = productDAO.getAllProduct() ;
//		
//		HttpHeaders responseHeaders = new HttpHeaders();
//        return new ResponseEntity<>(products, responseHeaders, HttpStatus.OK);
//	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> addToCart(@RequestBody BillGenerater billGenerater, @CookieValue(required=false, name = "userRoll") String cookieUserRoll)
	{
//		if(cookieUserRoll == "Customer")
//		{
			if(billGenerater.getOrderQuantity() == 0)
				return ResponseEntity.ok("Quantity can't be zero");
			if(customerDAO.addIntoBill(billGenerater) > 0)
				return ResponseEntity.ok("successfully add to cart");
			else
				return ResponseEntity.internalServerError().build();
//		}
//		
//		return ResponseEntity.status(401).build();
	}
	
	@RequestMapping(value = "/cart/{customerId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, List<Product>>> getListFromCart(@PathVariable int customerId, @CookieValue(required=false, name ="customerRoll") String cookieUserRoll)
	{
		
//		System.out.println(cookieUserRoll);
//		if(cookieUserRoll.equals("Customer"))
//		{
			//System.out.println("hello");
			List<Product> billGenerater = customerDAO.getCartList(customerId);	
			Map<String, List<Product>> map = new HashMap<>();
			map.put("cartList", billGenerater);
			return ResponseEntity.ok(map);
//		}
//		return ResponseEntity.status(401).build();
	}
	
	@RequestMapping(value = "/cart/remove/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeItemFromCart(@PathVariable int productId, @CookieValue(required=false, name = "userRoll") String cookieUserRoll)
	{
//		if(cookieUserRoll == "Customer")
//		{
			if(customerDAO.removeFromCart(productId) > 0)
				return ResponseEntity.ok("successfully removed from cart");
			return ResponseEntity.internalServerError().build();
//		}
//		return ResponseEntity.status(401).build();
	}
	
	@RequestMapping(value = "/cart/print/{customerId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, List<Product>>> billPrinter(@PathVariable int customerId, @CookieValue(required=false, name = "userRoll") String cookieUserRoll)
	{
		
//		if(cookieUserRoll == "Customer")
//		{
			List<Product> bills = customerDAO.billPrint(customerId);
			Map<String, List<Product>> map = new HashMap<>();
			map.put("billList", bills);
			return ResponseEntity.ok(map);
//		}
//		return ResponseEntity.status(401).build();
	}
	
}
