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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Task;
import org.app.service.entities.TaskFactory;
import org.app.service.entities.Test;

/**
 * Session Bean implementation class ScrumTeamRepositoryService
 * Aggregate Repository Service Facade: Project - features - releases
 */
@Path("tasks")

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
	
	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_XML }) 
	public Task getById(@PathParam("id") Object id){
		Task task=super.getById(id);
		return task.toDTOAggregate(task);
	}
	
	@Override
	@GET 
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_XML }) 
	public Collection <Task> toCollection(){
		return Task.toDTOs(super.toCollection());
	}
	
	@PUT @Path("/{id}") 	/* scrum/projects/{id} 	REST-resource: project-entity*/	
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@Override
	public Task add(Task task){
		task=super.add(task);
		return Task.toDTOAggregate(task);
	}
	
	@Override
	@DELETE 				/* scrum/projects 		REST-resource: projects-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public boolean remove(Task task){
		
		return super.remove(task);
	}
	
	@POST 					/* scrum/projects 		REST-resource: projects-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Collection<Task> addNew(Task task) {
		super.add(task);
		return Task.toDTOs(super.toCollection());
	}
	
	@DELETE @Path("/{id}") 	/* scrum/projects/{id} 	REST-resource: project-entity*/	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public void remove(@PathParam("id")Integer id) {
		logger.info("DEBUG: called REMOVE - ById() for tasks >>>>>>>>>>>>>> simplified ! + id");
		Task task = super.getById(id);
		super.remove(task);
	}	
	
	
	
	/********************************************************************/
}

// http://localhost:8080/SQM2ServiceREST/tasks/
