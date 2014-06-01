package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Membru implements Serializable{

	@Id@GeneratedValue
	int idmembru;
	String nume;
	String prenume;
	String rol;
	
	
}
