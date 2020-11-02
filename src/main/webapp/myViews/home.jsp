<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/style2.css" />" rel="stylesheet"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="homePgBody">

<div class="homePgFlexContainer">

<div>
<p class="genProps">Welcome to the Home Page.</p>
<p class="genProps"> Choose from the following: </p>
</div>

<div>
<a class="genPropsLink" href="<c:url value="loginPage" />">Login
<span class="tooltiptext">Click Login</span>
</a> <br/>  <br/>

<a class="genPropsLink" href="<c:url value="welcomeFileRegisterForm" />">Register
<span class="tooltiptext">Click Register</span>
</a> 
</div>

</div>

</body>
</html>