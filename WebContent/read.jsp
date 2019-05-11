<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CRUD OPEARTIONS</title>
</head>
<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp", "root", "shivam");
	String sql = "select * from student";
	Statement smtp = conn.createStatement();
	ResultSet rs = smtp.executeQuery(sql);
%>
<body>
	<h1>CRUD:READ OPERATION</h1>
	<form action="">
		<pre>
			<%
				while (rs.next()) {
			%>
			ID: <%=rs.getString(1)%>
			NAME: <%=rs.getString(2)%>
			EMAIL: <%=rs.getString(3)%>
			CONTACT: <%=rs.getString(4)%>
			CITY: <%=rs.getString(5)%>
			<%
				}
			%>
		</pre>
	</form>
</body>
</html>
