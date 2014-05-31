package org.app.service.ejb;

import java.util.Arrays;
import java.util.Collection;

import javax.ejb.Remote;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.app.service.entities.Client;


@Remote
public interface ClientDataService {

	public Client save(Client client);

	public String test();

	public String getMessage();

	public Client add(Client client);

	public Client getById(int i);
	

	//public Client createNewClient(int i);

	public Client getByID(int i);

	public void remove(Client client);
	public String getMessag();
	public Client createNewClient(Integer id);

	Client createNewClientFactory(int i);

	public Collection<Client> toCollection();

	//Client createNewClient(Integer id);

	

	
}