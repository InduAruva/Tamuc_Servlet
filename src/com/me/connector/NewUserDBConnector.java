package com.me.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.me.entity.BankDataBean;

public class NewUserDBConnector {
	private Statement stmt;
	private ResultSet rst;
	private Connection con;
	PreparedStatement pstmt=null;
	
	
	public boolean setConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
	
		 String userName = "root";
		 String password = "password";
		 String url = "jdbc:mysql://localhost/tamuc_bank";
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection(url,userName, password);
		return true;
	}
public void checkUser(String userID) throws SQLException{
		
		 System.out.println(userID);
		 String query1= "select * from t_users where username= ?";
	     pstmt=con.prepareStatement(query1);
	     pstmt.setString(1,userID);
	     rst= pstmt.executeQuery();
	}

	
	public boolean insertNewUser(BankDataBean bdb) throws SQLException
	{
		
	     checkUser(bdb.getUser_ID());
	     
	     if(rst.next()){
		 System.out.println("user id already taken:");
		 return false;
	     }
	     else{
		
		String username = bdb.getUser_ID();
		String password = bdb.getPassword();
		String first_name = bdb.getFirstName();
		String last_name = bdb.getLastName();
		String email_id = bdb.getEmail_ID();
		String dob_dt = bdb.getDateOfBirth();
		String address = bdb.getAddress();
		String acct_create_dt = bdb.getAccountCreateDate();
		String ps="insert into T_users(username,password,first_name,last_name,email_id,dob_dt,address,acct_create_dt)"+
		        "values"+"(?,?,?,?,?,?,?,?)";
		 pstmt=con.prepareStatement(ps);
		 pstmt.setString(1,username);
		 pstmt.setString(2,password);
		 pstmt.setString(3,first_name);
		 pstmt.setString(4,last_name);
		 pstmt.setString(5,email_id);
		 pstmt.setString(6,dob_dt);
		 pstmt.setString(7,address);
		 pstmt.setString(8,acct_create_dt);
		 pstmt.executeUpdate();
	  System.out.println("Inserted.");
	  try{
	  newUserAccount(bdb);
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  
	     }
	  return true;
	}
	public boolean newUserAccount(BankDataBean bdb) throws SQLException,Exception
	{
		String acct_type_c = bdb.getAccountType();
		String accounttype;
		String username = bdb.getUser_ID();
		float bal_a;
	//	PreparedStatement pstmt=null;
		System.out.println("Insert User Account Details:");
		String ps="insert into T_user_accounts(username,acct_type_c,bal_a)"+
        "values"+"(?,?,?)";
		if(acct_type_c=="C")
		{
			bal_a=bdb.getCheckingBal();
			accounttype ="C";
			pstmt=con.prepareStatement(ps);
			 pstmt.setString(1,username);
			 pstmt.setString(2,accounttype);
			 pstmt.setFloat(3,bal_a);
			 pstmt.executeUpdate();
		}
		if(acct_type_c=="S")
		{
			bal_a=bdb.getSavingBal();
			pstmt=con.prepareStatement(ps);
			accounttype ="S";
			 pstmt.setString(1,username);
			 pstmt.setString(2,accounttype);
			 pstmt.setFloat(3,bal_a);
			 pstmt.executeUpdate();
			
		}
		if(acct_type_c=="B")
		{
			bal_a=bdb.getCheckingBal();
			accounttype="C";
			pstmt=con.prepareStatement(ps);
			 pstmt.setString(1,username);
			 pstmt.setString(2,accounttype);
			 pstmt.setFloat(3,bal_a);
			 pstmt.executeUpdate();
			bal_a=bdb.getSavingBal();
			accounttype="S";
			pstmt=con.prepareStatement(ps);
			 pstmt.setString(1,username);
			 pstmt.setString(2,accounttype);
			 pstmt.setFloat(3,bal_a);
			 pstmt.executeUpdate();
		}
		System.out.println("New User Account Created.");
		        return true;
	}
	public boolean getUserInformation(BankDataBean bdb) throws SQLException,Exception
	{
	 System.out.println("Getting User Account");
	String query2 = "Select * from T_user_accounts"+
        " where username=?";
	System.out.println("executed");
	 String userID = bdb.getUser_ID();
	 pstmt=con.prepareStatement(query2);
 pstmt.setString(1,userID);
 rst= pstmt.executeQuery();
	 while(rst.next())
  {
 	if (rst.getString("acct_type_c").equals("C")){
 	
 		bdb.setCheckingAcctNum(rst.getInt("acct_num"));
 		int checkNum=rst.getInt("acct_num");
 		bdb.setCheckingBal(rst.getFloat("bal_a"));
 		System.out.println("checking account no:"+checkNum);
 		insertTransactions(bdb.getCheckingAcctNum(),bdb.getCheckingBal());
 	}
 	if (rst.getString("acct_type_c").equals("S")){
 		bdb.setSavingAcctNum(rst.getInt("acct_num"));
 		int savingNum=rst.getInt("acct_num");
 		bdb.setSavingBal(rst.getFloat("bal_a"));
 		System.out.println("Saving account no:"+savingNum);
 		insertTransactions(bdb.getSavingAcctNum(),bdb.getSavingBal());
 	}
 		
 	
  }
	 return true;
	}
	
	public boolean insertTransactions(int acctnum, float amount) throws SQLException,Exception{
		String ps="insert into T_user_transactions(acct_num,tran_type,bal_a) "+
		        " values"+"(?,?,?)";
		pstmt=con.prepareStatement(ps);
		 pstmt.setInt(1,acctnum);
		 pstmt.setString(2,"deposit");
		 pstmt.setFloat(3,amount);
		 pstmt.executeUpdate();
		
		return true;
	}
	public void booleanClose() throws SQLException{
		rst.close();
		 con.close();
	}
}
