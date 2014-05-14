package org.app.service.ejb;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Client;
import org.app.service.entities.Contract;
import org.app.service.entities.PersoanaFizica;
import org.app.service.entities.Produs;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


@Stateless @LocalBean
public abstract class ClientDataServiceEJB extends EntityRepositoryBase<Client> implements ClientDataService, Serializable{
	private static Logger logger = Logger.getLogger (ClientDataServiceEJB.class.getName());
	private EntityRepository<Contract> contracteRepository;
	@Inject
	private PersoanaFizica client;
	@Inject
	private EntityRepository<Produs> produsRepository;
	@PostConstruct
	public void init(){
		contracteRepository = new EntityRepositoryBase<Contract>(this.em, Contract.class);
		produsRepository.setEm(em);
		logger.info("Initialized contractRepository:"+ contracteRepository.size());
		logger.info("Initialized produsRepository:"+ produsRepository.size());
			}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PersoanaFizica createNewClient(Integer idClient){
		PersoanaFizica client=new PersoanaFizica ("Str.Plangerii", "0765228944", "alina@gmail.com","RO325SV555BRDE5555","Ilie", "Alina"); ;
		this.add(client);
		return PersoanaFizica.toDTOAggregate(client);
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
	
}
