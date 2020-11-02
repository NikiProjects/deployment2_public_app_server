SpringFinalProject_HibernateDBConnectivity and SpringWithCSS
Interface with db using Hibernate framework; Project runs on - Server: Tomcat Server 9; IDE: Eclipse NEON.3
MySQL57 database used 


This project builds upon SpringFinalProject. Here, we simply sytle the registration page and the login page. 

This is a Maven-WebApp project, which declares all of it's dependencies in the pom.xml file. 
To create this Maven project, we ran the following steps: 
File ---> New ---> Maven Project ----> Do not select checkbox 'Create a simple project'
Specify group Id: 
org.apache.maven.archetypes

Specify the artifact Id: 
maven-archetype-webapp

In this project, styling was applied using CSS. We created 5 style sheets in the webapp directory. In this project, we have also applied/demoed some advanced CSS including:
- shadow effects
- color gradient
- custom variables
- flex box
- tool tip
- background image 

This application uses one database called spring_database. This database contains only one table called registeredUsers1. 
This source code in this project that correspond to database are: 
User.java and Dbinterfacer.java 

Security features of this project: 
When user attempts to login, an authentication token is assigned based on the login credentials. The authentication token determines if user has access to 'View All Registered Users' page. 
The admin username is: admin
The admin password is: adminpass
If logged in as admin, the user has the capability of editing or deleting a registered user. 

Other important features of this application include:
- A new user 'Registration Page'. 
- A 'User Login' Page. 
- A Login Success page that displays all user details. 
- Logout functionality that handles Cookies behind the scenes. 


Location of project on C drive: C:\Users\Nikita\Spring_MVC_Wkspace\springappfinalWithCSS
