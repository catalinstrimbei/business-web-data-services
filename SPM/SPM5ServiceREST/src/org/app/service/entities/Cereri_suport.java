package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
@Entity
public class Cereri_suport extends Cerere{

Integer idCerereSuport;
	String ProdusSuport;
	Date DataCerereSuport;
	String TipCerereSuport;
	String obsCerereSuport;
	
//	Integer idCerere;
	
	@OneToOne
	private Cerere cerereSup;
	@ManyToOne
	private Clienti clientiSup;

	public Integer getIdCerereSuport() {
		return idCerereSuport;
	}

	public void setIdCerereSuport(Integer idCerereSuport) {
		this.idCerereSuport = idCerereSuport;
	}

	public String getProdusSuport() {
		return ProdusSuport;
	}

	public void setProdusSuport(String produsSuport) {
		ProdusSuport = produsSuport;
	}

	public Date getDataCerereSuport() {
		return DataCerereSuport;
	}

	public void setDataCerereSuport(Date dataCerereSuport) {
		DataCerereSuport = dataCerereSuport;
	}

	public String getTipCerereSuport() {
		return TipCerereSuport;
	}

	public void setTipCerereSuport(String tipCerereSuport) {
		TipCerereSuport = tipCerereSuport;
	}

	public String getObsCerereSuport() {
		return obsCerereSuport;
	}

	public void setObsCerereSuport(String obsCerereSuport) {
		this.obsCerereSuport = obsCerereSuport;
	}

	public Integer getIdCerere() {
		return idCerere;
	}

	public void setIdCerere(Integer idCerere) {
		this.idCerere = idCerere;
	}

	public Cereri_suport(Integer idCerereSuport, String produsSuport,
			Date dataCerereSuport, String tipCerereSuport,
			String obsCerereSuport, Integer idCerere) {
		super();
		this.idCerereSuport = idCerereSuport;
		ProdusSuport = produsSuport;
		DataCerereSuport = dataCerereSuport;
		TipCerereSuport = tipCerereSuport;
		this.obsCerereSuport = obsCerereSuport;
		this.idCerere = idCerere;
	}

	public Cereri_suport() {
		super();
	}

	public Cerere getCerereSup() {
		return cerereSup;
	}

	public void setCerereSup(Cerere cerereSup) {
		this.cerereSup = cerereSup;
	}

	public Clienti getClientiSup() {
		return clientiSup;
	}

	public void setClientiSup(Clienti clientiSup) {
		this.clientiSup = clientiSup;
	}
	
}
