
package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Project {

	private String endDate;
	private String name;
	@Id@GeneratedValue
	private int projectNo;
	private String startDate;
	private int status;

	public Project(){

	}

	public void finalize() throws Throwable {

	}

	public Project(String endDate, String name, int projectNo,
			String startDate, int status) {
		this.endDate = endDate;
		this.name = name;
		this.projectNo = projectNo;
		this.startDate = startDate;
		this.status = status;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}