package org.app.service.ejb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.scrum.produs.ProdusFactory;
import org.app.service.entities.Editie;
import org.app.service.entities.Produs;
import org.app.service.entities.Versiune;
import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;
@Path("produse") /*  http://localhost:8080/ScrumREST/produse    */
@Stateless @LocalBean
public class ProdusSprintDataServicesRest extends EntityRepositoryBase<Produs> implements ProdusSprintDataService, Serializable {

	
	private static Logger logger = Logger.getLogger(ProdusSprintDataServicesRest.class.getName());
	
	private EntityRepository<Versiune> versiuneRepository;
	
	@Inject
	private ProdusFactory produsFactory;
	
	@PostConstruct
	public void init(){
		versiuneRepository = new EntityRepositoryBase<Versiune>(this.em, Versiune.class);
		
		logger.info("Initialized versiuneRepository: " + versiuneRepository.size());
		
	/*
		editieRepository = new EntityRepositoryBase<Editie>(this.em, Editie.class);
		sprintRepository.setEm(this.em);
	
		Logger.info("Initialized editieRepository: " + editieRepository.size());
		Logger.info("Initialized sprintRepository: " + sprintRepository.size());
		*/
	}
	
	@TransactionAttribute (TransactionAttributeType.REQUIRES_NEW)
	public Produs createNewProdus(Integer id){
		Produs produs = ProdusFactory.buildProdus(id, "NEW Produs", "categ 1",4,2);
		this.add(produs);
		return Produs.toDTOAggregate(produs);
	}
	public String getMessage(){
		return "ProdusSprintDataServices is working...";
	}
//	@Override
	@GET @Path("/{id}")   /* scrum/produse/{id} REST- resource:produs-entity*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Produs getById (@PathParam("id") Integer id){
		Produs produs = super.getById(id);
		return Produs.toDTOAggregate(produs);
	}
	
	@Override
	@GET /*scrum/produse      REST-resource> produse-collection*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Produs> toCollection(){
		Collection<Produs> produse = super.toCollection();
		Produs[] produsArray = Produs.toDTOList(produse);
		return Arrays.asList(produsArray);
	}
	
	@Override
	public Produs add(Produs produs){
		produs = super.add(produs);
		return Produs.toDTOAggregate(produs);
	}
	
}
