package org.app.test.rest.patterns;


import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import org.app.service.entities.Complaints;
import org.app.service.entities.ComplaintsType;
import org.jboss.resteasy.util.GenericType;
import org.junit.Test;

public class TestRest {
	
	private static Logger logger=Logger.getLogger(TestRest.class.getName());
	
	@Test
	public void testGetComplaint() throws Exception{
		RESTfullResource<Complaints> resource=new RESTfullResource("http://localhost:8080/SQM3ServiceREST/complaints/",Complaints.class,"application/xml");
		Complaints complaint=resource.get();
		assertNotNull("Resource [/complaints/3001] not returned from service!",complaint);
		logger.info("DEBUG testGetComplaint: queried complaint"+complaint);
	}
	
	@Test
	public void testGetComplaints() throws Exception{
		RESTfullResource<Collection<Complaints>> resource=
				new RESTfullResource<Collection<Complaints>>(
						"http://localhost:8080/SQM3ServiceREST/complaints/",
						"application/xml",
						new GenericType<Collection<Complaints>>(){});
		Collection<Complaints> complaints=resource.get();
		assertNotNull("Message not returned from service!",complaints);
		for(Complaints c: complaints)
		  logger.info("DEBUG testGetComplaint: queried complaint"+complaints);
	}
	
	@Test
	public void testPOSTNewProjectResource() throws Exception{
		RESTfullResource<Collection<Complaints>> resource=
				new RESTfullResource<Collection<Complaints>>(
						"http://localhost:8080/SQM3ServiceREST/complaints/",
						"application/xml",
						new GenericType<Collection<Complaints>>(){});
		Collection<Complaints> complaints=resource.get();
		logger.info("DEBUG testPOSTNewProjectResource: queried complaint count: "+complaints.size());
		Complaints complaint=new Complaints();
		complaint.setComplaintsNumber(230);
		complaint.setComplaintsType(ComplaintsType.downtime);
		complaint.setDateOfClosedCompl(new Date("29.05.2014"));
		logger.info("DEBUG testPOSTNewProjectResource:"+complaint);
		resource.post(complaint);
		complaints=resource.get();
		logger.info("DEBUG testPOSTNewProjectResource: queried complaints count: " +complaints.size());
	}
	
	@Test
	public void testDELETENewProjectResource() throws Exception{
		RESTfullResource<Collection<Complaints>> resourceComplaint=
				new RESTfullResource<Collection<Complaints>>(
						"http://localhost:8080/SQM3ServiceREST/complaints/",
						"application/xml",
						new GenericType<Collection<Complaints>>(){});
		Collection<Complaints> complaints=resourceComplaint.get();
		logger.info("DEBUG testDELETENewProjectResource: queried complaint count: "+complaints.size());
		RESTfullResource<Complaints> resource=
				new RESTfullResource<Complaints>("http://localhost:8080/SQM3ServiceREST/complaints/325",Complaints.class,"application/xml");
						
		Complaints complaint=resource.get();
		
		logger.info("DEBUG testDELETENewProjectResource:"+complaint);
		assertNotNull("DEBUG testDELETENewProjectResource: Resource required [/complaints/3001] not returned from service", complaint);
		resourceComplaint.delete(complaint);
		complaints=resourceComplaint.get();
		logger.info("DEBUG testDELETENewProjectResource: queried complaints count: " +complaints.size());
	}
	
	 @Test
	 public void testPUTNewProjectResource() throws Exception{
		RESTfullResource<Collection<Complaints>> resource=
				new RESTfullResource<Collection<Complaints>>(
						"http://localhost:8080/SQM3ServiceREST/complaints/3001",
						Complaints.class,"application/xml");
		Complaints complaint=new Complaints();
		complaint.setComplaintsNumber(3001);
		complaint.setComplaintsType(ComplaintsType.downtime);
		complaint.setDateOfClosedCompl(new Date("29.05.2014"));
		logger.info("DEBUG testPUTNewProjectResource:"+complaint);
		
		Collection<Complaints> result=resource.put(complaint);
		logger.info("DEBUG testPUTNewProjectResource:"+result.size());
		assertNotNull(" Resource created [/complaints/3001] not returned from service", result);
		
	}

		@Test
		public void testDELETEProjectResource() throws Exception{
			
			RESTfullResource<Complaints> resource=
					new RESTfullResource<Complaints>("http://localhost:8080/SQM3ServiceREST/complaints/325",Complaints.class,"application/xml");
							
			Complaints complaint=resource.get();
			
			logger.info("DEBUG testDELETEProjectResource:"+complaint);
			assertNotNull("DEBUG testDELETEProjectResource: Resource required [/complaints/3001] not returned from service", complaint);
			resource.delete(null);
		}
	

}
