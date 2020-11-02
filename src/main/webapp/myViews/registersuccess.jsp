<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Values retrieved from BeanAttr</h1>
<br></br>
Name: ${BeanAttr.name}
<br></br>
Email: ${BeanAttr.email}
<br></br>
Age: ${BeanAttr.age}
<br></br>
Gender: ${BeanAttr.gender}


<br></br>
<br></br>

<!--  <a href="/viewAllUsers">View All Registered Users</a>   -->

<br></br>

<!--  <a href="<c:url value="/logoutMessage" />">Logout</a>  -->

<a href="<c:url value="/logoutToHomePage" />">Go Back to Home Page</a>

</body>
</html>