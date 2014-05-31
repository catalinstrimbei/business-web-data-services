package org.app.sqm3.ejb.test;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.app.service.ejb.ComplaintsDataService;
import org.app.service.entities.Complaints;
import org.app.service.entities.ComplaintsStatus;
import org.app.service.entities.ComplaintsType;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestComplainsDataService {
	private static Logger logger = Logger
			.getLogger(TestComplainsDataService.class.getName());
	private static ComplaintsDataService service;

	@BeforeClass
	public static void setUptBeforeClass() throws Exception {
		service = ComplaintsDataServicesEJBFactory.getComplaintsRepositoryService();
	}	
	//CREATE_READ
	@Test
	public void testGetMessage() {
		String message = service.getMessage();
		assertNotNull("Message was not returned!", message);
		logger.info("DEBUG testGetMessage" + message);
	}
	// @Test
	 public void test() {
	  logger.info("DEBUG: Junit TESTING ...");
	  logger.info("DEBUG: EJB Response ..." + service.test());
	 }
	 
	 
	 @Test
	 public void testSave() {
	  logger.info("DEBUG: Junit TESTING ...");
	  Complaints c = new Complaints();
	  c.setComplaintsId(101);
	  c.setComplaintsType(ComplaintsType.incident);
	  c.setComplaintsNumber(1230);
	  c=service.save(c);
	  
	  logger.info("DEBUG: EJB Response ..." + c);
	  logger.info("DEBUG: Junit TESTING ...");
	  Complaints c1 = new Complaints();
	  c1.setComplaintsId(102);
	  c1.setComplaintsType(ComplaintsType.downtime);
	  c1.setComplaintsNumber(1231);
	  logger.info("DEBUG: EJB Response ..." + c1);
	 }
	/*
	 @Test
	public void testCreateComplanint(){
		Complaints complaint = new Complaints(1201,ComplaintsType.downtime,new Date());
		complaint=service.add(complaint);
		logger.info("DEBUG create complaint : save complaint"+complaint);
		assertNotNull("Failed to create a new complaint in repository!", complaint);
		complaint=service.getById(1201);
		assertNotNull("Failed to find  new complaint in repository!", complaint);
		logger.info("DEBUG create complaint : queried complaint"+complaint);
			
		}
	@Test
	public void testCreateNewComplanint(){
		Complaints complaints = service.createNewComplaints(3401);
		assertNotNull("Failed to create a new complaint in repository!", complaints);
		complaints.setComplaintsType(complaints.getComplaintType());
		List<ComplaintsStatus> cs = complaints.getComplaintStatus();
		for(ComplaintsStatus c : cs){
			c.setStatusDescription(c.getStatusDescription() + "changed by test client");
			complaints = service.add(complaints);
			assertNotNull("Failed to save new complaints in repository!",complaints);
			logger.info("DEBUG createNewComplains : complaints changed "+ complaints);
			complaints = service.getById(3400);
			assertNotNull("Failed to find changed complaints in repository" ,complaints);
			logger.info("DEBUG createNewComplaints: queried project" + complaints);
		}
	}
	
	//READ_COLLECTION
	@Test
	public void testToCollection(){
		Collection<Complaints> complaints = service.toCollection();
		assertTrue("Failed to read complaints from repository!", complaints != null);
		logger.info("Debug testToCollection"+ complaints.size());
		assertTrue("Failed to read any complaints from repository!", complaints.size() > 0);
		for(Complaints c: complaints)
			logger.info("DEBUG testToCollection"+c);
	}
	//UPDATE
	@Test
	public void testUpdate(){
		Complaints complaints = service.getById(101);
		assertNotNull("Failed to get complaints from repository!",complaints);
		complaints.setComplaintsNumber(102);
		complaints = service.add(complaints);
		assertNotNull("Failed to save updated complaints in repository!",complaints);
		
		
		Complaints complaints1 = service.getById(103);
		assertNotNull("Failed to find updated complaints repository!",complaints);
		complaints1.setComplaintsNumber(143);
		complaints1 = service.add(complaints);
		assertNotNull("Failed to save updated complaints in repository!",complaints);
		logger.info("Debug complaints updated " + complaints);
	}
	
	//REMOVE
	@Test
	public void testRemove(){
		Complaints complaints = service.getById(101);
		assertNotNull("Failed to get complaints in repository!",complaints);
		service.remove(complaints);
		complaints = service.getById(101);
		logger.info("Debug testAdd: complaints removed" + complaints);
		assertNull("Failed to remove complaints in repository!", complaints);
	}*/
	
}
