<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form:form action="editFormsave" modelAttribute="userBeanInstance"> 
		<table>
			<tr>
				<td>Id:</td>
				<td>
					<form:input type="number" path="id"></form:input>
				</td>
			</tr>			
			<tr>
				<td>Name:</td>
				<td>
					<form:input path="name"></form:input>
					<form:errors path="name" cssClass="error"/>					
				</td>
			</tr>
		
			<tr>
				<td>Email:</td>
				<td>
					<form:input path="email"></form:input>
				</td>
			</tr>
		
			<tr>
				<td>Age:</td>
				<td>
					<form:input type="number" path="age"></form:input>
				</td>
			</tr>
			
			<tr>
				<td>Gender:</td>
				<td>
				<form:select path="gender">
					<form:option value="Male"> </form:option>
					<form:option value="Female"> </form:option>
				</form:select>
				</td>
			
			</tr>
			
			<tr>
				<td>Username:</td>
				<td>
					<form:input path="username"></form:input>
				</td>
			</tr>
			
			<tr>
				<td>Password:</td>
				<td>
					<form:input path="password"></form:input>
				</td>
			</tr>
			
			
			<tr>
				<td>
					<input type="submit" value="Update User Info"></input>
				</td>
			</tr>
			
		</table>
	</form:form>

<br></br>
  <a href="<c:url value="/logoutMessage" />">Logout</a>


</body>
</html>