package org.app.service.entities;


import javax.persistence.GeneratedValue.*;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Employee  implements Serializable {

	  @Id @GeneratedValue 
	  private Integer employeeId;
	  private String cnp;
	  private String firstName;
	  private String lastName;
	  private String adress;
	 private String city;
	  @Temporal(TemporalType.DATE)
	  private Date dateOfEmpl;
	  
	  @OneToMany(mappedBy="employee")
	  private List<Complaints> complaints=new ArrayList<Complaints>();
	  
	  @OneToMany(mappedBy="employee")
	  private List<ResolvedComplaints> resolvedComplaints=new ArrayList<ResolvedComplaints>();
	  
	  @OneToMany(mappedBy="employee")
	  private List<ComplaintsStatus> complaintStatus=new ArrayList<ComplaintsStatus>();
	  
	  @ManyToOne
	  private EmployeeLevel employeeLevel; 
	  
	  public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDateOfEmpl() {
		return dateOfEmpl;
	}

	public void setDateOfEmpl(Date dateOfEmpl) {
		this.dateOfEmpl = dateOfEmpl;
	}

	public List<Complaints> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaints> complaints) {
		this.complaints = complaints;
	}

	public EmployeeLevel getEmployeeLevel() {
		return employeeLevel;
	}

	public void setEmployeeLevel(EmployeeLevel employeeLevel) {
		this.employeeLevel = employeeLevel;
	}
	 public List<ResolvedComplaints> getResolvedComplaints() {
			return resolvedComplaints;
		}

		public void setResolvedComplaints(List<ResolvedComplaints> resolvedComplaints) {
			this.resolvedComplaints = resolvedComplaints;
		}

		public List<ComplaintsStatus> getComplaintStatus() {
			return complaintStatus;
		}

		public void setComplaintStatus(List<ComplaintsStatus> complaintStatus) {
			this.complaintStatus = complaintStatus;
		}

		

	
}
