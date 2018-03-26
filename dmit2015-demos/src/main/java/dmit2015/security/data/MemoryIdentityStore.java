package dmit2015.security.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class MemoryIdentityStore implements IdentityStore{

	Set<String> adminGroupSet;
	Set<String> userGroupSet;
	Set<String> userAdminGroupSet;
	
	@PostConstruct
	public void init() {
		adminGroupSet = new HashSet<>(Arrays.asList("admin"));
		userGroupSet = new HashSet<>(Arrays.asList("user"));
		userAdminGroupSet = new HashSet<>(Arrays.asList("admin","user"));
	}
	
	@Override
	public CredentialValidationResult validate(Credential credential) {
		UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
		CredentialValidationResult credentialValidationResult = CredentialValidationResult.INVALID_RESULT;
		
		if (usernamePasswordCredential.compareTo("admin1", "Password2015")) {
			credentialValidationResult = new CredentialValidationResult("admin1", adminGroupSet);
		}
		else if (usernamePasswordCredential.compareTo("user1", "Password2015")) {
			 credentialValidationResult = new CredentialValidationResult("user1", userGroupSet);
		}
		else if (usernamePasswordCredential.compareTo("userAdmin1", "Password2015")) {
			credentialValidationResult = new CredentialValidationResult("userAdmin1", userAdminGroupSet);
		}
				
		return credentialValidationResult;
	}
}
