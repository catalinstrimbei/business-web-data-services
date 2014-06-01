package org.app.service.entities;
import javax.persistence.Entity;
@Entity
public class TaskCategory extends Task {

	private String description;
	private int idTaskCategory;

	public TaskCategory(){

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

	public int getIdTaskCategory() {
		return idTaskCategory;
	}

	public void setIdTaskCategory(int idTaskCategory) {
		this.idTaskCategory = idTaskCategory;
	}

	public TaskCategory(String description, int idTaskCategory) {
		this.description = description;
		this.idTaskCategory = idTaskCategory;
	}
	

}