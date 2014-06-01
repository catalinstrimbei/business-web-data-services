package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.EntityBase;
import org.app.service.entities.Task;



@Remote
public interface DataService extends EntityRepository<Task>{

	String sayRest();
	
	public String getMessage();
	
	public Task createNewTask(int id);


}