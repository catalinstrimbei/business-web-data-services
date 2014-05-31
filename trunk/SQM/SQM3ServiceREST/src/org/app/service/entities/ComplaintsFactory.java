package org.app.service.entities;
import java.util.*;

import javax.ejb.Singleton;


@Singleton
public class ComplaintsFactory {
	
	public static Complaints buildComplaint(Integer complaintsId, ComplaintsType complaintType,Integer complStatusCount){
	
		Complaints complaint=new Complaints(complaintsId,complaintType,new Date());
	    List<ComplaintsStatus> complaintStatus=new ArrayList<ComplaintsStatus>();
	    
	    Date dateOfChangeStatus=new Date();
	    Long interval=30l*24*60*60*1000;
	    
	    for(int i=0;i<complStatusCount;i++){
	    	complaintStatus.add(new ComplaintsStatus(null,null,null,new Date(dateOfChangeStatus.getDate()+i*interval),complaint));
		}
		complaint.setComplaintStatus(complaintStatus);
		return complaint;
	}

}
