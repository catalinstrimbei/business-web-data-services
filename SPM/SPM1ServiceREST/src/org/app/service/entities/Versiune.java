package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity 
public class Versiune implements Serializable{
	
	public Versiune toDTO(){
		return new Versiune (idVersiune, denVersiune, dataLansare, produs.toDTO());
	}
	
	public static List<Versiune> toDTOList(List<Versiune> versiuni){
		List<Versiune> versiuneDTOList = new ArrayList<>();
		for (Versiune v: versiuni){
			versiuneDTOList.add(v.toDTO());
		}
		return versiuneDTOList;
	}
	
	@Id @GeneratedValue
	private Integer idVersiune;
	private String denVersiune;
	public Versiune(Integer idVersiune, String denVersiune, Date dataLansare,
			Produs produs) {
		super();
		this.idVersiune = idVersiune;
		this.denVersiune = denVersiune;
		this.dataLansare = dataLansare;
		this.produs = produs;
	}


	@Temporal(TemporalType.DATE)
	private Date dataLansare;
	private Integer nrVersiuni;
	private String descriere;
	
	@ManyToOne
	private Produs produs;
	
	@OneToMany(cascade = ALL, fetch = FetchType.EAGER)
	private List<Optiuni> optiuni=new ArrayList<>();
	
	//getteri si setteri
	
	public Integer getIdVersiune() {
		return idVersiune;
	}

	public void setIdVersiune(Integer idVersiune) {
		this.idVersiune = idVersiune;
	}

	public Date getDataLansare() {
		return dataLansare;
	}

	public void setDataLansare(Date dataLansare) {
		this.dataLansare = dataLansare;
	}

	public Integer getNrVersiuni() {
		return nrVersiuni;
	}

	public void setNrVersiuni(Integer nrVersiuni) {
		this.nrVersiuni = nrVersiuni;
	}

	public String getDescriere() {
		return descriere;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	public Produs getProdus() {
		return produs;
	}

	public void setProdus(Produs produs) {
		this.produs = produs;
	}

	public List<Optiuni> getOptiuni() {
		return optiuni;
	}

	public void setOptiuni(List<Optiuni> optiuni) {
		this.optiuni = optiuni;
	}

	public Versiune(Integer idVersiune, Date dataLansare, Produs produs) {
		super();
		this.idVersiune = idVersiune;
		this.dataLansare = dataLansare;
		this.produs = produs;
	}

	
	public Versiune() {
		super();
	}

	/*
	public Versiune(Object object, String string, Date date, Produs produs2) {
		// TODO Auto-generated constructor stub
	}
*/
	

}
