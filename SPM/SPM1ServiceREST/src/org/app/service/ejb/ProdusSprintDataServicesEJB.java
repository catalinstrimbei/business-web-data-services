package org.app.service.ejb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

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

@Stateless
public class ProdusSprintDataServicesEJB extends EntityRepositoryBase<Produs> implements Serializable {

	private static Logger logger = Logger.getLogger(ProdusSprintDataServicesEJB.class.getName());
	
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
		Produs produs = produsFactory.buildProdus(id, "NEW Produs", "categ 1",4,2);
		this.add(produs);
		return Produs.toDTOAggregate(produs);
	}
	public String getMessage(){
		return "ProdusSprintDataServices is working...";
	}
	@Override
	public Produs getById(Object id){
		Produs produs = super.getById(id);
		return Produs.toDTOAggregate(produs);
	}
	
	@Override
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
