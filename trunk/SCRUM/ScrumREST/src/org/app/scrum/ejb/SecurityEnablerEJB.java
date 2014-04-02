package org.app.scrum.ejb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.app.patterns.EntityRepository;
import org.app.scrum.team.Member;

@Singleton
@LocalBean
public class SecurityEnablerEJB {
	private static Logger logger = Logger.getLogger(SecurityEnablerEJB.class.getName());
	
	@Inject @DataRepositoryBean(entityType=Member.class)
	private EntityRepository<Member> memberEntityRepository;
	
	private List<Member> members = new ArrayList<>();
	private Member testUser = new Member(1, "test-user", "test-user", "test-pass");
	
	@PostConstruct
	private void init(){
		// check references: injected
		logger.info(">>>>>>>>>>>>>> Initialized sprintEntityRepository : " + memberEntityRepository.size());		
		this.members.addAll(memberEntityRepository.toCollection());
		logger.info("DEBUG : cached users " + members.size());	
		
		members.add(testUser);
		logger.info("DEBUG : cached users " + members.size());	
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Member getTestUser() {
		return testUser;
	}

	public void setTestUser(Member testUser) {
		this.testUser = testUser;
	}

	
}
