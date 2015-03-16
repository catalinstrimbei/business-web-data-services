package org.app.scrum.services;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.app.patterns.DataRepositoryBean;
import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.scrum.project.Project;
import org.app.scrum.project.ProjectFactory;
import org.app.scrum.project.ProjectView;
import org.app.scrum.project.Release;
import org.app.scrum.rest.CredentialBean;
import org.app.scrum.sprint.Sprint;

@Stateless @LocalBean 
public class ProjectSprintDataServiceEJB extends EntityRepositoryBase<Project> 
		implements ProjectSprintDataService, Serializable{
	private static Logger logger = Logger.getLogger(ProjectSprintDataServiceEJB.class.getName());
	
	private EntityRepository<Release> releaseRepository;
	@Inject private ProjectFactory projectFactory;
    
	@PostConstruct public void init(){
    	releaseRepository = new EntityRepositoryBase<Release>(this.em, Release.class);
		logger.info("Initialized releaseRepository : " + releaseRepository.size());
	}		
    	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Project createNewProject(Integer id){
		Project project = projectFactory.buildProiect(id, "NEW Project", 3);
		this.add(project);
		return Project.toDTOAggregate(project);
	}
	
	public String getMessage(){
		return "ProjectSprintDataService is working...";
	}	
	
	@Override
	public Project getById(Object id){
		Project project = super.getById(id);
		return Project.toDTOAggregate(project);
	}
	
	@Override
	public Collection<Project> toCollection() {
		return Project.toDTOs(super.toCollection());
	}
	
	@Override
	public Project add(Project project) {
		project = super.add(project);
		return Project.toDTOAggregate(project);
	}
	
	@Override
	public boolean remove(Project project) {
		return super.remove(project);
	}	
	
	public Release getReleaseById(
//			Integer projectid, 
			Integer releaseid){
		Release release = releaseRepository.getById(releaseid);
		return release.toDTO();
	}

	@Override
	public String check() {
		// TODO Auto-generated method stub
		return null;
	}
}