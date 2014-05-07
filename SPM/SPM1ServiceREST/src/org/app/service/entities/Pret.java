package org.app.service.entities;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity 
//???????????????????????????????????????????
@Inheritance(strategy = JOINED)
public class Pret {

	@Id @GeneratedValue
	private Integer idPret;
	private Integer nrUtilizatori;
	private Double pretPerUtilizator;
	private Double valoare;
}
