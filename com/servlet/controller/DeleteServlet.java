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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	Connection conn;
	PrintWriter out;
	@Override
	public void init(ServletConfig config) throws ServletException {
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp", "root", "shivam");
			// System.out.println(con);
		} catch (Exception e) {
			out.println("Something Went Wrong in Creating Connection!");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		try {
			PreparedStatement ps = conn.prepareStatement("delete from student where id = ?");
			ps.setInt(1, id);
			int rowDeleted = ps.executeUpdate();
			System.out.println(rowDeleted);
			if(rowDeleted != 0) {
				System.out.println("hello");
			}
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.forward(request, response);
		} catch (Exception e) {
			out.println("Something Went Wrong in DataBase!");
		}
	}
	@Override
	public void destroy() {
		try {
			conn.close();
		} catch (Exception e) {
			out.println("Something Went Wrong in Closing Connection!");
		}
	}
}
