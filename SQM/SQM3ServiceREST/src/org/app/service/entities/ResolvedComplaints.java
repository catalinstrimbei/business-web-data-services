package org.app.service.entities;

import javax.persistence.GeneratedValue.*;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ResolvedComplaints  implements Serializable {
	
	@Id @GeneratedValue
	protected Integer resolvedComplaintsId;
    @Temporal(TemporalType.DATE)
	private Date date;
   	//this is the employee who resolved the
	@ManyToOne
	private Employee employee; 
	
   public Integer getResolvedComplaintsId() {
		return resolvedComplaintsId;
	}

	public void setResolvedComplaintsId(Integer resolvedComplaintsId) {
		this.resolvedComplaintsId = resolvedComplaintsId;
	}

   public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



}
