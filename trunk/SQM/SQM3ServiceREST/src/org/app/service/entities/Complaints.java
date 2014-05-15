package org.app.service.entities;

import javax.persistence.GeneratedValue.*;
import javax.persistence.*;

import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Complaints  implements Serializable {

    @Id @GeneratedValue 
	private Integer complaintsId;  
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
//get set
    public Integer getComplaintsId() {
		return complaintsId;
	}

	public void setComplaintsId(Integer complaintsId) {
		this.complaintsId = complaintsId;
	}

	public ComplaintsType getComplaintType() {
		return complaintType;
	}

	public void setComplaintsType(ComplaintsType complaintType) {
		this.complaintType = complaintType;
	}


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
