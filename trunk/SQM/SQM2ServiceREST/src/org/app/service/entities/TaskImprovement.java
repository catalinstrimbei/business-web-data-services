package org.app.service.entities;
import javax.persistence.Entity;
@Entity
public class TaskImprovement extends Task {

	private int idImprovement;
	private int idTask;
	private Team m_Team;
	private ImprovementSolution m_ImprovementSolution;
	private TaskStatus m_TaskStatus;

	public TaskImprovement(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public TaskImprovement(int idImprovement, int idTask, Team m_Team,
			ImprovementSolution m_ImprovementSolution, TaskStatus m_TaskStatus) {
		this.idImprovement = idImprovement;
		this.idTask = idTask;
		this.m_Team = m_Team;
		this.m_ImprovementSolution = m_ImprovementSolution;
		this.m_TaskStatus = m_TaskStatus;
	}

	public int getIdImprovement() {
		return idImprovement;
	}

	public void setIdImprovement(int idImprovement) {
		this.idImprovement = idImprovement;
	}

	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public Team getM_Team() {
		return m_Team;
	}

	public void setM_Team(Team m_Team) {
		this.m_Team = m_Team;
	}

	public ImprovementSolution getM_ImprovementSolution() {
		return m_ImprovementSolution;
	}

	public void setM_ImprovementSolution(ImprovementSolution m_ImprovementSolution) {
		this.m_ImprovementSolution = m_ImprovementSolution;
	}

	public TaskStatus getM_TaskStatus() {
		return m_TaskStatus;
	}

	public void setM_TaskStatus(TaskStatus m_TaskStatus) {
		this.m_TaskStatus = m_TaskStatus;
	}
	

}