package model;

import java.sql.*;

import connection.connect;

public class product {
	
	connect con = new connect();
	//for Insert Details....
	public String insertProductDetails(String name, String code, String qty, String price, String desc) {
		String status = ""; 
		try
		 {			
					Connection c = con.getConnection();
					
					String insertQuery = " insert into gbweb (productName, productCode, productQty, productPrice, productDesc)" +" values(?,?,?,?,?)";
					PreparedStatement pst = c.prepareStatement(insertQuery);
						pst.setString(1, name); 
						pst.setString(2, code); 
						pst.setInt(3, Integer.parseInt(qty)); 
						pst.setDouble(4, Double.parseDouble(price));
						pst.setString(5, desc);	             
					pst.execute(); 
				c.close(); 
				status = "Product data Inserted Successfully. ";
				System.out.println("Inserted Successfully...");
				
		}catch (Exception ex){
			ex.printStackTrace();
			System.err.println("Inserted Unsuccessfully!!!");	 
		} 
		return status; 
		
	}
	//for Update Details....
	public String updateProductDetails(String id, String name, String code, String qty, String price, String desc) {
		String status = ""; 
		try
		 {
				Connection c = con.getConnection();
				
				String updateQuery = "update gbweb set productName=?,productCode=?,productQty=?,productPrice=?,productDesc=? where productId=?";
				PreparedStatement pst = c.prepareStatement(updateQuery);
					pst.setString(1, name); 
					pst.setString(2, code); 
					pst.setInt(3, Integer.parseInt(qty)); 
					pst.setDouble(4, Double.parseDouble(price));
					pst.setString(5, desc);	 
					pst.setInt(6, Integer.parseInt(id)); 
					pst.executeUpdate(); 
				c.close(); 
				String newItems = readProductDetails();
				status = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
				
		}catch (Exception ex){
				ex.printStackTrace();
				status = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
		} 
		return status; 
		
	}
	//for Delete Details....
	public String deleteProductDetails(String id) {
		String status = ""; 
		try
		{
			Connection c = con.getConnection();
			
			String deleteQuery = "delete from gbweb where productId=?"; 
			PreparedStatement pst = c.prepareStatement(deleteQuery);
				pst.setInt(1,Integer.parseInt(id));	             
				pst.execute(); 
			c.close(); 
			String newItems = readProductDetails();
			status = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
					
		}catch (Exception ex){
			ex.printStackTrace();
			status = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";	 
		} 
			return status; 
	}
	//for read Details....
	public String readProductDetails() 
	{ 
		String status = ""; 
		try	{
				Connection c = con.getConnection();
				 // for make table...
				status = "<table border=\"1\"><tr><th>Product Id</th><th>Product Name</th><th>Product Code</th><th>Product Qty</th><th>Product Price</th><th>Product Description</th><th>Update</th><th>Remove</th></tr>";
				
				 String displayQuery = "select * from gbweb"; 
				 Statement st = c.createStatement(); 
				 ResultSet rs = st.executeQuery(displayQuery); 
				 
					 while (rs.next()) 
					 { 
						 String pId = Integer.toString(rs.getInt("productId")); 
						 String pName = rs.getString("productName"); 
						 String pCode = rs.getString("productCode"); 
						 String pQty = Integer.toString(rs.getInt("productQty")); 
						 String pPrice = Double.toString(rs.getDouble("productPrice")); 
						 String pDesc = rs.getString("productDesc"); 
						 // add into the html table...
						 status += "<tr><td>"+pId+"</td>";
						 status += "<td>" + pName + "</td>"; 
						 status += "<td>" + pCode+ "</td>"; 
						 status += "<td>" + pQty+ "</td>"; 
						 status += "<td>" + pPrice + "</td>"; 
						 status += "<td>" + pDesc + "</td>"; 
						 
						// buttons
						status += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-itemid='" + pId + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + pId + "'></td></tr>";
					 } 
				 c.close(); 
				 status +=  "</table>"; 
			}catch (Exception ex) { 
				 status = "Data Reading error!!!"; 
				 System.err.println(ex.getMessage()); 
		} 
		return status; 
	} 		

}
			
			
