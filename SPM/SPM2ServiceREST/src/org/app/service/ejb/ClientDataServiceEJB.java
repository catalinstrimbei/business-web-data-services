package org.app.service.ejb;

import javax.ejb.Stateless;

import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Client;


@Stateless
public class ClientDataServiceEJB extends EntityRepositoryBase<Client> implements ClientDataService{

	
	/* (non-Javadoc)
	 * @see org.app.service.ejb.ClientDataService#save(org.app.service.entities.Client)
	 */
	@Override
	public Client save(Client client){
		client = this.add(client);
		return client;
	}
	
	/* (non-Javadoc)
	 * @see org.app.service.ejb.ClientDataService#test()
	 */
	@Override
	public String test(){
		return "Hello from ClientDataServiceEJB.";
	}
	
}
