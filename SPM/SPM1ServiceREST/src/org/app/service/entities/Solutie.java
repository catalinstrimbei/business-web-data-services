package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Solutie {

	@Id @GeneratedValue
	private Integer idSolutie;
	private String denSolutie;
	
	@OneToMany(mappedBy = "solutie", cascade = ALL, fetch = EAGER)
	private List<Produs> produse = new ArrayList<>();
	
	@OneToMany(mappedBy = "produs", cascade = ALL, fetch = EAGER)
	private List<Serviciu> servicii = new ArrayList<>();
}
