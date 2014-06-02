package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

   
	@Entity
    public class TaskPhase {

	@Id
	private Integer IdTP;	
		
	@Enumerated
	private Phase Phase;
	@Enumerated
	private Status Status;
	@ManyToOne
	private TaskExecution m_TaskExecution;

	public TaskPhase(){

	}

	public void finalize() throws Throwable {

	}

	public Phase getPhase() {
		return Phase;
	}

	public void setPhase(Phase phase) {
		Phase = phase;
	}

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public TaskExecution getM_TaskExecution() {
		return m_TaskExecution;
	}

	public void setM_TaskExecution(TaskExecution m_TaskExecution) {
		this.m_TaskExecution = m_TaskExecution;
	}

	public Integer getIdTP() {
		return IdTP;
	}

	public void setIdTP(Integer idTP) {
		IdTP = idTP;
	}
}