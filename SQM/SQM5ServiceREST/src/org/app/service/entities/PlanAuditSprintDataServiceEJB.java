package org.app.service.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;

import java.io.BufferedReader;

@Path("plan") //* http:localhost:8080/ScrumREST/plan */
@Stateless
@LocalBean
public class PlanAuditSprintDataServiceEJB extends EntityRepositoryBase<PlanAudit> implements PlanAuditSprintDataService, Serializable{

	//private static final String PlanAuditSprintDataServiceEJB = null;
	private static Logger logger=Logger.getLogger(PlanAuditSprintDataServiceEJB.class.getName());
	private EntityRepository<Echipa> echipaRepository;
	
	@Inject
	private PlanAuditFactory planauditFactory;
	private EntityRepositoryBase<PlanAudit> sprintRepository;
	@PostConstruct
	public void unit(){
		
		echipaRepository=new EntityRepositoryBase<Echipa>(this.em, Echipa.class);
		sprintRepository.setEm(this.em);
		logger.info("Initializare echipaRepository: " + echipaRepository.size());
	}

	
	
	@TransactionAttribute (TransactionAttributeType.REQUIRES_NEW)
	public PlanAudit createnewPlanAudit (Integer id) {
		PlanAudit planaudit= planauditFactory.buildPlanAudit(1, "new Plan", "3" , "5");
		this.add(planaudit);
		return PlanAudit.toDTOAggregate(planaudit);
	}
	public String getMessage(){
		return "PlanAuditSprintDataService is working...";
	}



	@GET@Path("/{id}")   
	@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public PlanAudit getById(@PathParam ("id") Integer id)  {
		PlanAudit planaudit=super.getById(id);
		
		return PlanAudit.toDTOAggregate(planaudit);
	}


	@PUT@Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public PlanAudit add(PlanAudit planaudit) {
		planaudit=super.add(planaudit);
		return PlanAudit.toDTOAggregate(planaudit);
	}


	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<PlanAudit> addNew(PlanAudit planaudit) {
		super.add(planaudit);
		return PlanAudit.toDTOs(super.toCollection());
	}



	
	@Override
	@DELETE
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean remove(PlanAudit planaudit) {
		// TODO Auto-generated method stub
		return super.remove(planaudit);
	}

	@DELETE@Path("/{id}")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remove(@PathParam("id") Integer id) {
		
		logger.info("DEBUG: called REMOVE - ById() for plan >>>>>>>>>>>>>> simplified ! + id ");
		PlanAudit planaudit = super.getById(id);
		super.remove(planaudit);
	}


	
	
}
