package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.service.entities.Client;
import org.app.service.entities.PersoanaFizica;
import org.app.service.entities.PersoanaJuridica;

@Remote
public interface ClientDataService {

	public Client save(Client client);

	public String test();

	public String getMessage();

	public PersoanaFizica add(PersoanaFizica client);

	public PersoanaFizica getById(int i);
	public PersoanaJuridica getByIdJ(int i);

	public PersoanaJuridica add(PersoanaJuridica clientJ);

	public PersoanaFizica createNewClient(int i);

	public PersoanaFizica getByID(int i);

	public void remove(PersoanaFizica client);

}