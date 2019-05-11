package com.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn;
	PrintWriter out;
	public void init(ServletConfig config) throws ServletException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp", "root", "shivam");
			// System.out.println(con);
		} catch (Exception e) {
			out.println("Something Went Wrong in Creating Connection!");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		out = response.getWriter();
		boolean b = false;

		if(id <= 0) {
			out.println("ID is Required!");
			b = false;
		} else if (name.equals("")) {
			out.println("NAME is Required!");
			b = false;
		} else if (email.equals("")) {
			out.println("EMAIL is Required!");
			b = false;
		} else if (contact.equals("")) {
			out.println("CONTACT is Required!");
			b = false;
		} else if (city.equals("")) {
			out.println("CITY is Required!");
			b = false;
		} else if (country.equals("")){
			out.println("COUNTRY is Required!");
			b = false;
		} else {
			b = true;
		}

		if(b == true) {
			try {
				PreparedStatement ps = conn.prepareStatement("insert into student values(?, ?, ?, ?, ?, ?)");
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, contact);
				ps.setString(5, city);
				ps.setString(6, country);
				int rowInserted = ps.executeUpdate();
				
				if(rowInserted != 0) {
					out.println("Success:)");
				}
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			} catch (Exception e) {
				out.println("Something Went Wrong in DataBase! or ID must be Unique");
			}
			
		}
	}

	public void destroy() {
		try {
			conn.close();
		} catch (Exception e) {
			out.println("Something Went Wrong in Closing Connection!");
		}
	}
}
