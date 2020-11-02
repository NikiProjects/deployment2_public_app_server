package mainpackage.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import mainpackage.Dbinterfacer;
import mainpackage.beans.User;


@Component
public class TokenGenerator implements AuthenticationProvider {


@Bean
	Dbinterfacer dao(){

	Dbinterfacer dataAccessObject = new Dbinterfacer();
	//dataAccessObject.setJdbctemplate(createJdbcTemplate());
		
		
		return dataAccessObject;
		
	}
	

@Autowired
Dbinterfacer dao;	

	
//public void initializeDataAccessObject(){
//	dao.setJdbctemplate(createJdbcTemplate());
//}


	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// spring automatically populates the Authentication object. 
		
		System.out.println("Inside authentication");

		
		//initializeDataAccessObject();
				
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
			boolean isAuthorizedGeneralUser = authorizedGeneralUser(username, password);
			boolean isAuthorizedAdminUser = authorizedAdminUser(username, password);
		
			if (isAuthorizedGeneralUser)
			{
				List<GrantedAuthority> grantedAuths = new ArrayList<>();
				grantedAuths.add(()-> {return "AUTH_USER1";});
				Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
				System.out.println("Authorities is: " + auth.getAuthorities());
				return auth;
			}
			else if (isAuthorizedAdminUser){
				List<GrantedAuthority> grantedAuths = new ArrayList<>();
				grantedAuths.add(()-> {return "AUTH_USER2";});
				Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
				System.out.println("Authorities is: " + auth.getAuthorities());
				return auth;
			}
			else{
				throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
			}
			
	
	}

	
	private boolean authorizedGeneralUser(String userName, String password)
	{
		List<User> list = dao.getRecByUsernameAndPassword(userName,password);
		if(list.size() > 0){
			
		
			return true;
		}
		return false;
		
	}
	

	
	private boolean authorizedAdminUser(String userName, String password)
	{
		System.out.println("username is :" + userName+" and password is "+password );
		if("admin".equals(userName) && "adminpass".equals(password))
				return true;

		
		return false;
	}
	
	public boolean supports(Class<?> authentication) {
		System.out.println(UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
		//return true;
		
	}

}
