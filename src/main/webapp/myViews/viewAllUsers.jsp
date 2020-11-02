<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link href="<c:url value="/resources/css/style3.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>All Users List</h1>
	<table border ="2" width="70%" cellpadding="2">
		<tr id="tblHeader"><th>Id</th><th>Name</th><th>Email</th><th>Age</th><th>Username</th><th>Password</th></tr>
	<c:forEach var="singleUser" items="${userList}"> 
    <tr>
    <td>${singleUser.id}</td>
    <td>${singleUser.name}</td>
    <td>${singleUser.email}</td>
    <td>${singleUser.age}</td>
    <td>${singleUser.username}</td>
    <td>${singleUser.password}</td>
    <td><a href="<c:url value="/editUser/${singleUser.id}"/>">Edit</a></td>
    
    <td><a href="<c:url value="/deleteUser/${singleUser.id}" />">Delete</a></td>
   
    
    </tr>
    </c:forEach>
	</table>

  <a href="<c:url value="/logoutMessage" />">Logout</a>


</body>
</html>