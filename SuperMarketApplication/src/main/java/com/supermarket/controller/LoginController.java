package com.supermarket.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.model.Customer;
import com.supermarket.repository.LoginAndSignupDAO;



@CrossOrigin(origins = "http://127.0.0.1:5500")

@RestController


public class LoginController {
	
	@Autowired
	LoginAndSignupDAO loginAndSignUp;
	
	@GetMapping("/login")
	public ResponseEntity<Map<String,String>> checkLoginDetails(@RequestParam String userName, String userPassword, HttpServletResponse response)
	{
		HashMap<String, String> loginDetailMap = loginAndSignUp.checkLoginDetails(userName, userPassword);
		
		String customerId = null;
		String userRoll  = null;
		
		
		customerId = loginDetailMap.get("customerId");
		userRoll = loginDetailMap.get("customerRoll");
		
		
		if(userRoll == null && customerId == null)
			return ResponseEntity.notFound().build();
		
		else
		{
			Cookie cookie1 = new Cookie("customerId",customerId);
			Cookie cookie2 = new Cookie("customerRoll", userRoll);
		    response.addCookie(cookie1);
		    response.addCookie(cookie2);
		}
		
		return ResponseEntity.ok(loginDetailMap);
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> createAccout(@RequestBody Customer customer)
	{
		
		if(loginAndSignUp.createAccout(customer) > 0)
			return ResponseEntity.ok("successfully account created");
		
		if(loginAndSignUp.createAccout(customer) == 0)
			return ResponseEntity.ok("userName already exist");
		
		return ResponseEntity.internalServerError().build();
		
	}
	
	@GetMapping("/logout")
	public void logout(HttpServletResponse response)
	{
		Cookie cookie1 = new Cookie("customerId","");
		Cookie cookie2 = new Cookie("userRoll", "");
		cookie1.setMaxAge(0);
		cookie1.setSecure(true);
		cookie1.setHttpOnly(true);
		cookie1.setPath("/SuperMarketApplication");
		
		cookie2.setMaxAge(0);
		cookie2.setSecure(true);
		cookie2.setHttpOnly(true);
		cookie2.setPath("/SuperMarketApplication");

		response.addCookie(cookie1);
		response.addCookie(cookie2);

	}
	

}
