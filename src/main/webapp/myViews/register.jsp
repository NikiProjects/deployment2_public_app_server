<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
  <link href="<c:url value="/resources/css/style1.css" />" rel="stylesheet">  
 <!--     <link href="<c:url value="/resources/theme1/css/style1.css" />" rel="stylesheet"> -->
 <!-- <link href="style1.css" rel="stylesheet" type="text/css">   -->
 

<!--  
<style>
td {
 background-color: lightblue;
}
</style>
-->
<script>


function form_validation(){
	var retrEmail = document.registrationInfo.email.value;
	//var retrAge = document.registrationInfo.age.value;
	var retrAge = document.getElementById("age").value;
	//console.log("retrAge: " + retrAge);
	var retrUserName = document.registrationInfo.username.value;
	var retrPassLength = document.registrationInfo.password.value.length;
	var min=5;
	var max=10;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var submitForm = true;
	
// begin validation for email	
	if (retrEmail == null || retrEmail == "")
	{
		alert("Email can't be blank");
		submitFrom = false;
		return false;
	}
	else
	{
		if(retrEmail.match(mailformat))
		{ 
			//alert("You have entered an invalid email address!");
			//submitFrom = false;
			//return false; 		
		}
		else{
			alert("You have entered an invalid email address!");
			submitFrom = false;
			return false; 
		}
		
	}
// end validation for email

// begin validation for age
if (retrAge == 0)
{
	//alert("Age can't be blank");
	document.getElementById("agespan").innerHTML = "<span style='color: red;'>Invalid Age</span>"	
	submitFrom = false;
	return false;
}
// validation for age

// begin validation for username
if(retrUserName == null || retrUserName == ""){
	
	alert("Please provide input for mandatory field username");
	submitFrom = false;
	return false;
	
}
// end validation for username
if (retrPassLength == 0 || retrPassLength > max || retrPassLength < min)
{
		alert("Password can't be blank. Password has to between 5 - 10 characters");
		submitFrom = false;
		return false;
}
else{
	alert("Password meets requirements");
	
}

if(submitFrom == true)
{
	return true;
}

} // end of form_validation function
</script>
<style>  
.error{color:red}  
</style>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form:form name="registrationInfo" onsubmit="return form_validation()" action="processRegistrationForm" modelAttribute="BeanAttr">
		<table>
			<tr>
			<h2>To register a new user, please provide the following information:</h2>
			</tr>
			<tr>
				<td class="tdTheme1">Name:</td>
				<td>
					<form:input class="tdTheme2" path="name"></form:input>
					<form:errors path="name" cssClass="error"/>					
				</td>
			</tr>
		
			<tr>
				<td class="tdTheme1">Email:</td>
				<td>
					<form:input class="tdTheme2" path="email"></form:input>
				</td>
			</tr>
		
			<tr>
				<td class="tdTheme1">Age:</td>
				<td>
				<!--  	<form:input id="ageId" path="age"></form:input>  -->
				  	<form:input class="tdTheme2" path="age"></form:input><span id="agespan"></span> 
				</td>
			</tr>
			
			<tr>
				<td class="tdTheme1">Gender:</td>
				<td>
				<form:select class="tdTheme2" path="gender">
					<form:option value="Male"> </form:option>
					<form:option value="Female"> </form:option>
				</form:select>
				</td>
			
			</tr>
			
			<tr>
				<td class="tdTheme1">Username:</td>
				<td>
					<form:input class="tdTheme2" path="username"></form:input>
				</td>
				<td>
					${userNameValidation}
				</td>
			</tr>
			
			<tr>
				<td class="tdTheme1">Password:</td>
				<td>
					<form:input class="tdTheme2" path="password"></form:input>
				</td>
			</tr>
			
			
			<tr>
				<td>
					<input id="idRegisterBtn" type="submit" value="Register User"></input>
				</td>
			</tr>
			
		</table>
	</form:form>

</body>
</html>