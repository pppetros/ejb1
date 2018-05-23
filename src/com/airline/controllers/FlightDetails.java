package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.FlightService;

/**
 * Servlet implementation class FlightDetails
 */
@WebServlet("/FlightDetails")
public class FlightDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
//	@EJB
	private FlightService fs = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		out.println("The flight details has been called...");
		
		//Arxikopoiisi tou fs os FlightService tou EJB xoris to kolpo me to @EJB
		//opos prin, alla me kleisi apeytheias tis klasis
		try {
			//Lookup in Java Naming and Directory Index (JNDI)
			Context context = new InitialContext(); 
			Object fObj = context.lookup("java:global/ejb1/FlightService!com.airline.service.FlightService");
			fs = (FlightService) fObj;
		}
		catch(NamingException e) {
			System.out.println("Naming exception has occured when trying to lookup the FlightService EJB");
		}
		
		if (fs != null) {
			out.println("Flight details: " + fs.getFrom() + " " + fs.getTo());
		}
		
//		out.println(fs.getAirplaneModel());
//		out.println(fs.getFrom());
//		out.println(fs.getTo());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
