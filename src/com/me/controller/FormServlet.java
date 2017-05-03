package com.me.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.entity.BankDataBean;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("arith");
		if(name.equals("LogOff"))	
		{
		System.out.println("LogOff");
		
		RequestDispatcher disp1 = request.getRequestDispatcher("/LoginServlet");
		disp1.forward(request, response);
		}	
		else if(name.equals("Transaction"))
		{
		System.out.println("Transaction");
		System.out.println("sai" + request.getParameter("savnum") +  request.getParameter("chknum"));
	//	System.out.println();
		RequestDispatcher disp2 = request.getRequestDispatcher("/TransactionServlet");
		disp2.forward(request, response);
		}
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
