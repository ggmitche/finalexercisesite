package com.aca.fitnessapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDbUtil {

	public static String connectionUrl = 
		"jdbc:mariadb://localhost:3306/fitnessapp?user=root&password=deatscythe580!";
	
	public static Connection getConnection() {
		Connection connection = null;

			try {
				Class.forName("org.mariadb.jdbc.Driver"); //Looks for a class with the driver in it
				connection = DriverManager.getConnection(connectionUrl);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
		
		return connection;
		

	}
	public static void main(String[] args) {
		Connection conn = MariaDbUtil.getConnection();
		if (null !=conn) {
			System.out.println("Tomcat server connection established");
			
			
			
		} else {
			System.out.println("Help.  Connection is null.");
		}
	}

}
