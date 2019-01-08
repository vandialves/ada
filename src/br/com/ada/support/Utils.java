package br.com.ada.support;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
	
	public static String getCurrentUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = "default";
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
		}
		return currentUserName;
	}
}
