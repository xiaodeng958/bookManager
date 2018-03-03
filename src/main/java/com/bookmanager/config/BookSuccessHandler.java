package com.bookmanager.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@Configuration
public class BookSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		String url = determineTargetUrl(authentication);
		
		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}
		redirectStrategy.sendRedirect(request, response, url);
	}
	
	
	protected String determineTargetUrl(Authentication authentication) {  
		
		String URL = "";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); 
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority auth : authorities) {
			roles.add(auth.getAuthority());
		}
		
		if (isAdmin(roles)) {  
        	URL = "/admin";  
        } else if (isUser(roles)) {  
        	URL = "/home";  
        } else {  
        	URL = "/accessDenied";  
        }  
		
		return URL;
	}
	
	
	 private boolean isUser(List<String> roles) {  
	        if (roles.contains("ROLE_USER")) {  
	            return true;  
	        }  
	        return false;  
	    }  
	   
	    private boolean isAdmin(List<String> roles) {  
	        if (roles.contains("ROLE_ADMIN")) {  
	            return true;  
	        }  
	        return false;  
	    }  
	    
	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {  
	        this.redirectStrategy = redirectStrategy;  
	    }  
	   
	    protected RedirectStrategy getRedirectStrategy() {  
	        return redirectStrategy;  
	    }  
}
