package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.*;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Complaints;

//facade interface
@Remote
public interface ComplaintsDataService extends EntityRepository<Complaints>{
//CRUD methods
	
	public String getMessage();	
	public String test();
	public Complaints createNewComplaints(Integer Id);
	public Collection<Complaints> toCollection();
	public Complaints save(Complaints complaint);
	 //CREATE and Update
	public Complaints add(Complaints complaint);
	 //READ
	public Complaints getById(Integer Id);
	 //DELETE
	public boolean remove(Complaints complaint);
	
}

