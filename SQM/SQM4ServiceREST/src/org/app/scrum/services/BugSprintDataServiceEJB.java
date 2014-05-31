package org.app.scrum.services;

import java.io.Serializable;
import java.io.BufferedReader;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.scrum.factory.BugFactory;
import org.app.service.entities.Atasament;
import org.app.service.entities.Bug;
import org.app.service.entities.PrioritateEnum;
import org.app.service.entities.StatusEnum;
import org.app.service.entities.TipBugEnum;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("service")
//1. Remote interface
@Stateless
@LocalBean
public class BugSprintDataServiceEJB 
	extends EntityRepositoryBase<Bug>
	implements BugSprintDataService, Serializable{

	private static Logger logger = Logger.getLogger(BugSprintDataServiceEJB.class.getName());
	
	// 2. Inject resource 
		@PersistenceContext(unitName="SQM4ServiceREST")
		private EntityManager scrumEM;

	    // 3. Init with injected EntityManager
	private EntityRepository<Atasament> atasamentRepository;
	
	@Inject
	private BugFactory bugFactory;
	
	 @PostConstruct
		public void init(){
	    	atasamentRepository = new EntityRepositoryBase<Atasament>(this.em, Atasament.class);
	    	//check inject references    	
			logger.info("Initialized atasamentRepository :"+ atasamentRepository.size());		
		}	
	 
	 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) //autonomous transsaction
		public Bug createNewBug(String idBug){
			Bug bug = bugFactory.buildBug("idBug", "titlu", TipBugEnum.IMPLEMENTARE, StatusEnum.OPENED, PrioritateEnum.P3, new Date(), new Date(), "descriere", 0);
			this.add(bug);
			
			return Bug.toDTOAggregate(bug);
		}
		
		public String getMessage() {
			return "BugSPrintDataService is working...";
		}

		@Override 
		public Bug getById(Object id){
			Bug bug = super.getById(id);
			return Bug.toDTOAggregate(bug);			
		}
			
		
		@Override 
		public Collection<Bug> toCollection(){
			return Bug.toDTOs(super.toCollection());
			
		}
		
		@Override
		public Bug add(Bug bug){
			return Bug.toDTOAggregate(bug);
		}
		
		@Override 
		public boolean remove(Bug bug){
			return super.remove(bug);
		}
		
	/********************************************************************/
	@GET
	@Produces("text/html")
	public String sayRest() {
		return "Rest-EJB-SQM4... ";
	}	
	/********************************************************************/

}
