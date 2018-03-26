package dmit2015.security.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@RequestScoped
public class LoginController {

	@Inject
	private SecurityContext securityContext;
	
	private String username;
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public void login() {
		
		Credential credential = new UsernamePasswordCredential(username, new Password(password) );
		
		HttpServletRequest request = Faces.getRequest();
		HttpServletResponse response = Faces.getResponse();
				
		AuthenticationStatus status = securityContext.authenticate(request, response, AuthenticationParameters.withParams().credential(credential));
		
		if (status.equals(AuthenticationStatus.SEND_CONTINUE)) {
			Faces.responseComplete();
		} else if (status.equals(AuthenticationStatus.SEND_FAILURE)) {
			Messages.addGlobalError("Authentication failed");
		}
				
	}
}
