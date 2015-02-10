package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;


@Singleton
public class PlanAuditFactory {
	public PlanAudit buildPlanAudit(int idplan, String denumireplan, String datastart, String deadline) {
		
	PlanAudit planaudit=new PlanAudit(idplan, deadline, null, null);
	List<Echipa> echipaplan=new ArrayList<>();
	
	planaudit.setEchipa(echipaplan);
	return planaudit;
	


}

	
}