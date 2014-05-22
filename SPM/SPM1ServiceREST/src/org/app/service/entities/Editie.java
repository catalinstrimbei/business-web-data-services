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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.service.rest.AtomLink;

@XmlRootElement(name="editie")
@XmlAccessorType(XmlAccessType.NONE)
@Entity 
public class Editie implements Serializable{
	@Id @GeneratedValue
	private Integer idEditie;
	private String denEditie;
	private String descriere;
	
	@ManyToOne
	private Produs produs;

	@OneToMany(cascade = ALL, fetch = FetchType.LAZY)
	private List<Optiuni> optiuni=new ArrayList<>();
	
	///////////??????????????????????????????????????????????????????????????????
	@OneToMany(fetch = FetchType.LAZY, cascade = ALL)
	private List<Pret> preturi=new ArrayList<>();
	
	public void add(){
		this.idEditie=idEditie;
		this.denEditie=denEditie;
		this.produs=produs;
	}

	public Editie(Integer idEditie, String denEditie, Produs produs) {
		super();
		this.idEditie = idEditie;
		this.denEditie = denEditie;
		this.produs = produs;
	}

	public Editie() {
		super();
	}
	public static String BASE_URL = Produs.BASE_URL;
	@XmlElement(name= "link")
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL + this.getProdus().getIdProdus() + "/editii/" + this.getIdEditie();
			return new AtomLink(restUrl, "get-editie");
	}
	@XmlElement
	public Integer getIdEditie() {
		return idEditie;
	}

	public void setIdEditie(Integer idEditie) {
		this.idEditie = idEditie;
	}

	@XmlElement
	public String getDenEditie() {
		return denEditie;
	}

	public void setDenEditie(String denEditie) {
		this.denEditie = denEditie;
	}

	@XmlElement
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
	@XmlElementWrapper(name="optiuni") @XmlElement(name="optiune")
	public List<Optiuni> getOptiuni() {
		return optiuni;
	}

	public void setOptiuni(List<Optiuni> optiuni) {
		this.optiuni = optiuni;
	}
	@XmlElementWrapper(name="preturi") @XmlElement(name="pret")
	public List<Pret> getPreturi() {
		return preturi;
	}

	public void setPreturi(List<Pret> preturi) {
		this.preturi = preturi;
	}
	
	
	
}
