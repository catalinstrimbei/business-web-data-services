package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity 
public class Editie {
	@Id @GeneratedValue
	private Integer idEditie;
	private String denEditie;
	private String descriere;

	@OneToMany(cascade = ALL, fetch = EAGER)
	private List<Optiuni> optiuni=new ArrayList<>();
	
	///////////??????????????????????????????????????????????????????????????????
	@OneToOne(fetch = EAGER, cascade = ALL, mappedBy = "editie")
	private List<Pret> preturi=new ArrayList<>();
}
