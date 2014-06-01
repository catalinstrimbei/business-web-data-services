package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Feature {

	private String description;
	private int featureCategory;
	@Id @GeneratedValue
	private int idFeature;
	private String name;
	private Team team;

	public Feature(){

	}

	public void finalize() throws Throwable {

	}

	public Feature(String description, int featureCategory, int idFeature,
			String name, Team team) {
		this.description = description;
		this.featureCategory = featureCategory;
		this.idFeature = idFeature;
		this.name = name;
		this.team = team;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFeatureCategory() {
		return featureCategory;
	}

	public void setFeatureCategory(int featureCategory) {
		this.featureCategory = featureCategory;
	}

	public int getIdFeature() {
		return idFeature;
	}

	public void setIdFeature(int idFeature) {
		this.idFeature = idFeature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	

}