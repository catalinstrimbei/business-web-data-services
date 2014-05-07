package org.app.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;


@Entity 
public class Produs {
	@Id @GeneratedValue
	private Integer idProdus;
	private String denProdus;
	private String categorieProdus;
	
	
	//@Transient
	//private ProductManager productManager;
	
	@OneToMany(mappedBy = "produs", cascade = ALL, fetch = EAGER)
	private List<Versiune> versiuni = new ArrayList<>();
	
	@OneToOne(fetch = EAGER, cascade = ALL, mappedBy = "produs")
	private List<Versiune> versiuneCurenta = new ArrayList<>();
	
	@OneToMany(cascade = ALL, fetch = EAGER, mappedBy = "produs")
	private List<Editie> editii = new ArrayList<>();
	
	
	

}
