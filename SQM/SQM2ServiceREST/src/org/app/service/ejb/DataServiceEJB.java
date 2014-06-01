package org.app.service.ejb;

import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.EntityBase;
import org.app.service.entities.Task;
import org.app.service.entities.TaskFactory;
import org.app.service.entities.Test;

/**
 * Session Bean implementation class ScrumTeamRepositoryService
 * Aggregate Repository Service Facade: Project - features - releases
 */
@Path("service")

@Stateless
@LocalBean
public class DataServiceEJB extends EntityRepositoryBase<Task> implements DataService,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(DataServiceEJB.class.getName());
	
	
	private EntityRepository<Test> testRepository;
	
	@Inject
	private TaskFactory taskFactory;
	
    @PostConstruct
    public void Init()
	{
		testRepository= new EntityRepositoryBase<Test>(this.em,Test.class);
		
	}

	public DataServiceEJB() {
		super();
		logger.info("INIT DEF CONSTRUCTOR ScrumProjectDataServiceEJB : " + this.em);		
	}

	
	/********************************************************************/
	@GET
	@Produces("text/html")
	public String sayRest() {
		return "Rest-EJB-SQM2... ";
	}	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Task createNewTask(int id)
	{
		Task task= taskFactory.buildTask(id, "New task", 1);
		this.add(task);
		
		return Task.toDTOAggregate(task);
	}
	
	public String getMessage()
	{
		return "Project is working...";
	}
	
	@Override
	public Task getById(Object id){
		Task task=super.getById(id);
		return task.toDTOAggregate(task);
	}
	
	@Override
	public Collection <Task> toCollection(){
		return Task.toDTOs(super.toCollection());
	}
	
	@Override
	public Task add(Task task){
		task=super.add(task);
		return Task.toDTOAggregate(task);
	}
	
	@Override
	public boolean remove(Task task){
		
		return super.remove(task);
	}
	
	/********************************************************************/
}

// http://localhost:8080/ServiceREST/service
