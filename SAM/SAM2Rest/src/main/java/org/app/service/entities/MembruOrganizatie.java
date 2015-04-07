package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MembruOrganizatie {
	
	@Id
	private Integer idMembru;
	
	private String numePrenumeMembru;
	private String telefonMembru;
	private String emailMembru;
	private String adresaMembru;
	
	@OneToMany(cascade=ALL,fetch=EAGER, orphanRemoval = false, mappedBy = "membruorg")
	private List<TrainingMembri> trainingMembriM = new ArrayList<>();
	
	@OneToMany(cascade=ALL,fetch=EAGER, orphanRemoval = false)
	private List<Competente> competente = new ArrayList<>();
	
	@OneToMany(cascade=ALL,fetch=EAGER, orphanRemoval = false, mappedBy = "membruorg")
	private List<Experienta> experiente = new ArrayList<>();

	@OneToMany(cascade=ALL,fetch=EAGER, orphanRemoval = false, mappedBy = "membruorg")
	private List<MembruEchipa> membruEchipaM = new ArrayList<>();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idMembru == null) ? 0 : idMembru.hashCode());
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
		MembruOrganizatie other = (MembruOrganizatie) obj;
		if (idMembru == null) {
			if (other.idMembru != null)
				return false;
		} else if (!idMembru.equals(other.idMembru))
			return false;
		return true;
	}

	public Integer getIdMembru() {
		return idMembru;
	}

	public void setIdMembru(Integer idMembru) {
		this.idMembru = idMembru;
	}

	public String getNumePrenumeMembru() {
		return numePrenumeMembru;
	}

	public void setNumePrenumeMembru(String numePrenumeMembru) {
		this.numePrenumeMembru = numePrenumeMembru;
	}

	public String getTelefonMembru() {
		return telefonMembru;
	}

	public void setTelefonMembru(String telefonMembru) {
		this.telefonMembru = telefonMembru;
	}

	public String getEmailMembru() {
		return emailMembru;
	}

	public void setEmailMembru(String emailMembru) {
		this.emailMembru = emailMembru;
	}

	public String getAdresaMembru() {
		return adresaMembru;
	}

	public void setAdresaMembru(String adresaMembru) {
		this.adresaMembru = adresaMembru;
	}

	public List<Competente> getCompetente() {
		return competente;
	}

	public void setCompetente(List<Competente> competente) {
		this.competente = competente;
	}

	public List<Experienta> getExperiente() {
		return experiente;
	}

	public void setExperiente(List<Experienta> experiente) {
		this.experiente = experiente;
	}

	public MembruOrganizatie(Integer idMembru, String numePrenumeMembru,
			String telefonMembru, String emailMembru, String adresaMembru,
			List<Competente> competente, List<Experienta> experiente) {
		super();
		this.idMembru = idMembru;
		this.numePrenumeMembru = numePrenumeMembru;
		this.telefonMembru = telefonMembru;
		this.emailMembru = emailMembru;
		this.adresaMembru = adresaMembru;
		this.competente = competente;
		this.experiente = experiente;
	}

	public MembruOrganizatie() {
		super();
	}

}
