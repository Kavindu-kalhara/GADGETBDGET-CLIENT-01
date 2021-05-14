package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin {
	
	
		
		//A common method to connect to the DB
	//A common method to connect to the DB
	private Connection connect(){
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/buyer2", "root", "root");
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return con;
	}
			
		
		
		//Insert Project Details
		public String insertCart(String BuyerID, String BuyerName, String NIC, String Email,String PhoneNumber){
			String output = "";
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for inserting."; 
				}
				
					
					// create a prepared statement
					String query = "INSERT INTO `adminbuyer2`(`BuyerID`, `BuyerName`, `NIC`, `Email`, `PhoneNumber`) VALUES (?,?,?,?,?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, BuyerName);
					 preparedStmt.setString(3, NIC);
					 preparedStmt.setString(4, Email);
					 preparedStmt.setString(5, PhoneNumber);
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newCart = readCart(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newCart + "\"}";
					 
					 }catch (Exception e)
					 {
						 
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the order to cart.\"}"; 
						 System.err.println(e.getMessage());
					 }
			 return output;
		 }
		
		
		
		
		public String readCart(){
			String output = "";
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for reading."; 
			}
					
				// Prepare the html table to be displayed
				output = 
						"<table border='1' >"+ 
						"<tr >" 
						
							+"<th >Buyer Name</th>" +
							 "<th>NIC</th>" +
							 "<th>Email</th>" +
							 "<th>Phone Number</th>" +
							 "<th>Update</th>" +
							 "<th>Remove</th>" +
						
						 "</tr>";
	
				String query = "select * from `adminbuyer2`";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 
					 String BuyerID =  Integer.toString(rs.getInt("BuyerID"));
					 String BuyerName = rs.getString("BuyerName");
					 String NIC = rs.getString("NIC");
					 String Email = rs.getString("Email");
					 String PhoneNumber =  rs.getString("PhoneNumber");
	
					 
					 // Add into the html table
					 
					 //output += "<tr><td>" + BuyerID + "</td>";
					 output += "<tr><td>" + BuyerName + "</td>";
					 output += "<td>" + NIC + "</td>";
					 output += "<td>" + Email + "</td>";
					 output += "<td>" + PhoneNumber + "</td>";
		
					 
					 
					 // buttons
					
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-userid='" + BuyerID + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-userid='" + BuyerID + "'></td></tr>"; 
				 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 
			 
			 }catch (Exception e){
				 
				 output = "Error while reading the cart buyers.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 
		}
		
		
		
		public String updateCart(String BuyerID, String BuyerName, String NIC,String Email, String PhoneNumber){ 
			String output = ""; 
			try{
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for updating."; 
				} 
				
				 // create a prepared statement
				String query = "UPDATE `adminbuyer2` SET `BuyerName`=?,`NIC`=?,`Email`=?,`PhoneNumber`=? WHERE 'BuyerID'=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				  
				 preparedStmt.setString(1, BuyerName);
				 preparedStmt.setString(2, NIC);
				 preparedStmt.setString(3, Email); 
				 preparedStmt.setString(4, PhoneNumber);
				 preparedStmt.setString(5, BuyerID);
				 
				// preparedStmt.setString(4, sector);
				
				 
 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newCart = readCart(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newCart + "\"}";
				 
		
				 } catch (Exception e) {
					 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the cart buyer.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
		 }
		
		

		public String deleteCart(String BuyerID) { 
			String output = ""; 
			try{ 
				Connection con = connect();
				if (con == null) { 
					return "Error while connecting to the database for deleting."; 
				} 
					// create a prepared statement
				    String query ="DELETE FROM `adminbuyer2` WHERE BuyerID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(BuyerID)); 
					
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					
					String newCart = readCart(); 
					output = "{\"status\":\"success\", \"data\": \"" + newCart + "\"}"; 
					
			} catch (Exception e) { 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the cart buyer.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}
		
}