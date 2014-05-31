package org.app.service.entities;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Test1;
@Remote
public interface TestSprintDataService  extends EntityRepository<Test1>{

	public String getMessage();
	public Test1 createNewTest(Integer id);
	
}
