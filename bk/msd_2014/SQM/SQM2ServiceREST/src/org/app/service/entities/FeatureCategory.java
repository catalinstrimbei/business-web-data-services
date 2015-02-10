package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class FeatureCategory extends Feature {

	private String description;
	private int idFeatureCategory;
	private String name;
	private Team m_Team;

	public FeatureCategory(){

	}
	

	public FeatureCategory(String description, int idFeatureCategory,
			String name, Team m_Team) {
		super();
		this.description = description;
		this.idFeatureCategory = idFeatureCategory;
		this.name = name;
		this.m_Team = m_Team;
	}
	

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdFeatureCategory() {
		return idFeatureCategory;
	}

	public void setIdFeatureCategory(int idFeatureCategory) {
		this.idFeatureCategory = idFeatureCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getM_Team() {
		return m_Team;
	}

	public void setM_Team(Team m_Team) {
		this.m_Team = m_Team;
	}
	

}