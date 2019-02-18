package com.capgemini.connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionJDBC
{
	private static Connection con;
	public Connection getConnection()
	{
		try
		{  
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Capgemini1234");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}
}