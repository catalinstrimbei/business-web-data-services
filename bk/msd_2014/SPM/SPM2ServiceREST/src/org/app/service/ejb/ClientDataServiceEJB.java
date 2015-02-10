package org.app.service.ejb;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.enterprise.context.*;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Client;
import org.app.service.entities.ClientFactory;
import org.app.service.entities.Contract;
import org.app.service.entities.Produs;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import java.io.BufferedReader;



//@SuppressWarnings("serial")
@Path("clienti") /*http://localhost:8080/SPM2ServiceREST/clienti*/
@Stateless @LocalBean
public class ClientDataServiceEJB extends EntityRepositoryBase<Client> implements Serializable{
	
	
	private static Logger logger = Logger.getLogger (ClientDataServiceEJB.class.getName());
	private EntityRepository<Contract> contractRepository;
	@Inject
	private ClientFactory clientFactory;
	@Inject
	private EntityRepository<Produs> produsRepository;
	@PostConstruct
	public void init(){
		contractRepository = new EntityRepositoryBase<Contract>(this.em, Contract.class);
		produsRepository.setEm(em);
		produsRepository = new EntityRepositoryBase<Produs>(this.em, Produs.class);
		logger.info("Initialized contractRepository:"+ contractRepository.size());
		logger.info("Initialized produsRepository:"+ produsRepository.size());
			}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Client createNewClient(Integer id){
		Client client= clientFactory.buildClient(id, "Mihai Mihai", 3);
		this.add(client);
		return Client.toDTOAggregate(client);
	}
	public String getMessage(){
		return "ClientDataService is working...";
	}
	@GET @Path("/{id}")/*SPM2/clienti/{id}	REST-resource:client-entity*/
	@Produces({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
	public Client getById(@PathParam("id") Integer id) {
		Client client=super.getById(id);
		return Client.toDTOAggregate(client);
	}
	@Override
	@GET /*SPM2/clienti	REST-resource:clienti-collection*/
	@Produces({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
	public Collection<Client> toCollection(){
		//logger.info("GET Clienti");
		Collection<Client> clienti=this.toCollection();
		Client[] clientArray= Client.toDTOList(clienti);
		return Arrays.asList(clientArray);
		//return Client.toDTOs(super.toCollection());
		//return super.toCollection();
	
	}
	@Override
	public Client add(Client client) {
		client= this.add(client);
		return Client.toDTOAggregate(client);
	}
	

	/* (non-Javadoc)
	 * @see org.app.service.ejb.ClientDataService#save(org.app.service.entities.Client)
	 */
	//@Override
	public Client save(Client client){
		client = this.add(client);
		return client;
	}
	
	/* (non-Javadoc)
	 * @see org.app.service.ejb.ClientDataService#test()
	 */
	@GET @Path("/test")
	//@Override
	public String test(){
		return "Hello from ClientDataServiceEJB.";
	}
	
	
	
	
	/*@Override
	public Client createNewClient(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}*/
	//@Override
	public Client getById(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	/*@Override
	public Client remove(Client client) {
		// TODO Auto-generated method stub
		return null;
	}*/
}
