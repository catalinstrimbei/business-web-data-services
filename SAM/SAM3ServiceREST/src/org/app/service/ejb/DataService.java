package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.EntityBase;
import org.app.service.entities.Projects;


@Remote
public interface DataService 
	extends EntityRepository<Projects>{
//Classic CRUD methods:
//CREATE and UPDATE
/*add(Projects p) 		inherited from EntityRepository
//READ
/*getById(Integer id) 	inherited from EntityRepository
//DELETE
/*remove(Projects p) 	inherited from EntityRepository

//Other business data methods to be exposed
*/
public String getMessage();
public Projects createNewProjects(Integer id);

String sayRest();

}