package org.app.service.entities;

import static javax.persistence.TemporalType.DATE;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class TrainingEchipe {
	
	@Id
	private Integer idTrainingEchipa;
	
	@Temporal(DATE)
	private Date dataInceputTrainingEchipa;
	
	@Temporal(DATE)
	private Date dataSfarsitTrainingEchipa;
	
	@ManyToOne
	private Training training;
	
	@ManyToOne
	private Echipa echipa;

	public Integer getIdTrainingEchipa() {
		return idTrainingEchipa;
	}

	public void setIdTrainingEchipa(Integer idTrainingEchipa) {
		this.idTrainingEchipa = idTrainingEchipa;
	}

	public Date getDataInceputTrainingEchipa() {
		return dataInceputTrainingEchipa;
	}

	public void setDataInceputTrainingEchipa(Date dataInceputTrainingEchipa) {
		this.dataInceputTrainingEchipa = dataInceputTrainingEchipa;
	}

	public Date getDataSfarsitTrainingEchipa() {
		return dataSfarsitTrainingEchipa;
	}

	public void setDataSfarsitTrainingEchipa(Date dataSfarsitTrainingEchipa) {
		this.dataSfarsitTrainingEchipa = dataSfarsitTrainingEchipa;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Echipa getEchipa() {
		return echipa;
	}

	public void setEchipa(Echipa echipa) {
		this.echipa = echipa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idTrainingEchipa == null) ? 0 : idTrainingEchipa.hashCode());
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
		TrainingEchipe other = (TrainingEchipe) obj;
		if (idTrainingEchipa == null) {
			if (other.idTrainingEchipa != null)
				return false;
		} else if (!idTrainingEchipa.equals(other.idTrainingEchipa))
			return false;
		return true;
	}

	public TrainingEchipe(Integer idTrainingEchipa,
			Date dataInceputTrainingEchipa, Date dataSfarsitTrainingEchipa,
			Training training, Echipa echipa) {
		super();
		this.idTrainingEchipa = idTrainingEchipa;
		this.dataInceputTrainingEchipa = dataInceputTrainingEchipa;
		this.dataSfarsitTrainingEchipa = dataSfarsitTrainingEchipa;
		this.training = training;
		this.echipa = echipa;
	}

	public TrainingEchipe() {
		super();
	}

	
}
