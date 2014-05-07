package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;


@Entity 
public class Produs implements Serializable{
	@Id @GeneratedValue
	private Integer idProdus;
	private String denProdus;
	private String categorieProdus;
	
	@OneToMany(mappedBy = "produs", cascade = ALL, fetch = EAGER)
	private List<Versiune> versiuni = new ArrayList<>();
	
	@OneToOne(fetch = EAGER, cascade = ALL, mappedBy = "produs")
	private List<Versiune> versiuneCurenta = new ArrayList<>();
	
	@OneToMany(cascade = ALL, fetch = EAGER, mappedBy = "produs")
	private List<Editie> editii = new ArrayList<>();
	
	//@Transient
	//private ProductManager productManager;
	
	public List<Versiune> getVersiuni() {
		return versiuni;
	}

	public void setVersiuni(List<Versiune> versiuni) {
		this.versiuni = versiuni;
	}

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
	
	return produsDTO;
	}

	public Produs toDTO() {
		return null;
	}

	public static Produs[] toDTOList(Collection<Produs> produse){
		List<Produs> produsDTOList = new ArrayList<>();
		for (Produs p: produse){
			produsDTOList.add(p.toDTO());
		}
		return produsDTOList.toArray(new Produs[0]);
	}

	public String getDenProdus() {
		return denProdus;
	}

	public void setDenProdus(String denProdus) {
		this.denProdus = denProdus;
	}
	
	
	

}
