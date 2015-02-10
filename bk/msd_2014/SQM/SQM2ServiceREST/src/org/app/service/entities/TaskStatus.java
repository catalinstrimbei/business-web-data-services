package org.app.service.entities;
import javax.persistence.Entity;
@Entity
public class TaskStatus extends Task {

	private String description;
	private int idTaskStatus;

	public TaskStatus(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdTaskStatus() {
		return idTaskStatus;
	}

	public void setIdTaskStatus(int idTaskStatus) {
		this.idTaskStatus = idTaskStatus;
	}

	public TaskStatus(String description, int idTaskStatus) {
		this.description = description;
		this.idTaskStatus = idTaskStatus;
	}
	

}