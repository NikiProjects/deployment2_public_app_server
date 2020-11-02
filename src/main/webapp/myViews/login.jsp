<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/style4.css" />" rel="stylesheet"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
      <font color="red">
        Your login attempt was not successful due to <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
      </font>
</c:if>


<form name=""loginForm" method="post" action="<c:url value='/authenticateUser'/>">
Username: <input name="username" type="text"/>
Password: <input name="password" type="password"/>
<br></br>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
<br></br>
<input id="loginSubmitBtn" type="submit" value="Login Page Submit"></input>
</form>




</body>
</html>