package dmit2015.security.config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@CustomFormAuthenticationMechanismDefinition(
	loginToContinue = @LoginToContinue(
		loginPage="/security/customLogin.xhtml", 
		errorPage="/security/customLogin.xhtml?error")
)

@DatabaseIdentityStoreDefinition(
	dataSourceLookup="java:jboss/datasources/oeDS",
	callerQuery="SELECT password FROM LoginUser WHERE username = ?",
	groupsQuery="SELECT g.groupname FROM LoginUser u, LoginUserGroup ug, LoginGroup g WHERE u.username = ? AND u.id = ug.userid AND ug.groupid = g.id",
	hashAlgorithm = Pbkdf2PasswordHash.class,
	hashAlgorithmParameters = { 
		"Pbkdf2PasswordHash.Iterations=3072", 
		"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", 
		"Pbkdf2PasswordHash.SaltSizeBytes=64" } 
)
@ApplicationScoped
public class ApplicationConfig {

}
