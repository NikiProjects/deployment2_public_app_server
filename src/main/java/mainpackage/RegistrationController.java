package mainpackage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import mainpackage.beans.User;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Controller

public class RegistrationController {


@Autowired
Dbinterfacer dao;
	
	
	

// When we run the application, the 1st page that is invoked that is index.jsp. 
// index.jsp contains syntax which forwards the request to the RegistrationController.  	
// The tag <jsp:forward> with the page attribute is used to transfer the request to a new page. 
// The request forwarded by the index.jsp should be captured by the Controller class GET method....
//... this GET method should be responsible for displaying the registration.jsp page. 
// in the RegistrationController, declare a method called showRegistrationForm. 
// As a parameter to showRegistrationForm function, we pass org.springframework.ui.ModelMap. The ModelMap is a 
// type of Map. The ModelMap library can be used to hold an attribute. The attribute specified in ModelMap 
// can be displayed on jsp page. When using ModelMap, the return value of the function should reference JSP page.
// The function which uses ModelMap parameter needs to return a String. 	
// This Controller's class's GET method will display the register.page. 
// The register.jsp page contains a form, and when we submit the form, the request is forwarded to Controller
// method which accepts POST requests. 



@RequestMapping(value="/loginPage", method = RequestMethod.GET)
public ModelAndView logUserIn(HttpServletRequest request, HttpServletResponse response) {

ModelAndView m = new ModelAndView("login");



    return m;
}

// next mapping
@RequestMapping(value="/showHomePage", method = RequestMethod.GET)
public ModelAndView showHomePage(HttpServletRequest request, HttpServletResponse response) {

ModelAndView m = new ModelAndView("home");

m.addObject("BeanAttr", new User());

    return m;
}


// next mapping
@RequestMapping(value="/logoutToHomePage", method = RequestMethod.GET)
public ModelAndView logoutToHomePage(HttpServletRequest request, HttpServletResponse response) {

ModelAndView m = new ModelAndView("home");

m.addObject("BeanAttr", new User());

    return m;
}


// next mapping
@RequestMapping(value="/", method = RequestMethod.GET)
public ModelAndView processEmptyUrlPattern(HttpServletRequest request, HttpServletResponse response) {

ModelAndView m = new ModelAndView("home");

m.addObject("BeanAttr", new User());

    return m;
}


// next mapping
@RequestMapping(value="/loginSuccessPage", method = RequestMethod.GET)
public ModelAndView showLoginSuccess() {


System.out.println("Printing Principle" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
String retrievedUserName =(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
List<User> list = dao.getRecByUsername(retrievedUserName);


ModelAndView m = new ModelAndView("loginsuccess");
if(list.size() != 0)
{	
	User user = list.get(0);
	m.addObject("BeanAttr2", user);
}
else if(list.size() == 0){

	User user = new User();	
	user.setName("admin");
	user.setEmail("admin@gmail.com");
	user.setUsername("admin");
	user.setId(0);
	user.setGender("admin");
	m.addObject("BeanAttr2", user);;
}

    return m;
}


//next mapping
@RequestMapping(value="/processRegistrationForm")	
public String processRegistrationForm(@Valid @ModelAttribute(value="BeanAttr") User userParam, BindingResult bindingResult, ModelMap modelMap){
	List<User> listExistingUsernames = new ArrayList<User>();
	
	String username = userParam.getUsername();
	String password = userParam.getPassword();
	int userid = userParam.getId();
	int age = userParam.getAge();
	String email = userParam.getEmail();
	String gender = userParam.getGender();
	int id = userParam.getId();
	String name = userParam.getName();
	
	User user = new User();
	user.setUsername(username);
	user.setPassword(password);
	user.setId(userid);
	user.setAge(age);
	user.setEmail(email);
	user.setGender(gender);
	user.setName(name);
	
	
	
	
	System.out.println("a.username " + username);
	System.out.println("b.password " + password);
	System.out.println("c.id" + userid);
	System.out.println("d. age" + age);
	
	
	
	if(dao == null)
		System.out.println("dao is null");
	else{
		System.out.println("dao is not null");
	}
	/*
	modelMap.addAttribute("userNameValidation", "");
	dao.insertOneRecord(userParam);
	System.out.println("Success, the value of the bean is: " + userParam);
	return "registersuccess";
	*/
	
	List<User> credentialList = dao.getRecByUsernameAndPassword(username, password);
	
	System.out.println("After populating credentialList");
	
	if(credentialList == null){
		System.out.println("No records which match username and password were found");
	}
	else if(credentialList != null){
		System.out.println("Records were found that match username and password");
		listExistingUsernames = credentialList;
	}
	
	

	System.out.println("Finished retrieving from db");

	
	if(listExistingUsernames.size() != 0)
	{
		modelMap.addAttribute("userNameValidation", "This username is taken. Please register with different useranme");
		return "register";
	}
	else{  //if it is equal to 0
		modelMap.addAttribute("userNameValidation", "");
	
			if(bindingResult.hasErrors())
			{
				return "register";
			}
			else
			{
				//dao.insertOneRecord(userParam);
				insertOneRecord(user);
				
				System.out.println("Success, the value of the bean is: " + userParam);
				return "registersuccess";
		// forward to success.jsp
			}	
	}
	
}

public void insertOneRecord(User user){
	dao.insertOneRecord(user);
}


//next mapping
@RequestMapping(value="/logoutMessage", method = RequestMethod.GET)
public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null){    
        new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/showRegistrationForm";
}

// next mapping
@RequestMapping("/viewAllUsers")  
public String viewAllUsers(Model m){  
    List<User> userList= dao.getUsers();  
    m.addAttribute("userList",userList);
    return "viewAllUsers";  
}  


//next mapping
@RequestMapping(value="/deleteUser/{id}",method = RequestMethod.GET)  
public String delete(@PathVariable int id){  
    dao.delete(id);  
    return "redirect:/viewAllUsers";  
} 


//next mapping
@RequestMapping(value="/editUser/{id}")  
public ModelAndView edit(@PathVariable int id){  
    User user = dao.getUserById(id);  
    System.out.println("id from /editUser/{id} is: " + user.getId());
    System.out.println("name from /editUser/{id} is: " + user.getName());
    
    ModelAndView modelAndView = new ModelAndView("usereditform");
    modelAndView.addObject("userBeanInstance", user);
    
    return modelAndView;  
}  



//@RequestMapping(method = RequestMethod.GET)
// next mapping
@RequestMapping("/welcomeFileRegisterForm")	
public String welcomeFileForRegisterForm(Model map){
return "index";
}


// next mapping
@RequestMapping(value="/registerForm", method = RequestMethod.GET)
public ModelAndView displayRegistrationFrom(HttpServletRequest request, HttpServletResponse response) {

ModelAndView m = new ModelAndView("register");

m.addObject("BeanAttr", new User());

    return m;
}


// next mapping
@RequestMapping("editUser/editFormsave")
public String editsave(@Valid @ModelAttribute(value="userBeanInstance") User userParam, BindingResult bindingResult) {
	if(bindingResult.hasErrors()){
		System.out.println("Inside editFormsave if condition");
		 return "redirect:/viewAllUsers"; 
	}
	else{
		System.out.println("Inside editFormSave else condition");
		dao.update(userParam); 
		System.out.println("Success, the value of the bean is: " + userParam);
		return "redirect:/viewAllUsers"; 
		
	}
}


}

