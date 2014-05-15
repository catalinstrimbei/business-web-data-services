package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@Entity
public class Solutie implements Serializable{

	@Id @GeneratedValue
	private Integer idSolutie;
	private String denSolutie;
	
	@OneToMany(cascade = ALL, fetch = EAGER)
	private List<Produs> produse = new ArrayList<>();
	
	@OneToMany(cascade = ALL, fetch = FetchType.LAZY)
	private List<Serviciu> servicii = new ArrayList<>();

	@XmlElement
	public Integer getIdSolutie() {
		return idSolutie;
	}

	public void setIdSolutie(Integer idSolutie) {
		this.idSolutie = idSolutie;
	}

	public String getDenSolutie() {
		return denSolutie;
	}

	public void setDenSolutie(String denSolutie) {
		this.denSolutie = denSolutie;
	}

	@XmlElementWrapper(name="produse") @XmlElement(name="produs")
	public List<Produs> getProduse() {
		return produse;
	}

	public void setProduse(List<Produs> produse) {
		this.produse = produse;
	}

	@XmlElementWrapper(name="servicii") @XmlElement(name="serviciu")
	public List<Serviciu> getServicii() {
		return servicii;
	}

	public void setServicii(List<Serviciu> servicii) {
		this.servicii = servicii;
	}
	
	
}
