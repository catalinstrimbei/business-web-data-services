package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class ProceduraControl {
	
	@Id
	int codproceduracontrol;
	String denumireprocedura;
	String obiectiv;
	int limitainferioara;
	int limitasuperioara;

}
