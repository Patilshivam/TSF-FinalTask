<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CRUD OPEARTIONS</title>
<style> 
input[type=text] {
  width: 50%;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border: none;
  background-color: #3CBC8D;
  color: white;
}
input[type=button], input[type=submit], input[type=reset] {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 16px 32px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
}
</style>
</head>
<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp", "root", "shivam");
	String sql = "select id from student";
	Statement smtp = conn.createStatement();
	ResultSet rs = smtp.executeQuery(sql);
%>
<body>
	<h1>CRUD:UPDATE OPERATION</h1>
	<form action="./UpdateServlet" method="post">
		<pre>
			ID:		<select name="id">
			<%
				while (rs.next()) {
			%>
			<option value="<%=rs.getString(1)%>">
			<%=rs.getString(1)%></option>
			<%
				}
			%>
			</select>
			NAME:		<input type="text" name="name" />
			EMAIL:		<input type="text" name="email" />
			CONTACT:	<input type="text" name="contact" />
			CITY:		<input type="text" name="city" />
			COUNTRY:	<input type="text" name="country" />
			
			<input type="submit" value="Update">
		</pre>
	</form>
</body>
</html>
