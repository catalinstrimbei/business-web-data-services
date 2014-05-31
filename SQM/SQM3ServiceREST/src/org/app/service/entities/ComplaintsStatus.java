package org.app.service.entities;
import javax.persistence.GeneratedValue.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.service.rest.AtomLink;

import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;

enum Status{
  received,
  processing,
  finished 
}
@XmlRootElement(name="complaintsStatus")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class ComplaintsStatus implements Serializable {

	@Id @GeneratedValue 
	private Integer complaintsStatusId;  
	private Status status;
	private String statusDescription;
	@Temporal(TemporalType.DATE)
    private Date dateOfChangeStatus;
	//the employee who changed status of complain
	@ManyToOne
	private Employee employee; 
	
	@ManyToOne
	private Complaints complaint;
	/*REST RESOURCE URL*/
	public static String BASE_URL= Complaints.BASE_URL;
	@XmlElement(name="link")
	public AtomLink getLink() throws Exception{
		String restUrl=BASE_URL+this.getComplaint().getComplaintsNumber()+"/complaintsStatus/"+this.getComplaintsStatusId();
	
		return new AtomLink(restUrl,"get-complaintsStatus");	
	}
	 //construsctors 
	
	public ComplaintsStatus(){
	}
    
	public ComplaintsStatus(Integer complaintsStatusId, Status status,
			String statusDescription, Date dateOfChangeStatus,
			Complaints complaint) {
		super();
		this.complaintsStatusId = complaintsStatusId;
		this.status = status;
		this.statusDescription = statusDescription;
		this.dateOfChangeStatus = dateOfChangeStatus;
		this.complaint = complaint;
	}
	@XmlElement
	public Integer getComplaintsStatusId() {
		return complaintsStatusId;
	}
	public void setComplaintsStatusId(Integer complaintsStatusId) {
		this.complaintsStatusId = complaintsStatusId;
	}
	@XmlElement
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	@XmlElement
	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	@XmlElement
	public Date getDateOfChangeStatus() {
		return dateOfChangeStatus;
	}

	public void setDateOfChangeStatus(Date dateOfChangeStatus) {
		this.dateOfChangeStatus = dateOfChangeStatus;
	}
   
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@XmlElement
	public Complaints getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaints complaint) {
		this.complaint = complaint;
	}

	public static List<ComplaintsStatus> toDTOList(	List<ComplaintsStatus> complaintStatus) {
		List<ComplaintsStatus> complaintStatusDTOList=new ArrayList<>();
		for(ComplaintsStatus cs : complaintStatus)  {
			complaintStatusDTOList.add(cs.toDTO());
		}
		// TODO Auto-generated method stub
		return complaintStatusDTOList;
	}

	private ComplaintsStatus toDTO() {
		// TODO Auto-generated method stub
		return new ComplaintsStatus(this.complaintsStatusId,this.status, this.statusDescription,this.dateOfChangeStatus,this.complaint);
	}
	
}
