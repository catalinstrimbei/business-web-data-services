package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Training {
	
	@Id
	private Integer idTraining;
	
	private String numeTraining;
	
	@OneToMany(cascade=ALL,fetch=EAGER, orphanRemoval = false)
	private List<Competente> competente = new ArrayList();

	@OneToMany(cascade=ALL,fetch=EAGER, orphanRemoval = false, mappedBy = "training")
	private List<TrainingEchipe> trainingEchipeT = new ArrayList<>();
	
	@OneToMany(cascade=ALL,fetch=EAGER, orphanRemoval = false, mappedBy = "training")
	private List<TrainingMembri> trainingMembriT = new ArrayList<>();
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTraining == null) ? 0 : idTraining.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Training other = (Training) obj;
		if (idTraining == null) {
			if (other.idTraining != null)
				return false;
		} else if (!idTraining.equals(other.idTraining))
			return false;
		return true;
	}

	public Integer getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(Integer idTraining) {
		this.idTraining = idTraining;
	}

	public String getNumeTraining() {
		return numeTraining;
	}

	public void setNumeTraining(String numeTraining) {
		this.numeTraining = numeTraining;
	}

	public List<Competente> getCompetente() {
		return competente;
	}

	public void setCompetente(List<Competente> competente) {
		this.competente = competente;
	}

	public Training(Integer idTraining, String numeTraining,
			List<Competente> competente) {
		super();
		this.idTraining = idTraining;
		this.numeTraining = numeTraining;
		this.competente = competente;
	}

	public Training() {
		super();
	}

	
}
