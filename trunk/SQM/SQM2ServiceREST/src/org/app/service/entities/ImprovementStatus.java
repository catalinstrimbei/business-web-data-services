package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ImprovementStatus {

	private String description;
	@Id@GeneratedValue
	private int idImprovementStatus;

	public ImprovementStatus(){

	}

	public void finalize() throws Throwable {

	}

	public ImprovementStatus(String description, int idImprovementStatus) {
		this.description = description;
		this.idImprovementStatus = idImprovementStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdImprovementStatus() {
		return idImprovementStatus;
	}

	public void setIdImprovementStatus(int idImprovementStatus) {
		this.idImprovementStatus = idImprovementStatus;
	}
	

}