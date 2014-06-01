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

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;

import java.io.BufferedReader;


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



	@Override
	public PlanAudit getById(Object id) {
		PlanAudit planaudit=super.getById(id);
		
		return PlanAudit.toDTOAggregate(planaudit);
	}



	@Override
	public PlanAudit add(PlanAudit planaudit) {
		planaudit=super.add(planaudit);
		return PlanAudit.toDTOAggregate(planaudit);
	}



	@Override
	public Collection<PlanAudit> toCollection() {
		return PlanAudit.toDTOs(super.toCollection());
	}



	@Override
	public boolean remove(PlanAudit planaudit) {
		// TODO Auto-generated method stub
		return super.remove(planaudit);
	}
	
	
}