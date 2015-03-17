
package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.*;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Complaints;
import org.app.patterns.*;
import org.app.service.entities.*;
import org.app.service.entities.Complaints.*;

import java.util.Collection;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.Serializable;						

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("complaints")
@Stateless @LocalBean
public class ComplaintsDataServiceEJB extends EntityRepositoryBase<Complaints> implements Serializable,ComplaintsDataService{
 
	private  static Logger LOGGER = Logger.getLogger(ComplaintsDataServiceEJB.class.getName());
	
	@Inject ComplaintsFactory complaintsFactory;
	private EntityRepository<ComplaintsStatus> complaintsStatusRepository;
	
	
	@PostConstruct
	public void init(){
		complaintsStatusRepository=new EntityRepositoryBase<ComplaintsStatus>(this.em, ComplaintsStatus.class);
		LOGGER.info("Initialized complaintsStatusRepository : "+complaintsStatusRepository.size());
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
	public Complaints createNewComplaints(Integer Id) {
		Complaints complaint = complaintsFactory.buildComplaint(Id,ComplaintsType.downtime , 3);
		this.add(complaint);
		return Complaints.toDTOAggregate(complaint);	
	}
	@Override
	public String getMessage() {
		return "ComplaintssDataServices is working...";
	}
	
	@Override
	public Complaints save(Complaints complaint) {
		complaint = super.add(complaint);
		  return complaint;
	}
	
	@Override
	public String test() {
		return "The service ComplaintssDataServices says hello !";
	}
	
	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Complaints getById(@PathParam("id") Integer id){
		Complaints complaints = super.getById(id);
		return Complaints.toDTOAggregate(complaints);
	}
	
	@Override
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public  Collection<Complaints>  toCollection(){
		
		return Complaints.toDTOs(super.toCollection());
	}
	
	@GET
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public  Collection<Complaints>  addNew(Complaints complaints){
		super.add(complaints);
		return Complaints.toDTOs(super.toCollection());
	}
	
	@Override
	@PUT @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public  Complaints add(Complaints complaint){
		complaint = super.add(complaint);
		return Complaints.toDTOAggregate(complaint);
	}
	
	@Override
	@DELETE
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean remove (Complaints complaint){
		return super.remove(complaint);
	}
	
	
	public void remove (@PathParam("id") Integer id){
		LOGGER.info("DEBUG:called REMOVE - byId");
		Complaints complaints=super.getById(id);
		super.remove(complaints);
	}
	
	@Override
	public boolean removeAll (Collection<Complaints> complaints){
		return super.removeAll(complaints);
	}
	 @Override
	 public  int size(){
		 return super.size();
	 }
	 
	 @Override
	 public  Complaints refresh(Complaints complaints)
	 {
		 return super.refresh(complaints);
	 }

}