package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import static javax.persistence.InheritanceType.JOINED;

@Entity 
@Inheritance(strategy = JOINED)
public class Optiuni {

	@Id @GeneratedValue
	private Integer idOptiune;
	private String denOptiune;
	private String descriere;
	
}
