package com.capgemini.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capgemini.beans.Customer;
import com.capgemini.connect.ConnectionJDBC;
import com.capgemini.repository.WalletRepo;
import com.capgemini.repository.WalletRepoImpl;

public class WalletServiceImpl implements WalletService
{
	WalletRepo walletRepo = new WalletRepoImpl();
	ConnectionJDBC ob = new ConnectionJDBC();
	
	@Override
	public Customer createAccount(String name, String mobileNumber, long amount)
	{
		Customer customer=new Customer(name, mobileNumber, amount);
		walletRepo.save(customer);
		return customer;
	}

	@Override
	public Customer showBalance(String mobileNumber)
	{
		if(walletRepo.findOne(mobileNumber)!=null)
			return walletRepo.findOne(mobileNumber);
		else
			return null;
	}

	@Override
	public Customer fundTransfer(String sourceMobileNumber, String targetMobileNumber, long amount) {
		if(walletRepo.findOne(sourceMobileNumber)==null)
		{
			return null;
		}
		if(walletRepo.findOne(targetMobileNumber)==null)
		{
			return null;
		}
		this.depositAmount(targetMobileNumber, amount);
		this.withdrawAmount(sourceMobileNumber, amount);
		return walletRepo.findOne(sourceMobileNumber);
	}

	@Override
	public Customer depositAmount(String mobileNumber, long amount)
	{
		Connection con = ob.getConnection();
		if(walletRepo.findOne(mobileNumber)==null)
		{
			return null;
		}
		Customer customer=walletRepo.findOne(mobileNumber);
		try
		{
			PreparedStatement stmt=con.prepareStatement("update paywallet set wallet = ? where mobile=?");
			stmt.setLong(1, customer.getWallet()+amount);
			stmt.setString(2, mobileNumber);
			stmt.executeQuery();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public Customer withdrawAmount(String mobileNumber, long amount)
	{
		Connection con = ob.getConnection();
		if(walletRepo.findOne(mobileNumber)==null)
		{
			return null;
		}
		Customer customer=walletRepo.findOne(mobileNumber);
		try
		{
			PreparedStatement stmt=con.prepareStatement("update paywallet set wallet = ? where mobile=?");
			stmt.setLong(1, customer.getWallet()-amount);
			stmt.setString(2, mobileNumber);
			stmt.executeQuery();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return customer;
	}

}
