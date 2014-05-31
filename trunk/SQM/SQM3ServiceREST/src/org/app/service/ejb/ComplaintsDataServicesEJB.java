package org.app.service.ejb;
import org.app.patterns.*;
import org.app.service.entities.*;
import org.app.service.entities.Complaints.*;

import java.util.Collection;
import java.util.logging.Logger;
import java.awt.PageAttributes.MediaType;
import java.io.BufferedReader;
import java.io.Serializable;						

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

@Path("complaint")
@Stateless @LocalBean
public class ComplaintsDataServicesEJB extends EntityRepositoryBase<Complaints> implements Serializable,IComplaintsDataServices{
 
	private  static Logger LOGGER = Logger.getLogger(ComplaintsDataServicesEJB.class.getName());
	private EntityRepository<ComplaintsStatus> complaintsStatusRepository;
	//needs more repository
	@Inject
	private ComplaintsFactory complainstFactory;
	
	@PostConstruct
	public void init(){
		complaintsStatusRepository=new EntityRepositoryBase<ComplaintsStatus>(this.em, ComplaintsStatus.class);
		LOGGER.info("Initialized complaintsStatusRepository : "+complaintsStatusRepository.size());
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Complaints createNewComplaints(Integer Id) {
		Complaints complaint = complainstFactory.buildComplaint(Id,ComplaintsType.downtime , 1002);
		this.add(complaint);
		return Complaints.toDTOAggregate(complaint);	
	}
	
	@Override
	public String getMessage() {
		return "ComplaintssDataServices is working...";
	}
	

	@Override
	public String test() {
		return "Module ComplaintsDataService works";
	}

	 @GET
	 @Path("/{id}")
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
	
	@Override
	public Complaints getById(Object id){
		Complaints complaints = super.getById(id);
		return Complaints.toDTOAggregate(complaints);
	}
	
	@Override
	public  Complaints add(Complaints complaint){
		complaint = super.add(complaint);
		return Complaints.toDTOAggregate(complaint);
	}
	@Override
	public boolean remove (Complaints complaint){
		return super.remove(complaint);
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

	@Override
	public Complaints save(Complaints complaint) {
		complaint = super.add(complaint);
		return complaint;
	}

	
	
}
