package org.app.service.entities;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class taskExecution {

	private Date ExecutionDate;
	private double ExecutionEffort;
	private double PlanningEffort = 0;
	private double Progress;

	public taskExecution(){

	}

	public void finalize() throws Throwable {

	}

	public Date getExecutionDate() {
		return ExecutionDate;
	}

	public void setExecutionDate(Date executionDate) {
		ExecutionDate = executionDate;
	}

	public double getExecutionEffort() {
		return ExecutionEffort;
	}

	public void setExecutionEffort(double executionEffort) {
		ExecutionEffort = executionEffort;
	}

	public double getPlanningEffort() {
		return PlanningEffort;
	}

	public void setPlanningEffort(double planningEffort) {
		PlanningEffort = planningEffort;
	}

	public double getProgress() {
		return Progress;
	}

	public void setProgress(double progress) {
		Progress = progress;
	}

}
