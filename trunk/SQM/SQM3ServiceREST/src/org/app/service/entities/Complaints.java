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
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Complaints extends Comunication implements Serializable {

	private Integer complaintsId;  
	private  int ComplaintsNumber; 
	private ComplaintsType complaintType; 
    @Temporal(TemporalType.DATE)
    private Date dateOfClosedCompl;
    
    @ManyToOne
    private KnowledgeBase topicKnowledgeBase;
    
    @OneToOne(mappedBy="complaint")
    private Comunication comunication;
    //this is the first employee who recorded the complaint
    @ManyToOne
    private Employee employee; 
    
    @OneToMany(mappedBy="complaint")
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
	/*REST RESOURCE URL*/
	public static String BASE_URL="http://localhost:8000/SQM3ServiceREST/complaints/";
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
  
	public KnowledgeBase getTopicKnowledgeBase() {
		return topicKnowledgeBase;
	}

	public void setTopicKnowledgeBase(KnowledgeBase topicKnowledgeBase) {
		this.topicKnowledgeBase = topicKnowledgeBase;
	}
	@XmlElement
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    
	public Comunication getComunication() {
		return comunication;
	}
   
	public void setComunication(Comunication comunication) {
		this.comunication = comunication;
	}
	@XmlElementWrapper(name="complaintsStatus") @XmlElement(name="complaintsStatus")
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
	private Complaints toDTO() {
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
