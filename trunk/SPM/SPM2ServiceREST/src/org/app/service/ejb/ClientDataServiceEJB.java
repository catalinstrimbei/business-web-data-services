package org.app.service.ejb;

import java.awt.PageAttributes.MediaType;
import java.awt.PageAttributes.MediaType.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Client;
import org.app.service.entities.Contract;
import org.app.service.entities.PersoanaFizica;
import org.app.service.entities.PersoanaJuridica;
import org.app.service.entities.Produs;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Produces;

@Path("client")/*http://localhost:8080/ScrumREST/client*/
@Stateless @LocalBean
public class ClientDataServiceEJB extends EntityRepositoryBase<Client> implements ClientDataService, Serializable{
	private static Logger logger = Logger.getLogger (ClientDataServiceEJB.class.getName());
	private EntityRepository<Contract> contracteRepository;
//	@Inject
//	private PersoanaFizica client;
//	@Inject
	private EntityRepository<Produs> produsRepository;
	@PostConstruct
	public void init(){
		contracteRepository = new EntityRepositoryBase<Contract>(this.em, Contract.class);
//		produsRepository.setEm(em);
		produsRepository = new EntityRepositoryBase<Produs>(this.em, Produs.class);
		logger.info("Initialized contractRepository:"+ contracteRepository.size());
		logger.info("Initialized produsRepository:"+ produsRepository.size());
			}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PersoanaFizica createNewClient(Integer idClient){
		PersoanaFizica client=new PersoanaFizica ("Str.Plangerii", "0765228944", "alina@gmail.com","RO325SV555BRDE5555","Ilie", "Alina"); ;
		this.add(client);
		return PersoanaFizica.toDTOAggregate(client);
	}
	
	@Override
	@GET
	@Produces/*({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})*/
	public Collection<Client> toCollection(){
		return PersoanaFizica.toDTOs(super.toCollection());
		
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PersoanaJuridica createNewPersoanaJuridica(Integer idClient){
		PersoanaJuridica client=new PersoanaJuridica ("Str.Palatului", "0765228944", "marina@gmail.com","RO325SV555BRDE5555","Marina SRL",2356412,"J40/8118/2002"); ;
		this.add(client);
		return PersoanaJuridica.toDTOAggregate(client);
	}
	
	
	public String getMessage(){
		return "ClientDataService is working...";
	}
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
	@Override
	public PersoanaFizica add(PersoanaFizica client) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PersoanaFizica getById(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PersoanaJuridica getByIdJ(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PersoanaJuridica add(PersoanaJuridica clientJ) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PersoanaFizica createNewClient(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PersoanaFizica getByID(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void remove(PersoanaFizica client) {
		// TODO Auto-generated method stub
		
	}
	
}
