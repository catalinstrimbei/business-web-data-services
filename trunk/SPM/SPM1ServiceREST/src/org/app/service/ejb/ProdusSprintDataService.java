package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Produs;

@Remote
public interface ProdusSprintDataService extends EntityRepository<Produs>{
	public String getMessage();
	public Produs createNewProdus(Integer id);
	
}
