package org.app.service.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
@Entity
public class Cerere_training extends Cerere
implements Serializable{

 Integer idCerereTraining;
	Date dataCerereTraining;
	Date PerioadaTraining;
	Integer NrPersoane;
	String ProdusTraining;
	String LocatieTraining;
	
	@OneToOne
	private Cerere cerereTr;
	@ManyToOne
	private Clienti clientiTr;
	
//	Integer idCerere;

	public Integer getIdCerereTraining() {
		return idCerereTraining;
	}

	public void setIdCerereTraining(Integer idCerereTraining) {
		this.idCerereTraining = idCerereTraining;
	}

	public Date getDataCerereTraining() {
		return dataCerereTraining;
	}

	public void setDataCerereTraining(Date dataCerereTraining) {
		this.dataCerereTraining = dataCerereTraining;
	}

	public Date getPerioadaTraining() {
		return PerioadaTraining;
	}

	public void setPerioadaTraining(Date perioadaTraining) {
		PerioadaTraining = perioadaTraining;
	}

	public Integer getNrPersoane() {
		return NrPersoane;
	}

	public void setNrPersoane(Integer nrPersoane) {
		NrPersoane = nrPersoane;
	}

	public String getProdusTraining() {
		return ProdusTraining;
	}

	public void setProdusTraining(String produsTraining) {
		ProdusTraining = produsTraining;
	}

	public String getLocatieTraining() {
		return LocatieTraining;
	}

	public void setLocatieTraining(String locatieTraining) {
		LocatieTraining = locatieTraining;
	}

//	public Integer getIdCerere() {
//		return idCerere;
//	}
//
//	public void setIdCerere(Integer idCerere) {
//		this.idCerere = idCerere;
//	}

	public Cerere_training(Integer idCerereTraining, Date dataCerereTraining,
			Date perioadaTraining, Integer nrPersoane, String produsTraining,
			String locatieTraining, Integer idCerere) {
		super();
		this.idCerereTraining = idCerereTraining;
		this.dataCerereTraining = dataCerereTraining;
		PerioadaTraining = perioadaTraining;
		NrPersoane = nrPersoane;
		ProdusTraining = produsTraining;
		LocatieTraining = locatieTraining;
		this.idCerere = idCerere;
	}

	public Cerere_training() {
		super();
	}

	public Cerere getCerereTr() {
		return cerereTr;
	}

	public void setCerereTr(Cerere cerereTr) {
		this.cerereTr = cerereTr;
	}

	public Clienti getClientiTr() {
		return clientiTr;
	}

	public void setClientiTr(Clienti clientiTr) {
		this.clientiTr = clientiTr;
	}
	
}
