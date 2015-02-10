package org.app.service.ejb;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Activitati_suport;
import org.app.service.entities.Produse;

@Path("service")
@Stateless 
@LocalBean
public  class ProduseDataServiceEJB 
			extends EntityRepositoryBase<Produse> 
			implements ProduseDataService, Serializable{
public static Logger logger = Logger.getLogger(ProduseDataServiceEJB.class.getName());


	
	/* (non-Javadoc)
	 * @see org.app.service.ejb.ProduseService#save(org.app.service.entities.Produse)
	 */
private EntityRepository<Activitati_suport> activitatiRepository;

@PostConstruct
public void init(){
	activitatiRepository = new EntityRepositoryBase<Activitati_suport>(this.em, Activitati_suport.class);
	
	logger.info("Initializare activitatiRepository : " + activitatiRepository.size());
}

	@Override
	public Produse save(Produse produs){
		produs = this.add(produs);
		return produs;
	}
	
	
	public String test(){
		return "Hello from ProduseDataService.";
	}
}
