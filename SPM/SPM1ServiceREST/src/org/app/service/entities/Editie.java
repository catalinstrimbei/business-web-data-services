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
	
	
}
