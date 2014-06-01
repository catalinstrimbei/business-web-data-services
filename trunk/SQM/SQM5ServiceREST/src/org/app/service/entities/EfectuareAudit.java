package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EfectuareAudit {
	@Id
	int etapa;
	String scenariu;

}
