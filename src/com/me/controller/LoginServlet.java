package com.me.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.adaptor.Validation;
import com.me.connector.LoginDBConnector;
import com.me.entity.BankDataBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean rst=false;
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		BankDataBean bdb=new BankDataBean();
		String userId =request.getParameter("userID");
		bdb.setUser_ID(userId);
		String password =request.getParameter("password");
	    bdb.setPassword(password);
	    Validation vd=new Validation();
	    try {
			rst=vd.validationTest(bdb);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if(rst)
	    {
	    	LoginDBConnector ld=new LoginDBConnector();
	    	try {
				ld.setConnection();
				ld.getUserInformation(bdb);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	String fName=bdb.getFirstName();
	    	String lName=bdb.getLastName();
	    	String emailId=bdb.getEmail_ID();
	    	int savingAcctNum=bdb.getSavingAcctNum();
		    int checkingAcctNum=bdb.getCheckingAcctNum();
		    
	    	out.write("<html><head> <title>Account Details</title>");
	    	out.write("<center> <b>"+fName+" "+lName+" Account Details </b></center>");
	    	out.print("<form action='FormServlet'>");
	    	out.print("First Name:"+fName+" <br/>");
	    	out.print("Last Name :"+lName+"<br/>");
	    	out.print("email ID  :"+emailId+"<br/>");
	    	
	    	if(savingAcctNum>0)
		    {
		    	float savingBal=bdb.getSavingBal();
		    	int SavAcct=savingAcctNum;
	//	    	out.print("Saving Account no:"+SavAcct+"<br/>");
		    	out.print("Saving Account no: <input type ='text' name ='savnum' value = "+SavAcct+" readonly><br/>");
	       //  	out.print("Sav Account no: <input type =\"text\" name =\"savnum\" value = "+SavAcct+" readonly><br/>");
		    	out.print("Saving Account Balance: $"+savingBal+"<br/>");
		    	
		    }
		    if(checkingAcctNum>0)
		    {
		    	float checkingBal=bdb.getCheckingBal();
		    	int checkAcct=checkingAcctNum;
		  //  	out.print("Checking Account no:"+checkAcct+"<br/>");
		    	out.print("Checking Account no: <input type ='text' name ='chknum' value = "+checkAcct+" readonly><br/>");
		    	out.print("Checking Account Balance: "+checkingBal+"<br/>");
		    	
		    }
		   out.print("<br/><input type='submit' value='Transaction' name='arith'/><br/><br/>");
	    	out.print("<input type='submit' value='LogOff' name='arith'/><br/>");
	    	out.print("</form>");
	    }
	    else
	    {
	    	RequestDispatcher disp1 = request.getRequestDispatcher("/LoginFrame.html");
			disp1.forward(request, response);
	    }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
