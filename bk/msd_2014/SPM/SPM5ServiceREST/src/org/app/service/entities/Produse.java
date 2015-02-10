package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Produse implements Serializable{

	@Id
	@GeneratedValue 
	Integer idProdus;
	String DenumireProdus;
	String TipProdus;
	String SpecificatiiProdus;
	Date Data;
	@OneToMany(mappedBy = "produse")
	private List<Activitati_suport> activitati = new ArrayList <Activitati_suport>();
	
	public Integer getIdProdus() {
		return idProdus;
	}
	public void setIdProdus(Integer idProdus) {
		this.idProdus = idProdus;
	}
	public String getDenumireProdus() {
		return DenumireProdus;
	}
	public void setDenumireProdus(String denumireProdus) {
		DenumireProdus = denumireProdus;
	}
	public String getTipProdus() {
		return TipProdus;
	}
	public void setTipProdus(String tipProdus) {
		TipProdus = tipProdus;
	}
	public String getSpecificatiiProdus() {
		return SpecificatiiProdus;
	}
	public void setSpecificatiiProdus(String specificatiiProdus) {
		SpecificatiiProdus = specificatiiProdus;
	}
	public Date getData() {
		return Data;
	}
	public void setData(Date data) {
		Data = data;
	}
	public Produse(Integer idProdus, String denumireProdus, String tipProdus,
			String specificatiiProdus, Date data) {
		super();
		this.idProdus = idProdus;
		DenumireProdus = denumireProdus;
		TipProdus = tipProdus;
		SpecificatiiProdus = specificatiiProdus;
		Data = data;
	}
	public Produse() {
		super();
	}
	public List<Activitati_suport> getActivitati() {
		return activitati;
	}
	public void setActivitati(List<Activitati_suport> activitati) {
		this.activitati = activitati;
	}
	@Override
	public String toString() {
		return "Produse [idProdus=" + idProdus + ", DenumireProdus="
				+ DenumireProdus + ", TipProdus=" + TipProdus + "]";
	}
	
	
	
}
