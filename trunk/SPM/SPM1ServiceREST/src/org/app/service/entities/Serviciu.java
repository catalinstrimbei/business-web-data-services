package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity 
public class Serviciu implements Serializable{

	@Id @GeneratedValue
	private Integer idServiciu;
	private String denServiciu;
	private String categorie;
	
	@OneToMany(mappedBy = "serviciu", cascade = ALL, fetch = EAGER)
	private List<SAAS> saasuri = new ArrayList<>();
	
	@OneToMany(mappedBy = "serviciu", cascade = ALL, fetch = EAGER)
	private List<Suport> suporturi = new ArrayList<>();
}
