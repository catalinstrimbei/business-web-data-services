package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Id;

@Entity
@DiscriminatorValue(value="codactivitate")
public class Activitate {
	@Id
	int codactivitate;
	String numeactivitate;
	String descriere;
	Double durataestimata;
	Double durataramasa;
	String status;
	String categorieactivitate;
	
	
	

}
