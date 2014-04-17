package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class Rapoarte_incidente  extends Cerere{

Integer idRaport;
	Date dataRaport;
	String obsIncident;
	String PrioritateIncident;
	String RaportProdus;
//	Integer idCerere;
	
	@OneToOne
	private Cerere cerereRap;
	@ManyToOne
	private Clienti clientiRap;

	public Integer getIdRaport() {
		return idRaport;
	}

	public void setIdRaport(Integer idRaport) {
		this.idRaport = idRaport;
	}

	public Date getDataRaport() {
		return dataRaport;
	}

	public void setDataRaport(Date dataRaport) {
		this.dataRaport = dataRaport;
	}

	public String getObsIncident() {
		return obsIncident;
	}

	public void setObsIncident(String obsIncident) {
		this.obsIncident = obsIncident;
	}

	public String getPrioritateIncident() {
		return PrioritateIncident;
	}

	public void setPrioritateIncident(String prioritateIncident) {
		PrioritateIncident = prioritateIncident;
	}

	public String getRaportProdus() {
		return RaportProdus;
	}

	public void setRaportProdus(String raportProdus) {
		RaportProdus = raportProdus;
	}

	public Integer getIdCerere() {
		return idCerere;
	}

	public void setIdCerere(Integer idCerere) {
		this.idCerere = idCerere;
	}

	public Rapoarte_incidente(Integer idRaport, Date dataRaport,
			String obsIncident, String prioritateIncident, String raportProdus,
			Integer idCerere) {
		super();
		this.idRaport = idRaport;
		this.dataRaport = dataRaport;
		this.obsIncident = obsIncident;
		PrioritateIncident = prioritateIncident;
		RaportProdus = raportProdus;
		this.idCerere = idCerere;
	}

	public Rapoarte_incidente() {
		super();
	}

	public Cerere getCerereRap() {
		return cerereRap;
	}

	public void setCerereRap(Cerere cerereRap) {
		this.cerereRap = cerereRap;
	}

	public Clienti getClientiRap() {
		return clientiRap;
	}

	public void setClientiRap(Clienti clientiRap) {
		this.clientiRap = clientiRap;
	}
	
}
