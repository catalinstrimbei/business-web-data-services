package org.app.service.entities;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity 
@Inheritance(strategy = JOINED)
public class Suport implements Serializable{

	@Id @GeneratedValue
	private Integer idSuport;
	private String denSuport;
	
}
