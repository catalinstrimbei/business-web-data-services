package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class ImprovementSolution {

	private String description;
	private String entryDate;
	@Id @GeneratedValue
	private int idImprovement;
	private int status;
	private ImprovementStatus m_ImprovementStatus;

	public ImprovementSolution(){

	}

	public void finalize() throws Throwable {

	}

	public ImprovementSolution(String description, String entryDate,
			int idImprovement, int status, ImprovementStatus m_ImprovementStatus) {
		this.description = description;
		this.entryDate = entryDate;
		this.idImprovement = idImprovement;
		this.status = status;
		this.m_ImprovementStatus = m_ImprovementStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public int getIdImprovement() {
		return idImprovement;
	}

	public void setIdImprovement(int idImprovement) {
		this.idImprovement = idImprovement;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ImprovementStatus getM_ImprovementStatus() {
		return m_ImprovementStatus;
	}

	public void setM_ImprovementStatus(ImprovementStatus m_ImprovementStatus) {
		this.m_ImprovementStatus = m_ImprovementStatus;
	}
	

}