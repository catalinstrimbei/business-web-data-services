package org.app.service.entities;

import static javax.persistence.TemporalType.DATE;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class TrainingMembri {
	
	@Id
	private Integer idTrainingMembri;
	
	@Temporal(DATE)
	private Date dataInceputTrainingMembri;
	
	@Temporal(DATE)
	private Date dataSfarsitTrainingMembri;

	@ManyToOne
	private Training training;
	
	@ManyToOne
	private MembruOrganizatie membruorg;
	
	public Integer getIdTrainingMembri() {
		return idTrainingMembri;
	}
	public void setIdTrainingMembri(Integer idTrainingMembri) {
		this.idTrainingMembri = idTrainingMembri;
	}
	public Date getDataInceputTrainingMembri() {
		return dataInceputTrainingMembri;
	}
	public void setDataInceputTrainingMembri(Date dataInceputTrainingMembri) {
		this.dataInceputTrainingMembri = dataInceputTrainingMembri;
	}
	public Date getDataSfarsitTrainingMembri() {
		return dataSfarsitTrainingMembri;
	}
	public void setDataSfarsitTrainingMembri(Date dataSfarsitTrainingMembri) {
		this.dataSfarsitTrainingMembri = dataSfarsitTrainingMembri;
	}
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	public MembruOrganizatie getMembruorg() {
		return membruorg;
	}
	public void setMembruorg(MembruOrganizatie membruorg) {
		this.membruorg = membruorg;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idTrainingMembri == null) ? 0 : idTrainingMembri.hashCode());
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
		TrainingMembri other = (TrainingMembri) obj;
		if (idTrainingMembri == null) {
			if (other.idTrainingMembri != null)
				return false;
		} else if (!idTrainingMembri.equals(other.idTrainingMembri))
			return false;
		return true;
	}
	public TrainingMembri(Integer idTrainingMembri,
			Date dataInceputTrainingMembri, Date dataSfarsitTrainingMembri,
			Training training, MembruOrganizatie membruorg) {
		super();
		this.idTrainingMembri = idTrainingMembri;
		this.dataInceputTrainingMembri = dataInceputTrainingMembri;
		this.dataSfarsitTrainingMembri = dataSfarsitTrainingMembri;
		this.training = training;
		this.membruorg = membruorg;
	}
	public TrainingMembri() {
		super();
	}
	
	
}
