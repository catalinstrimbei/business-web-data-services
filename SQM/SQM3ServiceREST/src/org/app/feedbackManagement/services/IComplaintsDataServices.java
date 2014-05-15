package org.app.feedbackManagement.services;

import javax.ejb.*;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Complaints;

//facade interface
@Remote
public interface IComplaintsDataServices extends EntityRepository<Complaints>{
//CRUD methods
	
	public String getMessage();
	public Complaints createNewComplaints(Integer Id);
	
}
