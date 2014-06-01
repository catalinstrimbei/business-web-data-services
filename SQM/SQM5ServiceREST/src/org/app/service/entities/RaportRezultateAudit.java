package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RaportRezultateAudit {
	@Id
	int idrezultat;
	Double gradindeplinire;
	String dificultatiintampinate;
	String riscimplicat;

}
