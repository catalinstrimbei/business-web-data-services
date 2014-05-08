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

@Entity
public class Solutie implements Serializable{

	@Id @GeneratedValue
	private Integer idSolutie;
	private String denSolutie;
	
	@OneToMany(cascade = ALL, fetch = EAGER)
	private List<Produs> produse = new ArrayList<>();
	
	@OneToMany(cascade = ALL, fetch = FetchType.LAZY)
	private List<Serviciu> servicii = new ArrayList<>();
}
