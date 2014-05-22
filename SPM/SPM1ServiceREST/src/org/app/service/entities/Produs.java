package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.service.rest.AtomLink;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@XmlRootElement(name="produs")
@XmlAccessorType(XmlAccessType.NONE)
@Entity 
public class Produs implements Serializable{
	@Id //@GeneratedValue
	private Integer idProdus;
	private String denProdus;
	private String categorieProdus;
	
	@OneToMany(mappedBy = "produs", cascade = ALL, fetch = FetchType.LAZY)
	private List<Versiune> versiuni = new ArrayList<>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = ALL)
	private Versiune versiuneCurenta;
	
	@OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "produs")
	private List<Editie> editii = new ArrayList<>();
	
	//@Transient
	//private ProductManager productManager;
	
	@XmlElementWrapper(name="versiuni") @XmlElement(name="versiune")
	public List<Versiune> getVersiuni() {
		return versiuni;
	}

	public void setVersiuni(List<Versiune> versiuni) {
		this.versiuni = versiuni;
	}

	@XmlElement
	public Integer getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(Integer idProdus) {
		this.idProdus = idProdus;
	}


	public Produs(Integer idProdus, String denProdus, String categorieProdus) {
		super();
		this.idProdus = idProdus;
		this.denProdus = denProdus;
		this.categorieProdus = categorieProdus;
	}

	@XmlElementWrapper(name="editii") @XmlElement(name="editie")
	public List<Editie> getEditii() {
		return editii;
	}

	public void setEditii(List<Editie> editii) {
		this.editii = editii;
	}

	public static Produs toDTOAggregate(Produs produs) {
		if (produs == null)
		return null;
		
	Produs produsDTO = produs.toDTO();
	List<Versiune> versiuneDTO = Versiune.toDTOList(produs.getVersiuni());
	produsDTO.setVersiuni(versiuneDTO);
	
	// ptr editii
	
	return produsDTO;
	}

	public Produs toDTO() {
		return new Produs(this.idProdus, this.denProdus, this.categorieProdus);
	}

	
	public static Produs[] toDTOList(Collection<Produs> produse){
		List<Produs> produsDTOList = new ArrayList<>();
		for (Produs p: produse){
			produsDTOList.add(p.toDTO());
		}
		return produsDTOList.toArray(new Produs[0]);
	}

	@XmlElement
	public String getDenProdus() {
		return denProdus;
	}

	public void setDenProdus(String denProdus) {
		this.denProdus = denProdus;
	}

	public Produs() {
		super();
	}
	
	public static String BASE_URL = "http://localhost:8080/SPM1ServiceREST/produse/";
	@XmlElement(name= "link")
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL + this.getIdProdus();
			return new AtomLink(restUrl, "get-produs");
			
	}	

}
