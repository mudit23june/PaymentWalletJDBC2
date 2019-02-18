package com.capgemini.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.capgemini.connect.*;

import com.capgemini.beans.Customer;

public class WalletRepoImpl implements WalletRepo
{
	ConnectionJDBC ob = new ConnectionJDBC();

	@Override
	public boolean save(Customer customer)
	{
		Connection con = ob.getConnection();
		try
		{
			PreparedStatement stmt = con.prepareStatement("insert into paywallet values(?,?,?)");
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getMobileNumber());
			stmt.setLong(3, customer.getWallet());
			stmt.executeQuery();
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return true;
	}

	public Customer findOne(String mobilenumber)
	{
		Customer c = new Customer();
		Connection con = ob.getConnection();
		try
		{
			PreparedStatement stmt = con.prepareStatement("select * from paywallet where mobile=?");
			stmt.setString(1, mobilenumber);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				c.setName(rs.getString(1));
				c.setMobileNumber(rs.getString(2));
				c.setWallet(rs.getInt(3));
			}
			con.close();
			return c;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
}
