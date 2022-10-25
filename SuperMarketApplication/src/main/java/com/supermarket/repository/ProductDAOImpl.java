package com.supermarket.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.supermarket.model.Product;

@Component
public class ProductDAOImpl implements ProductDAO{
	
	@Override
	public List<Product> getAllProduct() {
		Connection con = null;
		con = ConnectionClass.getConnection();
		List<Product> products = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from product");
			while(rs.next())
			{
				Product product = new Product();
				product.setProductId(rs.getInt("productId"));
				product.setProductName(rs.getString("productName"));
				product.setProductQuantity(rs.getInt("productQuantity"));
				product.setProductAmount(rs.getFloat("productAmount"));
				
				products.add(product);
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
		
		return products;
	}

	@Override
	public Product getProductById(int productId) {
		Connection con = ConnectionClass.getConnection();
		Product product = new Product();
		try {
			PreparedStatement pst = con.prepareStatement("select * from product where productId = ?");
			pst.setInt(1, productId);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				product.setProductId(rs.getInt("productId"));
				product.setProductName(rs.getString("productName"));
				product.setProductQuantity(rs.getInt("productQuantity"));
				product.setProductAmount(rs.getFloat("productAmount"));
				
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
		
		return product;
	}

	@Override
	public int addProduct(Product product) {
		
		int noOfRowsAffected = 0;
		Connection con = null;
		con = ConnectionClass.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("insert into product (productName,productQuantity,productAmount) values(?,?,?)");
			pst.setString(1, product.getProductName());
			pst.setInt(2, product.getProductQuantity());
			pst.setFloat(3, product.getProductAmount());
			 
			noOfRowsAffected = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public int updateProduct(Product product) {
		
		int noOfRowsAffected = 0;
		Connection con = null;
		con = ConnectionClass.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("update product set productName = ?, productQuantity = ?, productAmount = ? where productId=?");
			pst.setString(1, product.getProductName());
			pst.setInt(2, product.getProductQuantity());
			pst.setFloat(3, product.getProductAmount());
			pst.setInt(4, product.getProductId());
			
			noOfRowsAffected = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public int deleteProduct(int productId) {
		int noOfRowsAffected = 0;
		Connection con = null;
		con = ConnectionClass.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("delete from  product where productId = ?");
			pst.setInt(1, productId);
			noOfRowsAffected = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

}
