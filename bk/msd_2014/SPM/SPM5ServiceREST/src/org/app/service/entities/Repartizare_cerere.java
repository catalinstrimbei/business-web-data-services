package org.app.service.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Repartizare_cerere implements Serializable{
@Id
Integer idRepartizare;
	Date DataRepartizare;
	
//	Integer idResponsabil;

	@ManyToOne
	private Responsabili Resp;
	
	@OneToOne
	private Cerere cerere;
	
	public Integer getIdRepartizare() {
		return idRepartizare;
	}

	public void setIdRepartizare(Integer idRepartizare) {
		this.idRepartizare = idRepartizare;
	}

	public Date getDataRepartizare() {
		return DataRepartizare;
	}

	public void setDataRepartizare(Date dataRepartizare) {
		DataRepartizare = dataRepartizare;
	}

//	public Integer getIdResponsabil() {
//		return idResponsabil;
//	}
//
//	public void setIdResponsabil(Integer idResponsabil) {
//		this.idResponsabil = idResponsabil;
//	}
//
//	public Repartizare_cerere(Integer idRepartizare, Date dataRepartizare,
//			Integer idResponsabil) {
//		super();
//		this.idRepartizare = idRepartizare;
//		DataRepartizare = dataRepartizare;
//		this.idResponsabil = idResponsabil;
//	}

	public Repartizare_cerere() {
		super();
	}

	public Responsabili getResp() {
		return Resp;
	}

	public void setResp(Responsabili resp) {
		Resp = resp;
	}
	
}
