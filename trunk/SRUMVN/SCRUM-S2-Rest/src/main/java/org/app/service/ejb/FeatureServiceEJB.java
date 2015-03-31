package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.EntityBase;
import org.app.service.entities.Feature;

@Path("features")
// 1. Remote interface: FeatureService
@Stateless
@LocalBean // to be referenced locally: e.g. by Arquillian-client
public class FeatureServiceEJB implements FeatureService{
	private static Logger logger = Logger.getLogger(FeatureServiceEJB.class.getName());
	
	// 1. Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;

    @PostConstruct
	public void init(){
		logger.info("Initialized :");
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}	

	public FeatureServiceEJB() {
		super();
		logger.info("CONSTRUCTOR-INIT : " + this.em);		
	}
	
	@Override
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Feature> getFeatures(){
		List<Feature> features = em.createQuery("SELECT f FROM Feature f", Feature.class).getResultList();
		return features;
	}
	
	@Override
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Feature addFeature(Feature featureToAdd){
		em.persist(featureToAdd);
		em.flush();
		// transactions are managed by default by container
		em.refresh(featureToAdd);
		return featureToAdd;
	}
	
	@Override
	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String removeFeature(Feature featureToDelete){
		// 1. Use remove 
//		featureToDelete = em.find(Feature.class,featureToDelete.getFeatureID());
//		em.remove(featureToDelete);
//		em.flush();
		
		// 2. Use JPQL-DELETE
		String jpqlDELETE = "DELETE FROM Feature f WHERE f.id = :id";
		logger.info("DEBUG removeFeature :" + jpqlDELETE);
		em.createQuery(jpqlDELETE).setParameter("id", featureToDelete.getFeatureID()).executeUpdate();
		
		// transactions are managed by default by container
		return "True";
	}
	
	
	
	/********************************************************************/
	@GET @Path("/test")
	@Produces("text/html")
	public String sayRest() {
		return "Feature Service is On... ";
	}	
	/********************************************************************/	
}
