package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Release {

	private String codeName;
	private String description;
	@Id@GeneratedValue
	private int idRelease;
	private String indicative;
	private Feature m_Feature;
	private Project m_Project;

	public Release(){

	}

	public void finalize() throws Throwable {

	}

	public Release(String codeName, String description, int idRelease,
			String indicative, Feature m_Feature, Project m_Project) {
		this.codeName = codeName;
		this.description = description;
		this.idRelease = idRelease;
		this.indicative = indicative;
		this.m_Feature = m_Feature;
		this.m_Project = m_Project;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdRelease() {
		return idRelease;
	}

	public void setIdRelease(int idRelease) {
		this.idRelease = idRelease;
	}

	public String getIndicative() {
		return indicative;
	}

	public void setIndicative(String indicative) {
		this.indicative = indicative;
	}

	public Feature getM_Feature() {
		return m_Feature;
	}

	public void setM_Feature(Feature m_Feature) {
		this.m_Feature = m_Feature;
	}

	public Project getM_Project() {
		return m_Project;
	}

	public void setM_Project(Project m_Project) {
		this.m_Project = m_Project;
	}
	

}