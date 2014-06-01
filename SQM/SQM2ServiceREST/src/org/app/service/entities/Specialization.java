package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Specialization {

	private String description;
	@Id@GeneratedValue
	private int idSpecialization;

	public Specialization(){

	}

	public void finalize() throws Throwable {

	}

	public Specialization(String description, int idSpecialization) {
		this.description = description;
		this.idSpecialization = idSpecialization;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdSpecialization() {
		return idSpecialization;
	}

	public void setIdSpecialization(int idSpecialization) {
		this.idSpecialization = idSpecialization;
	}
	

}