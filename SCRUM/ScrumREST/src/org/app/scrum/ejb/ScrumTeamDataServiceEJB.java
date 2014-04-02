package org.app.scrum.ejb;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.patterns.ProjectBuilder;
import org.app.scrum.project.Project;
import org.app.scrum.project.Release;
import org.app.scrum.team.Team;

@Stateless
@LocalBean
public class ScrumTeamDataServiceEJB 
	extends EntityRepositoryBase<Team>
	implements ScrumTeamDataService{
	
	private static Logger logger = Logger.getLogger(ScrumTeamDataServiceEJB.class.getName());
	
	@EJB
	private ScrumSprintDataServiceEJB sprintEntityRepository;
	// skeleton
	
	@Inject @DataRepositoryBean(entityType=Release.class)
	private EntityRepository releaseEntityRepository;
	
	@Inject @DataRepositoryBean(entityType=Project.class)
	private EntityRepository projectEntityRepository;	
	
    @PostConstruct
	public void init(){
    	// check references: directed and injected
		logger.info(">>>>>>>>>>>>>> Initialized sprintEntityRepository : " + sprintEntityRepository.size());
		logger.info(">>>>>>>>>>>>>> Initialized releaseEntityRepository : " + releaseEntityRepository.size());
		logger.info(">>>>>>>>>>>>>> Initialized projectEntityRepository : " + projectEntityRepository.size());
	}	

}
