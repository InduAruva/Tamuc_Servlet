package com.me.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.me.connector.LoginDBConnector;
import com.me.entity.BankDataBean;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<h1>Transaction Details</h1>");
		out.print("<form action='FormServlet'>");
		int savingAcctNo=0;
		int checkingAcctNo=0;
		if (request.getParameter("savnum") != null){
		 savingAcctNo = Integer.parseInt(request.getParameter("savnum"));
		}
		if(request.getParameter("chknum") != null){
		  checkingAcctNo =Integer.parseInt(request.getParameter("chknum"));
		}
		LoginDBConnector con = new LoginDBConnector();
		try {
			con.setConnection();
			ArrayList<String> accountDetails = new ArrayList<>();
			accountDetails = con.getTransactionInformation(savingAcctNo, checkingAcctNo);
			out.print("<form>");
			for (String details : accountDetails) {
				
				out.print(details +"<br/><br/>");
			}
			out.print("<input type='submit' value='LogOff' name='arith'/><br/>");
	    	out.print("</form>");
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
