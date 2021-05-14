package com;

import java.sql.*;

public class Project 
{
	
		//A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectmanagement1", "root", "");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return con;
		}
		
		
		
		public String insertProject(String title, String description, String document, String video)
		{
			String output = "";
			try
			{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for inserting."; 
				}
				
				// create a prepared statement
				String query = " insert into project"
						+"(`projectID`,`projectTitle`,`projectDescription`,`projectProposalLink`,`projectVideoLink`)"
						+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, title);
				preparedStmt.setString(3, description);
				preparedStmt.setString(4, document);
				preparedStmt.setString(5, video);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newProject = readProject(); 
				output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
			}
			catch (Exception e)
			{
				 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
				 System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		public String readProject()
		{
			String output = "";
			try
			{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Project Title</th><th>Project Description</th>" +
						"<th>Project Proposal </th>" +
						"<th>Project Video</th>" +
						"<th>Update</th><th>Remove</th></tr>";

				String query = "select * from project";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String projectID = Integer.toString(rs.getInt("projectID"));
					String projectTitle = rs.getString("projectTitle");
					String projectDescription = rs.getString("projectDescription");
					String projectProposalLink = rs.getString("projectProposalLink");
					String projectVideoLink = rs.getString("projectVideoLink");
					
					// Add into the html table
					output += "<tr><td><input id='hidProjectIDUpdate' name='hidProjectIDUpdate' type='hidden' value='"+ projectID +"'>" + projectTitle + "</td>";
					output += "<td>" + projectDescription + "</td>";
					output += "<td>" + projectProposalLink + "</td>";
					output += "<td>" + projectVideoLink + "</td>";
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='"+projectID+"'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-itemid='" + projectID + "'></td></tr>"; 
				 }
				
				 con.close();
				 
				 // Complete the html table
				 output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the projects.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		 
		
		
		
		public String updateProject(String ID, String title, String desc, String link, String video)
		{
			String output = "";
			try {
				Connection con = connect();
				
				if (con == null) 
				{
					return "Error while connecting to the database for updating.";
				}
				
				// create a prepared statement
				String query = "UPDATE project SET projectTitle=?,projectDesc=?,projectProposalLink=?,projectVideoLink=? WHERE projectID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, title);
				preparedStmt.setString(2, desc);
				preparedStmt.setString(3, link);
				preparedStmt.setString(4, video);
				preparedStmt.setInt(5, Integer.parseInt(ID));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newProject = readProject(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
				
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		
		
		public String deleteProject(String projectID)
		{
			 String output = "";
			 
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for deleting."; 
				 }
				 
				 // create a prepared statement
				 String query = "delete from project where projectID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(projectID));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 String newProject = readProject(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
				 
			 }
			 catch (Exception e)
			 {
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
				 System.err.println(e.getMessage());
			 }
			 
			 return output;
		}
}
