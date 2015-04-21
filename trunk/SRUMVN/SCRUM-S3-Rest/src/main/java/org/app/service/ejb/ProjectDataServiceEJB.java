package org.app.service.ejb;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Project;
import org.app.service.entities.Release;

@Stateless @LocalBean
public class ProjectDataServiceEJB extends EntityRepositoryBase<Project>
		implements ProjectDataService, Serializable {
	private static Logger logger = Logger.getLogger(ProjectDataServiceEJB.class.getName());

	private EntityRepository<Release> releaseRepository;
	
	@EJB
	private FeatureDataService featureDataService; 

	@Inject 
	private ProjectFactory projectFactory;
	
	@PostConstruct
	public void init() {
		releaseRepository = new EntityRepositoryBase<Release>(this.em,
				Release.class);
		logger.info("POSTCONSTRUCT-INIT releaseRepository: " + this.releaseRepository);
		logger.info("POSTCONSTRUCT-INIT featureDataService: " + this.featureDataService);
		logger.info("POSTCONSTRUCT-INIT projectFactory: " + this.projectFactory);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Project createNewProject(Integer id){
		Project project = projectFactory.buildProiect(id, "NEW Project", 3);
		this.add(project);
		return project;
	}
		
	
	public Release getReleaseById(Integer releaseid) {
		return releaseRepository.getById(releaseid);
	}

	public String getMessage() {
		return "ProjectSprint DataService is working...";
	}

}