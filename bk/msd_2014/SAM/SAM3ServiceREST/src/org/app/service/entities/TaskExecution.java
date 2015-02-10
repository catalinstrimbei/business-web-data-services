package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

    @Entity
    public class TaskExecution {
	
	@Id
	private Integer Id;
	
	@Temporal(TemporalType.DATE)
	private Date ExecutionDate;
	private Double ExecutionEffort;
	private Double PlanningEffort = 0.0;
	private Double Progress;

	public TaskExecution(){

	}

	public void finalize() throws Throwable {

	}

	public Date getExecutionDate() {
		return ExecutionDate;
	}

	public void setExecutionDate(Date executionDate) {
		ExecutionDate = executionDate;
	}

	public Double getExecutionEffort() {
		return ExecutionEffort;
	}

	public void setExecutionEffort(Double executionEffort) {
		ExecutionEffort = executionEffort;
	}

	public Double getPlanningEffort() {
		return PlanningEffort;
	}

	public void setPlanningEffort(Double planningEffort) {
		PlanningEffort = planningEffort;
	}

	public Double getProgress() {
		return Progress;
	}

	public void setProgress(Double progress) {
		Progress = progress;
	}

}
