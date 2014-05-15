package org.app.feedbackMangement.ejb.test;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.app.feedbackManagement.services.IComplaintsDataServices;
import org.app.service.entities.Complaints;
import org.app.service.entities.ComplaintsStatus;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestComplainsDataService {
	private static Logger logger = Logger
			.getLogger(TestComplainsDataService.class.getName());
	private static IComplaintsDataServices service;

	@BeforeClass
	public static void setUptBeforeClass() throws Exception {
		service = ComplaintsDataServicesEJBFactory
				.getComplaintsRepositoryServices();
	}	
	//CREATE_READ
	@Test
	public void testGetMessage() {
		String message = service.getMessage();
		assertNotNull("Message was not returned!", message);
		logger.info("DEBUG testGetMessage" + message);
	}
	
	
	//CREATE_AGGREGATE
	@Test
	public void testCreateNewComplanints(){
		Complaints complaints = service.createNewComplaints(3401);
		assertNotNull("Fail to create a new complaint in repository!", complaints);
		complaints.setComplaintsType(complaints.getComplaintType());
		List<ComplaintsStatus> cs = complaints.getComplaintStatus();
		for(ComplaintsStatus c : cs){
			c.setStatusDescription(c.getStatusDescription() + "changed by client");
			complaints = service.add(complaints);
			assertNotNull("Fail to save new complaints in repository!",complaints);
			logger.info("DEBUG createNewComplains : complaints changed "+ complaints);
			complaints = service.getById(3400);
			assertNotNull("Fail to find changed complaints in repository" ,complaints);
			logger.info("DEBUG createNewComplaints: queried project" + complaints);
		}
	}
	//READ_COLLECTION
	@Test
	public void testToCollection(){
		Collection<Complaints> complaints = service.toCollection();
		assertTrue("Fail to read complaints from repository!", complaints != null);
		logger.info("Debug testToCollection"+ complaints.size());
		assertTrue("Fail to read any complaints from repository!", complaints.size() > 0);
	}
	//READ UPDATE
	@Test
	public void testAdd(){
		Complaints complaints = service.getById(3400);
		assertNotNull("Fail to get complaints from repository!",complaints);
		complaints.setComplaintsType(complaints.getComplaintType());
		complaints = service.add(complaints);
		assertNotNull("Fail to save updated complaints in repository!",complaints);
		complaints = service.getById(3400);
		assertNotNull("Fail to find updated complaints repository!",complaints);
		logger.info("Debug complaints updated " + complaints);
	}
	
	//REMOVE
	@Test
	public void testRemove(){
		Complaints complaints = service.getById(3400);
		assertNotNull("Fail to get complaints in repository!",complaints);
		service.remove(complaints);
		complaints = service.getById(3400);
		logger.info("Debug testAdd: complaints removed" + complaints);
		assertNull("Fail to remove complaints in repository!", complaints);
	}
	
}
