package org.app.service.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

	@Entity
	@Inheritance(strategy=InheritanceType.JOINED)
	public class Tasks implements Serializable{
	/**
		 * 
		 */
		private static final long serialVersionUID = 3196925210927782982L;

	@Id @GeneratedValue
	private Integer TaskId;
	
	@Temporal(TemporalType.DATE)
	private Date DueDate;
	@Temporal(TemporalType.DATE)
	private Date FinishDate;
	@Temporal(TemporalType.DATE)
	private Date StartDate;
	
	private String  TaskName;
	@Enumerated
	private TaskType TaskType;
	@ManyToOne
	private Member m_Member;
	@ManyToOne
	private TaskPhase m_TaskPhase;
	@ManyToOne
	private Releases releases;
	
	public Tasks(){

	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}

	public Date getFinishDate() {
		return FinishDate;
	}

	public void setFinishDate(Date finishDate) {
		FinishDate = finishDate;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public String getTaskName() {
		return TaskName;
	}

	public void setTaskName(String taskName) {
		TaskName = taskName;
	}

	public TaskType getTaskType() {
		return TaskType;
	}

	public void setTaskType(TaskType taskType) {
		TaskType = taskType;
	}

	public Member getM_Member() {
		return m_Member;
	}

	public void setM_Member(Member m_Member) {
		this.m_Member = m_Member;
	}

	public TaskPhase getM_TaskPhase() {
		return m_TaskPhase;
	}

	public void setM_TaskPhase(TaskPhase m_TaskPhase) {
		this.m_TaskPhase = m_TaskPhase;
	}

	public Integer getTaskId() {
		return TaskId;
	}

	public void setTaskId(Integer taskId) {
		TaskId = taskId;
	}

	
}
