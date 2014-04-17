package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cerere {
@Id
Integer idCerere;
	Date dataCerere;
	String obsCerere;
	
	Integer idRepartizare;
	
	@OneToOne(mappedBy="cerere")
	private Repartizare_cerere RepCerere;

	public Integer getIdCerere() {
		return idCerere;
	}

	public void setIdCerere(Integer idCerere) {
		this.idCerere = idCerere;
	}

	public Date getDataCerere() {
		return dataCerere;
	}

	public void setDataCerere(Date dataCerere) {
		this.dataCerere = dataCerere;
	}

	public String getObsCerere() {
		return obsCerere;
	}

	public void setObsCerere(String obsCerere) {
		this.obsCerere = obsCerere;
	}

	public Integer getIdRepartizare() {
		return idRepartizare;
	}

	public void setIdRepartizare(Integer idRepartizare) {
		this.idRepartizare = idRepartizare;
	}

	public Cerere(Integer idCerere, Date dataCerere, String obsCerere,
			Integer idRepartizare) {
		super();
		this.idCerere = idCerere;
		this.dataCerere = dataCerere;
		this.obsCerere = obsCerere;
		this.idRepartizare = idRepartizare;
	}

	public Cerere() {
		super();
	}

	public Repartizare_cerere getRepCerere() {
		return RepCerere;
	}

	public void setRepCerere(Repartizare_cerere repCerere) {
		RepCerere = repCerere;
	}
	
	
}
