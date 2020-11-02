<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page import="mainpackage.beans.User" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>User Information</h3>
<br></br>
<br></br>
<% User user = (User) request.getAttribute("BeanAttr2"); 
int id = user.getId();
String email = user.getEmail();
String username = user.getUsername();
String name = user.getName();

%>
Id: <%= id %>
<br></br>
<br></br>
Email: <%= email %>
<br></br>
<br></br>




  <a href="<c:url value="/viewAllUsers" />">View All Registered Users</a>

<br></br>
  <a href="<c:url value="/logoutMessage" />">Logout</a>


</body>
</html>