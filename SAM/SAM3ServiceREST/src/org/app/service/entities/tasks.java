package org.app.service.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class tasks {
	private Date DueDate;
	private Date FinishDate;
	private Date StartDate;
	@Id
	@GeneratedValue
	private char TaskId;
	private char TaskName;
	@Enumerated
	private taskType TaskType;
	@ManyToOne
	public member m_Member;
	@ManyToOne
	public taskPhase m_TaskPhase;

	public tasks(){

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

	public char getTaskId() {
		return TaskId;
	}

	public void setTaskId(char taskId) {
		TaskId = taskId;
	}

	public char getTaskName() {
		return TaskName;
	}

	public void setTaskName(char taskName) {
		TaskName = taskName;
	}

	public taskType getTaskType() {
		return TaskType;
	}

	public void setTaskType(taskType taskType) {
		TaskType = taskType;
	}

	public member getM_Member() {
		return m_Member;
	}

	public void setM_Member(member m_Member) {
		this.m_Member = m_Member;
	}

	public taskPhase getM_TaskPhase() {
		return m_TaskPhase;
	}

	public void setM_TaskPhase(taskPhase m_TaskPhase) {
		this.m_TaskPhase = m_TaskPhase;
	}

}
