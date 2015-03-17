/**
 * 
 */
package org.app.service.ejb;

/**
 * @author Alina
 *
 */
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.enterprise.context.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Activitate;
import org.app.service.entities.Client;
import org.app.service.entities.Contract;
import org.app.service.entities.Produs;


import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
@Path("contracte") /*http://localhost:8080/SPM2ServiceREST/contracte*/
@Stateless @LocalBean
public abstract  class ContractDataServiceEJB extends EntityRepositoryBase<Contract> implements Serializable{
	
	
	private static Logger logger = Logger.getLogger (ContractDataServiceEJB.class.getName());
	private EntityRepository<Produs> produsRepository;
	@Inject
	private Contract contract;
	@Inject
	private EntityRepository<Activitate> activitateRepository;
	@PostConstruct
	public void init(){
		produsRepository = new EntityRepositoryBase<Produs>(this.em, Produs.class);
		activitateRepository.setEm(em);
		logger.info("Initialized contractRepository:"+ produsRepository.size());
		logger.info("Initialized produsRepository:"+ activitateRepository.size());
			}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Contract createNewContract(Integer nrContract){
		Contract contract=new Contract ();
		this.add(contract);
		return Contract.toDTOAggregate(contract);
	}
	@Override
	@GET
	@Produces({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
	public Collection<Contract> toCollection(){
		//logger.info("GET Contracte");
		return Contract.toDTOList(super.toCollection());}
		//return super.toCollection();}

	

	public String getMessage(){
		return "ContractDataService is working...";
	}
	/* (non-Javadoc)
	 * @see org.app.service.ejb.ClientDataService#save(org.app.service.entities.Client)
	 */
	//@Override
	public Contract save(Contract contract){
		contract = this.add(contract);
		return contract;
	}
	
	/* (non-Javadoc)
	 * @see org.app.service.ejb.ClientDataService#test()
	 */

	
	//@Override

	@GET @Path("/test")
	public String test(){
		return "Hello from ContractDataServiceEJB.";
	}
}