package org.app.service.entities;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity 
@Inheritance(strategy = JOINED)
public class SAAS {

	@Id @GeneratedValue
	private Integer idSAAS;
	private String denSAAS;
	
}
