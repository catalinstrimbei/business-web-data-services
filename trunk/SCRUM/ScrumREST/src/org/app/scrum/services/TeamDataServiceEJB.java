package org.app.scrum.services;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.app.patterns.DataRepositoryBean;
import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.scrum.team.Member;
import org.app.scrum.team.Team;
import org.app.scrum.team.TeamFactory;

@Path("teams") /* http://localhost:8080/ScrumREST/teams */
@Stateless @LocalBean
public class TeamDataServiceEJB 
//	extends EntityRepositoryBase<Team, Integer> 
	extends EntityRepositoryBase<Team>
	implements TeamDataService, Serializable{
	private static Logger logger = Logger.getLogger(TeamDataServiceEJB.class.getName());
	
//	@Inject @DataRepositoryBean(entityType=Member.class)
//	private EntityRepository<Member, Integer> memberRepository;
	private EntityRepository<Member> memberRepository;
	
	@Inject
	private TeamFactory teamFactory;
	
	/* dummy validation rest */
	@GET @Path("/test")
	@Produces("text/html")
	public String getMessage(){
		return "TeamDataService is working...";
	}	
}
