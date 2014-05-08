package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;



import org.app.service.entities.Produse;

@Remote
public interface ProduseDataService {

	public Produse save(Produse produs);
	public String test();
	public Collection<Produse> toCollection();
	//CREATE and Update
	public Produse add(Produse produs);
	//READ
	public Produse getById(Integer Id);
	//DELETE
	public Produse remove(Produse produs);
}