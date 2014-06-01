
package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ProjectStatus extends Project {

	private String description;
	private int idStatus;

	public ProjectStatus(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public ProjectStatus(String description, int idStatus) {
		this.description = description;
		this.idStatus = idStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}
	

}