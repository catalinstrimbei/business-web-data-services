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

import org.app.service.rest.AtomLink;

@Entity 
public class Serviciu implements Serializable{

	@Id @GeneratedValue
	private Integer idServiciu;
	private String denServiciu;
	private String categorie;
	
	@OneToMany(cascade = ALL, fetch = FetchType.EAGER)
	private List<SAAS> saasuri = new ArrayList<>();
	
	@OneToMany(cascade = ALL, fetch = FetchType.LAZY)
	private List<Suport> suporturi = new ArrayList<>();

	@XmlElement
	public Integer getIdServiciu() {
		return idServiciu;
	}

	public void setIdServiciu(Integer idServiciu) {
		this.idServiciu = idServiciu;
	}

	@XmlElement
	public String getDenServiciu() {
		return denServiciu;
	}

	public void setDenServiciu(String denServiciu) {
		this.denServiciu = denServiciu;
	}

	@XmlElement
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@XmlElementWrapper(name="saasuri") @XmlElement(name="saas")
	public List<SAAS> getSaasuri() {
		return saasuri;
	}

	public void setSaasuri(List<SAAS> saasuri) {
		this.saasuri = saasuri;
	}

	@XmlElementWrapper(name="suporturi") @XmlElement(name="suport")
	public List<Suport> getSuporturi() {
		return suporturi;
	}

	public void setSuporturi(List<Suport> suporturi) {
		this.suporturi = suporturi;
	}
	

	public static String BASE_URL = "http://localhost:8080/SPM1ServiceREST/servicii/";
	@XmlElement(name= "link")
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL + this.getIdServiciu();
			return new AtomLink(restUrl, "get-serviciu");
			
	}
}
