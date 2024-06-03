package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.model.FinancialAccount;

public class FinancialDao {
	
	DatabaseConnection dbCon = new DatabaseConnection();
	
	public void add(FinancialAccount account) {
		String sql = "INSERT INTO financial_account (date, type, description, person, value) values (?, ?, ?, ?, ?)";
		
		try {
			Connection con = dbCon.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			Date utilDate = account.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
			ps.setDate(1, sqlDate);
			ps.setString(2, account.getType());
			ps.setString(3, account.getDescription());
			ps.setString(4, account.getPerson());
			ps.setDouble(5, account.getValue());
			
			ps.executeUpdate();
			
			ps.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void update(FinancialAccount account) {
		String sql = "UPDATE financial_account SET date=?, type=?, description=?, person=?, value=? WHERE id=?";
		
		try {
			Connection con = dbCon.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			Date utilDate = account.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
			ps.setDate(1, sqlDate);
			ps.setString(2, account.getType());
			ps.setString(3, account.getDescription());
			ps.setString(4, account.getPerson());
			ps.setDouble(5, account.getValue());
			ps.setInt(6, account.getId());
			
			ps.executeUpdate();
			
			ps.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void delete(int id) {
		String sql = "DELETE FROM financial_account WHERE id=?";
		
		try {
			Connection con = dbCon.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			ps.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void getAccountById(FinancialAccount account) {
		String sql = "SELECT * FROM financial_account WHERE id = ?";
		
		try {
			Connection con = dbCon.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, account.getId());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				account.setId(rs.getInt("id"));
				account.setDate(rs.getDate("date"));
				account.setType(rs.getString("type"));
				account.setDescription(rs.getString("description"));
				account.setPerson(rs.getString("person"));
				account.setValue(rs.getDouble("value"));
            }
			
			ps.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<FinancialAccount> getAll() {
		ArrayList<FinancialAccount> accounts = new ArrayList<>();
		String sql = "SELECT * FROM financial_account ORDER BY date DESC";
		
		try {
			Connection con = dbCon.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				FinancialAccount account = new FinancialAccount();
				account.setId(rs.getInt("id"));
				account.setDate(rs.getDate("date"));
				account.setType(rs.getString("type"));
				account.setDescription(rs.getString("description"));
				account.setPerson(rs.getString("person"));
				account.setValue(rs.getDouble("value"));
				accounts.add(account);
            }
			
			ps.close();
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
		return accounts;
	}

}
