
	package org.app.service.entities;

	import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;
import java.io.BufferedReader;

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
import org.app.service.entities.ExecutieTest;
import org.app.service.entities.Test1;
import org.app.service.entities.TestFactory;
	@Path ("teste")
	@Stateless @LocalBean
	public class TestSprintDataServiceEJB<Sprint> extends EntityRepositoryBase<Test1> implements Serializable {

		private  static Logger logger = Logger.getLogger(TestSprintDataServiceEJB.class.getName());

	private EntityRepository<ExecutieTest> executietestRepository;

	@Inject
	private TestFactory testFactory;

	@Inject 
	private EntityRepository<Sprint> sprintRepository;

	@PostConstruct
	public void init() {
		executietestRepository = new EntityRepositoryBase<ExecutieTest>(this.em, ExecutieTest.class);
		sprintRepository.setEm(this.em);
		logger.info("Inizialized executietestRepository:" + executietestRepository.size());
		logger.info("Inizialized executietestRepository:" + executietestRepository.size());
	}




	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Test1 createNewTest(Integer id) {
		Test1 test1= testFactory.buildTest(id, "NEW Test", 3);
		this.add(test1);
		return Test1.toDTOAggregate(test1);
	}

	public String getMessage() {
		return "TestSprintDataService is working...";
	}

	//@Override
	@GET @Path ("/{id}")
	@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Test1 getById(@PathParam("id") Integer id){
		Test1 test1 =super.getById(id);
		return Test1.toDTOAggregate(test1);
	}

	@Override
	@GET 
	@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Test1> toCollection() {
		return Test1.toDTOs(super.toCollection());
		
	}

	@POST
	@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Test1> addNew(Test1 test1) {
		super.add(test1);
		return Test1.toDTOs(super.toCollection());
	}
	
	@PUT @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Test1 add(Test1 test1) {
		test1= super.add(test1);
		return Test1.toDTOAggregate(test1);
		
	}
	
	@Override
	@DELETE
	@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean remove(Test1 test1) {
		return super.remove(test1);
		}
	
	
	
	@DELETE @Path ("/{id}")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remove(@PathParam("id")Integer id) {
		logger.info("DEBUG: called REMOVE - ById() for teste >>>>>>>>>>>>>>>>.. simplified ! + id");
		Test1 test1 =super.getById(id);
		super.remove(test1);
		
	}
	
	
	}

