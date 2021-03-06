package br.com.ada.support;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.ada.model.User;
import br.com.ada.model.repository.IUserDAO;

@Named
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Inject
	private IUserDAO userDAO;

	public CustomAuthenticationProvider() {
		super();
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String login = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = this.userDAO.findByLoginAndPassword(login, password);

		if (user != null) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

			grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

			UserDetails userDetails = new org.springframework.security.core.userdetails.User(
					login, password, grantedAuthorities);
			return new UsernamePasswordAuthenticationToken(userDetails,
					password, grantedAuthorities);
		} else {
			return null;
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
