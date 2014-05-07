package org.app.scrum.services;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Produs;

public interface ProdusSprintDataService extends EntityRepository<Produs>{
	public String getMessage();
	public Produs createNewProdus(Integer id);
}
