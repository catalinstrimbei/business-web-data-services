package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.service.entities.Client;

@Remote
public interface ClientDataService {

	public Client save(Client client);

	public String test();

}