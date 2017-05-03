package com.me.adaptor;

import java.sql.SQLException;

import com.me.connector.LoginDBConnector;
import com.me.connector.NewUserDBConnector;
import com.me.entity.BankDataBean;

public class Validation {

public boolean validationTest(BankDataBean bdb) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
{
	boolean rst=false;
	LoginDBConnector con=new LoginDBConnector();
	con.setConnection();
	rst=con.validLogin(bdb);
	return rst;
	
}
public boolean newUserTest(BankDataBean bdb) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
{
	boolean rst=false;
	NewUserDBConnector con=new NewUserDBConnector();
	con.setConnection();
	rst=con.insertNewUser(bdb);
	return rst;
	
}
}
