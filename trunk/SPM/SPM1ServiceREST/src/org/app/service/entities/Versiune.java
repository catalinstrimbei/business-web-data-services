package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity 
public class Versiune {
	
	@Id @GeneratedValue
	private Integer idVersiune;
	@Temporal(TemporalType.DATE)
	private Date dataLansare;
	private Integer nrVersiuni;
	private String descriere;
	
	@ManyToOne
	private Produs produs;
	
	@OneToMany(cascade = ALL, fetch = EAGER)
	private List<Optiuni> optiuni=new ArrayList<>();

}
