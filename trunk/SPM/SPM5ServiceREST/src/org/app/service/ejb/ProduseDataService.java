package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.service.entities.Produse;

@Remote
public interface ProduseDataService {

	public Produse save(Produse produs);
	public String test();

}