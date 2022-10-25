package com.supermarket.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.supermarket.model.Customer;

@Component
public class LoginAndSignupDAO {
	
	public HashMap<String, String> checkLoginDetails(String userName, String userPassword)
	{
		Connection con = ConnectionClass.getConnection();
		HashMap<String, String> map = new HashMap<>();
		int customerId = 0;
		String userRoll = null;
		try {
			PreparedStatement pst = con.prepareStatement("select customerId, userRoll from loginDetails where userName = ? and userPassword = ?");
			pst.setString(1, userName);
			pst.setString(2, userPassword);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				customerId = rs.getInt("customerId");
				userRoll = rs.getString("userRoll");
			}
			
			map.put("customerId", customerId+"");
			map.put("customerRoll", userRoll);
			if(userRoll != null && customerId != 0)
			{
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return map;
		
	}
	
	public int createAccout(Customer customer)
	{
		Connection con = ConnectionClass.getConnection();
		int noOfRowAffected = 0;
		try {
			PreparedStatement pst = con.prepareStatement("select customerId from loginDetails where userName = ?");
			pst.setString(1, customer.getUserName());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				return 0;
			}
			
			pst = con.prepareStatement("insert into customerDetails (customerName, customerEmail, customerPhn) values (?,?,?)");
			pst.setString(1, customer.getCustomerName());
			pst.setString(2, customer.getCustomerEmail());
			pst.setString(3, customer.getCustomerPhn());
			if(pst.executeUpdate() > 0)
			{
				pst = con.prepareStatement("insert into loginDetails (userName, userPassword) values(?,?)");
				pst.setString(1, customer.getUserName());
				pst.setString(2, customer.getUserPassword());
				noOfRowAffected = pst.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return noOfRowAffected;
	}
}
