package org.app.service.entities;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;


@Remote
public interface PlanAuditSprintDataService extends EntityRepository<PlanAudit> {
	


	
	public String getMessage();
	public PlanAudit createnewPlanAudit(Integer id);

}
