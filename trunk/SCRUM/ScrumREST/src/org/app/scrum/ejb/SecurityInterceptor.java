package org.app.scrum.ejb;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.app.scrum.rest.CredentialBean;
import org.app.scrum.team.Member;

public class SecurityInterceptor implements Serializable{
	private static Logger logger = Logger.getLogger(SecurityInterceptor.class.getName());
	
	@EJB
	private SecurityEnablerEJB securityEnablerEJB;
	
	@Inject
    private CredentialBean creds;

	@AroundInvoke
	private Object securityCheck(InvocationContext ctx) throws Exception{
		logger.info("DEBUG: SecurityInterceptor.creds =  " + creds + "\n");
		
		Member user = new Member(null, creds.getUsername(), creds.getUsername(), creds.getCreds());

		logger.info("DEBUG: user to check  " + user);
		int idx = Collections.binarySearch(securityEnablerEJB.getMembers(), user, userComparator);
		logger.info("DEBUG: user check result  " + idx);
		
//		if(idx < 0 )
//			throw new Exception("User authorizaion failed!");
			
		return ctx.proceed();
	}
	
	private UserComparator userComparator = new UserComparator(); 
	public class UserComparator implements Comparator<Member>{

		@Override
		public int compare(Member m1, Member m2) {
			if(m1==null || m2==null)
				throw new RuntimeException("NULLs not comparable!");
			
			if (m1.getUserName() == null || m2.getUserName() == null)
				throw new RuntimeException("NULLs not comparable!");
			
			if (m1.getUserName().equals(m2.getUserName()) && m1.getPassword().equals(m2.getPassword()))
				return 0;
			
			return 1;
		}
		
	}	
}

/*
	@Resource
    protected SessionContext sessionCtx;	
*/