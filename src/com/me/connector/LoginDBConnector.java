package com.me.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.me.entity.BankDataBean;

public class LoginDBConnector {

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
		System.out.println("Connection made:" +con);
		return true;
	}
	public boolean validLogin(BankDataBean bdb ) throws SQLException{
		 System.out.println("Inside Valid Login"+con);
		 String userID=bdb.getUser_ID();
	     checkUser(userID);
	     String enteredPassword = bdb.getPassword();
	     System.out.println(enteredPassword);
	     System.out.println("Query executed.");
	     while(rst.next())
	     {
	    String userName=rst.getString("username");
	    String passwrd=rst.getString("password");
	    if(enteredPassword.equals(passwrd))
	    {
	    String firstName=rst.getString("first_name");
	    String lastName=rst.getString("last_name");
	    String emailID=rst.getString("email_id");
	    String dateOfBirth=rst.getString("dob_dt");
	    String add=rst.getString("address");
	    Date accountCreateDate=rst.getDate("acct_create_dt");
	    bdb.setUser_ID(userName);
	    bdb.setPassword(passwrd);
	    bdb.setFirstName(firstName);
	    bdb.setLastName(lastName);
	    bdb.setEmail_ID(emailID);
	    bdb.setDateOfBirth(dateOfBirth);
	    bdb.setAddress(add);
	    return true;
	   //  acctDate = (String) accountCreateDate;
	   // dlb.setAccountCreateDate(accountCreateDate);   	
	     }
	    else
	    {
	    	System.out.println("Password did not match");
	    	return false;
	    }
	     }
	
	    System.out.println("no sucj user id exist");
	     return false;
	}	
	public void checkUser(String userID) throws SQLException{
		
		 System.out.println(userID);
		 String query1= "select * from t_users where username= ?";
	     pstmt=con.prepareStatement(query1);
	     pstmt.setString(1,userID);
	     rst= pstmt.executeQuery();
	}
	public boolean getUserInformation(BankDataBean bdb) throws SQLException
 	{
 	 System.out.println("Getting User Account");
 	String query2 = "Select * from T_user_accounts"+
            " where username=?";
 	 String userID = bdb.getUser_ID();
 	 pstmt=con.prepareStatement(query2);
     pstmt.setString(1,userID);
     rst= pstmt.executeQuery();
 	 while(rst.next())
      {
     	if (rst.getString("acct_type_c").equals("C")){
     	
     		bdb.setCheckingAcctNum(rst.getInt("acct_num"));
     		bdb.setCheckingBal(rst.getFloat("bal_a"));
     	}
     	if (rst.getString("acct_type_c").equals("S")){
     		bdb.setSavingAcctNum(rst.getInt("acct_num"));
     		bdb.setSavingBal(rst.getFloat("bal_a"));
     	}
     		
     	
      }
 	 return true;
 	}
	public ArrayList getTransactionInformation(int savingAcctNo, int checkingAcctNo) throws SQLException
	{
		System.out.println("Getting Transaction Information");
	 	String query2 = "Select * from T_user_Transactions"+
	            " where acct_num in(?,?)";
	 	 pstmt=con.prepareStatement(query2);
	     pstmt.setInt(1,savingAcctNo);
	     pstmt.setInt(2, checkingAcctNo);
	     rst= pstmt.executeQuery();
		ArrayList<String> accountDetails = new ArrayList<>(); 
		while(rst.next())
		{
		  String acctNo = Integer.toString(rst.getInt("acct_num"));
		  String transaction_Type = rst.getString("tran_type");
		  String balance = Float.toString(rst.getFloat("bal_a"));
		  String str="User holding account no:"+acctNo+" has "+transaction_Type+"ed $"+balance;
		  accountDetails.add(str);
		}
		return accountDetails;
	}
	public void booleanClose() throws SQLException{
		rst.close();
		 con.close();
	}

}
