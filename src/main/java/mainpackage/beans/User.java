package mainpackage.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Size;
//import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "User")
@Table(name="registeredUsers1")
public class User implements Serializable {

	
public User(){
		
}	
//@Size(min=5, max=30) 

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="idCol")
private int id;	
	

@Size(min=3,message="required")
@Column(name="nameCol", unique=false, nullable=false, length = 100)
private String name;

@Column(name="emailCol", unique=false, nullable=false, length = 100)
private String email;

@Column(name="ageCol", unique=false, nullable=false, length = 100)
private int age;

@Column(name="usernameCol", unique=true, nullable=false, length = 100)
private String username;

@Column(name="passwordCol", unique=true, nullable=false, length = 100)
private String password;


// for id
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
// for username
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}

// for password
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

//@NotEmpty
private String gender;

// for name
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

// for email
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

// for age
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}

// for gender
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
	
public String toString(){
	return "Name: " + name + "Email: " + email + "Age: " + age + "Gender: " + gender;
}

}
