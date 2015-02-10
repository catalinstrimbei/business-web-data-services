package org.app.scrum.services;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Bug;

public interface BugSprintDataService extends EntityRepository<Bug>{
	//String sayRest();
	
	//inherited: CRUD methods, adauga(Bug b), Sterge(Bug b) etc. 
	public String getMessage();
	public Bug createNewBug(String idBug);
}
