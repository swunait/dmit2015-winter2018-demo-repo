package dmit2015.security.config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.LdapIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@CustomFormAuthenticationMechanismDefinition(
	loginToContinue = @LoginToContinue(
		loginPage="/security/customLogin.xhtml", 
		errorPage="/security/customLogin.xhtml?error")
)

//@LdapIdentityStoreDefinition(
//	url = "ldap://192.168.202.227:389/",
//	callerSearchBase = "ou=Departments,dc=dmit2015,dc=ca",
//	callerNameAttribute = "SamAccountName",	
//	groupSearchBase = "ou=Departments,dc=dmit2015,dc=ca",
//	groupMemberAttribute="member",
//	bindDn = "CN=DMIT2015 Student,ou=IT Support,ou=Departments,dc=dmit2015,dc=ca",
//	bindDnPassword = "Password2015",
//	priority = 5
//)

@DatabaseIdentityStoreDefinition(
	dataSourceLookup="java:jboss/northwindDS",
	callerQuery="SELECT password FROM LoginUser WHERE username = ?",
	groupsQuery="SELECT g.groupname FROM LoginUser u, LoginUserGroup ug, LoginGroup g WHERE u.username = ? AND u.id = ug.userid AND ug.groupid = g.id",
	hashAlgorithm = Pbkdf2PasswordHash.class,
	hashAlgorithmParameters = { 
		"Pbkdf2PasswordHash.Iterations=3072", 
		"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", 
		"Pbkdf2PasswordHash.SaltSizeBytes=64" },
	priority = 10
)
@ApplicationScoped
public class ApplicationConfig {

}
