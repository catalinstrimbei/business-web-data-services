package org.app.service.entities;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Optimize {

	private Date dateAdded;
	private String description;
	@Id@GeneratedValue
	private int idOptimize;
	@ManyToOne
	private Task task;

	public Optimize(){

	}

	public void finalize() throws Throwable {

	}

	

	

	public Optimize(Date dateAdded, String description, int idOptimize,
			Task task) {
		this.dateAdded = dateAdded;
		this.description = description;
		this.idOptimize = idOptimize;
		this.task = task;
	}

	
	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdOptimize() {
		return idOptimize;
	}

	public void setIdOptimize(int idOptimize) {
		this.idOptimize = idOptimize;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	

}