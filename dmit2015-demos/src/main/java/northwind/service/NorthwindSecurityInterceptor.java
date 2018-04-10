package northwind.service;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.security.enterprise.SecurityContext;

@DeclareRoles({"Administrator","Employee","Customer"})
public class NorthwindSecurityInterceptor {

	@Inject
	private SecurityContext sessionContext;
	
	@AroundInvoke
	public Object verifyAccess(InvocationContext context) throws Exception {
		String methodName = context.getMethod().getName();
		
		if (methodName.matches("^delete.*$")) {
			if (sessionContext.isCallerInRole("Administrator")) {
				Object result = context.proceed();
				return result;
			} else {
				throw new EJBAccessException("Access denied. You do not have permission to execute this method.");
			}
			
		} else if (methodName.matches("^update.*$")) {
			if (sessionContext.isCallerInRole("Employee")) {
				Object result = context.proceed();
				return result;
			} else {
				throw new EJBAccessException("Access denied. You do not have permission to execute this method.");
			}
			
		} else if (methodName.matches("^add.*$")) {
			if (sessionContext.isCallerInRole("Administrator") || sessionContext.isCallerInRole("Employee")) {
				Object result = context.proceed();
				return result;
			} else {
				throw new EJBAccessException("Access denied. You do not have permission to execute this method.");
			}
			
		} 
		Object result = context.proceed();
		return result;
	}
}
