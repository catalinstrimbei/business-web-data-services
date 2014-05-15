package org.app.service.entities;
import javax.persistence.GeneratedValue.*;
import javax.persistence.*;

import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;

enum Status{
  received,
  processing,
  finished 
}

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
	public Integer getComplaintsStatusId() {
		return complaintsStatusId;
	}
	public void setComplaintsStatusId(Integer complaintsStatusId) {
		this.complaintsStatusId = complaintsStatusId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

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
