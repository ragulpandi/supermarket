package com.supermarket.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.supermarket.model.BillGenerater;
import com.supermarket.model.Customer;
import com.supermarket.model.Product;

@Component
public class CustomerDAOImpl implements CustomerDAO{
	
	@Override
	public int updateCustomerDetails(Customer customer) {
		Connection con = null;
		con = ConnectionClass.getConnection();
		
		boolean flag = false;
		boolean flag1 = false;
		
		int customerId = customer.getCustomerId();
		String customerName = customer.getCustomerName();
		String customerEmail = customer.getCustomerEmail();
		String customerPhn = customer.getCustomerPhn();
		String userName = customer.getUserName();
		String userPassword = customer.getUserPassword();
		
		try {
			
			PreparedStatement pst = con.prepareStatement("update customerDetails set customerName = ?, customerEmail = ?, customerPhn = ? where customerId = ?");
			pst.setString(1, customerName);
			pst.setString(2, customerEmail);
			pst.setString(3, customerPhn);
			pst.setInt(4, customerId);
			if(pst.executeUpdate() > 0 )
				flag = true;
			
			pst = con.prepareStatement("update loginDetails set userName = ?, userPassword = ? where customerId = ?");
			pst.setString(1, userName);
			pst.setString(2, userPassword);
			pst.setInt(3, customerId);
			if(pst.executeUpdate() > 0)
				flag1 = true;
			
			if(flag && flag1)
				return 1;
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	@Override
	public Customer getCustomerDetailsById(int customerId)
	{
		Connection con = null;
		con = ConnectionClass.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("select customerName,customerEmail, customerPhn, userName, userPassword from customerDetails c join loginDetails l using (customerId) where customerId=?");
			pst.setInt(1, customerId);
			ResultSet rs = pst.executeQuery();
			rs.next();
			Customer customer = new Customer();
			
			customer.setCustomerName(rs.getString("customerName"));
			customer.setCustomerEmail(rs.getString("customerEmail"));
			customer.setCustomerPhn(rs.getString("customerPhn"));
			customer.setUserName(rs.getString("userName"));
			customer.setUserPassword(rs.getString("userPassword"));
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int addIntoBill(BillGenerater billGenerater) {
		Connection con = null;
		int noOfRowAffected = 0;
		float amountPerItem = 0;
		con = ConnectionClass.getConnection();
		try {
			
			PreparedStatement pst = con.prepareStatement("select productQuantity, productAmount from product where productId = ?");
			pst.setInt(1, billGenerater.getProductId());
			ResultSet rs = pst.executeQuery();
			int initalQuantity = 0;
			while(rs.next())
			{
				initalQuantity = rs.getInt("productQuantity");
				amountPerItem = rs.getFloat("productAmount");
			}
			
			if(initalQuantity >= billGenerater.getOrderQuantity() && billGenerater.getOrderQuantity() > 0)
			{
				pst = con.prepareStatement("insert into billGenerater (customerId, productId, orderQuantity, amount) values(?,?,?,?)");
				pst.setInt(1, billGenerater.getCustomerId());
				pst.setInt(2, billGenerater.getProductId());
				pst.setInt(3,  billGenerater.getOrderQuantity());
				pst.setFloat(4, amountPerItem*billGenerater.getOrderQuantity());
				noOfRowAffected = pst.executeUpdate();
				
				pst = con.prepareStatement("update product set productQuantity = ? where productId = ?");
				pst.setInt(1, (initalQuantity - billGenerater.getOrderQuantity()));
				pst.setInt(2, billGenerater.getProductId());
				pst.executeUpdate();
				
			}
			else
			{
				return 0;
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
	
	@Override
	public List<Product> getCartList(int customerId)
	{

		Connection con = null;
		con = ConnectionClass.getConnection();
		List<Product> cartList = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement("select b.productId, p.productName, b.orderQuantity, b.amount from billGenerater b join product p using (productId) where customerId = ?");
			pst.setInt(1, customerId);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Product cartItem = new Product();
				cartItem.setProductId(rs.getInt("productId"));
				cartItem.setProductName(rs.getString("productName"));
				cartItem.setProductQuantity(rs.getInt("orderQuantity"));
				cartItem.setProductAmount(rs.getFloat("amount"));
				cartList.add(cartItem);
			}
		}catch (SQLException e) {
				e.printStackTrace();
			}finally
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return cartList;
	}

	@Override
	public int removeFromCart(int productId) {
		int noOfRowsAffected = 0;
		int orderQuantity = 0;
		int productQuantity = 0;
		Connection con = null;
		con = ConnectionClass.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("select orderQuantity from  billGenerater where productId = ?");
			pst.setInt(1, productId);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				orderQuantity += rs.getInt("orderQuantity");
			}
			
			pst = con.prepareStatement("delete from billGenerater where productId = ?");
			pst.setInt(1, productId);
			noOfRowsAffected = pst.executeUpdate();
			
			if(noOfRowsAffected > 0)
			{
				pst = con.prepareStatement("select productQuantity from product where productId = ?");
				pst.setInt(1, productId);
				rs = pst.executeQuery();
				while(rs.next())
				{
					productQuantity = rs.getInt("productQuantity");
				}
				pst = con.prepareStatement("update product set productQuantity = ? where productId = ?");
				pst.setInt(1, orderQuantity+productQuantity);
				pst.setInt(2, productId);
				noOfRowsAffected = pst.executeUpdate();
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
		
		return noOfRowsAffected;
	}

	@Override
	public List<Product> billPrint(int customerId) {
		
		Connection con = null;
		con = ConnectionClass.getConnection();
		List<Product> bills = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement("select p.productName, b.orderQuantity, b.amount from billGenerater b join product p using (productId) where customerId = ?");
			pst.setInt(1, customerId);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Product billPrinter = new Product();
				billPrinter.setProductName(rs.getString("productName"));
				billPrinter.setProductQuantity(rs.getInt("orderQuantity"));
				billPrinter.setProductAmount(rs.getFloat("amount"));
				bills.add(billPrinter);
			}
			
			pst = con.prepareStatement("delete from billGenerater where customerId = ?");
			pst.setInt(1, customerId);
			pst.executeUpdate();
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
	
		return bills;
	}
}







