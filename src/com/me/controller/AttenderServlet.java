package com.me.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AttenderServlet
 */
@WebServlet("/AttenderServlet")
public class AttenderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttenderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("arith");
		if(name.equals("Login"))	
		{
		System.out.println("Login");
		
		RequestDispatcher disp1 = request.getRequestDispatcher("/LoginServlet");
		disp1.forward(request, response);
		}	
		else if(name.equals("Register"))
		{
		System.out.println("Register");
		RequestDispatcher disp2 = request.getRequestDispatcher("/NewUserFrame.html");
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
