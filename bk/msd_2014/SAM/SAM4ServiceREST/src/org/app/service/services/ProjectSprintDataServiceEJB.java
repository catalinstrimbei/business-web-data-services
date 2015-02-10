package org.app.service.services;

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
import org.app.service.project.Activitate;
import org.app.service.project.Proiect;
import org.app.service.project.ProjectFactory;
import org.app.service.project.ProjectView;
import org.app.service.rest.CredentialBean;
import org.app.service.sprint.Sprint;

@Stateless @LocalBean 
public class ProjectSprintDataServiceEJB extends EntityRepositoryBase<Proiect> 
		implements ProjectSprintDataService, Serializable{
	private static Logger logger = Logger.getLogger(ProjectSprintDataServiceEJB.class.getName());
	
	private EntityRepository<Activitate> activitateRepository;
	@Inject private ProjectFactory projectFactory;
    
	@PostConstruct public void init(){
		activitateRepository = new EntityRepositoryBase<Activitate>(this.em, Activitate.class);
		logger.info("Initialized activitateRepository : " + activitateRepository.size());
	}		
    	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Proiect createNewProject(Integer IdProiect){
		Proiect proiect = projectFactory.buildProiect(IdProiect, "NEW Project", 3);
		this.add(proiect);
		return Proiect.toDTOAggregate(proiect);
	}
	
	public String getMessage(){
		return "ProjectSprintDataService is working...";
	}	
	
	@Override
	public Proiect getById(Object IdProiect){
		Proiect proiect = super.getById(IdProiect);
		return Proiect.toDTOAggregate(proiect);
	}
	
	@Override
	public Collection<Proiect> toCollection() {
		return Proiect.toDTOs(super.toCollection());
	}
	
	@Override
	public Proiect add(Proiect proiect) {
		proiect = super.add(proiect);
		return Proiect.toDTOAggregate(proiect);
	}
	
	@Override
	public boolean remove(Proiect proiect) {
		return super.remove(proiect);
	}	
	
	public Activitate getActivitateById(
//			Integer IdProiect, 
			Integer IdActivitate){
		Activitate activitate = activitateRepository.getById(IdActivitate);
		return activitate.toDTO();
	}
}