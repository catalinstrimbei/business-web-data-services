package org.app.service.entities;

import javax.persistence.GeneratedValue.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.service.rest.AtomLink;

import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;

@XmlRootElement(name="complaints")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity

public class Complaints implements Serializable {

	@Id 
	//@GeneratedValue 
	private Integer complaintsId;  
	private  int ComplaintsNumber; 
	private  ComplaintsType complaintType; 
    @Temporal(TemporalType.DATE)
    private Date dateOfClosedCompl;

   private String topicKnowledgeBase; 
   private String comunicationType;

    private Integer employeeId; 
    
    @OneToMany(mappedBy="complaint", cascade =CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=false)
	private List<ComplaintsStatus> complaintStatus=new ArrayList<ComplaintsStatus>();
 
 //construsctors  
    
	public Complaints(){}
	public Complaints(Integer complaintsId, ComplaintsType complaintType,
			Date dateOfClosedCompl) {
		super();
		this.complaintsId = complaintsId;
		this.complaintType = complaintType;
		this.dateOfClosedCompl = dateOfClosedCompl;
	}
	
	public Complaints(Integer complaintsId, ComplaintsType complaintType,Integer complaintsNumber ) {
		super();
		this.complaintsId = complaintsId;
		this.complaintType = complaintType;
		this.ComplaintsNumber = complaintsNumber;
	}
	/*REST RESOURCE URL*/
	public static String BASE_URL="http://localhost:8080/SQM3ServiceREST/complaints/";
	
	@XmlElement(name="link")
	public AtomLink getLink() throws Exception{
		String restUrl=BASE_URL+this.getComplaintsNumber();
		return new AtomLink(restUrl,"get-complaint");	
	}
//get set
	@XmlElement
    public Integer getComplaintsId() {
		return complaintsId;
	}

	public void setComplaintsId(Integer complaintsId) {
		this.complaintsId = complaintsId;
	}
	@XmlElement
	 public Integer getComplaintsNumber() {
			return complaintsId;
		}

	public void setComplaintType(ComplaintsType complaintType) {
		this.complaintType = complaintType;
	}
	public void setComplaintsNumber(Integer complaintsNumber) {
			this.ComplaintsNumber=complaintsNumber;
		}
   @XmlElement
	public ComplaintsType getComplaintType() {
		return complaintType;
	}
   
	public void setComplaintsType(ComplaintsType complaintType) {
		this.complaintType = complaintType;
	}

	@XmlElement
	public Date getDateOfClosedCompl() {
		return dateOfClosedCompl;
	}


	public void setDateOfClosedCompl(Date dateOfClosedCompl) {
		this.dateOfClosedCompl = dateOfClosedCompl;
	}
  
	public String getTopicKnowledgeBase() {
		return topicKnowledgeBase;
	}

	public void setTopicKnowledgeBase(String topicKnowledgeBase) {
		this.topicKnowledgeBase = topicKnowledgeBase;
	}
	
	public Integer getEmployee() {
		return employeeId;
	}

	public void setEmployee(Integer employee) {
		this.employeeId = employee;
	}
    
	public String getComunication() {
		return comunicationType;
	}
   
	public void setComunication(Comunication comunication) {
	this.comunicationType = comunicationType;
	}
	
	//@XmlElementWrapper(name="complaintsStatus") @XmlElement(name="complaintsStatus")
	public List<ComplaintsStatus> getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(List<ComplaintsStatus> complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	
	public static Complaints toDTOAggregate(Complaints complaint) {
		// TODO Auto-generated method stub
		if(complaint == null){
			return null;
		}
		Complaints complaintDTO = complaint.toDTO();
		List<ComplaintsStatus> complaintStatusDTO =ComplaintsStatus.toDTOList(complaint.getComplaintStatus());
		complaintDTO.setComplaintStatus(complaintStatusDTO);
		return complaintDTO;
	}
	public Complaints toDTO() {
		// TODO Auto-generated method stub
		Complaints complaintDTO = new Complaints(complaintsId,complaintType,dateOfClosedCompl);		
		return complaintDTO;
	}
	public static Collection<Complaints> toDTOs(Collection<Complaints> collection) {
		List<Complaints> complaintsDTO =new ArrayList<>();
		for(Complaints c : collection)  {
			complaintsDTO.add(c.toDTO());
		}
		// TODO Auto-generated method stub
		return complaintsDTO;

	}
}
