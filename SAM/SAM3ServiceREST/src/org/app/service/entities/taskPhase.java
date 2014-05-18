package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class taskPhase {

	@Enumerated
	private phase Phase;
	@Enumerated
	private status Status;
	@ManyToOne
	public taskExecution m_TaskExecution;

	public taskPhase(){

	}

	public void finalize() throws Throwable {

	}

	public phase getPhase() {
		return Phase;
	}

	public void setPhase(phase phase) {
		Phase = phase;
	}

	public status getStatus() {
		return Status;
	}

	public void setStatus(status status) {
		Status = status;
	}

	public taskExecution getM_TaskExecution() {
		return m_TaskExecution;
	}

	public void setM_TaskExecution(taskExecution m_TaskExecution) {
		this.m_TaskExecution = m_TaskExecution;
	}
}