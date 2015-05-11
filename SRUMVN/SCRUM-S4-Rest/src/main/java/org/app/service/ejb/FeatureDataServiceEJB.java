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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.Feature;

/*
 * SCRUM: 		src/main/webapp/WEB-INF/jboss-web.xml
 * data	: 		org.app.service.rest.ApplicationConfig
 * features: 	@Path
 */
@Path("features") /* http://localhost:8080/SCRUM/data/features */
@Stateless @LocalBean
public class FeatureDataServiceEJB implements FeatureDataService{
	private static Logger logger = Logger.getLogger(FeatureDataServiceEJB.class.getName());
	
	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	// Constructor
	public FeatureDataServiceEJB() {
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}		

	/* CRUD operations implementation */
	// CREATE or UPDATE
	@PUT @Path("/{id}") 	/* SCRUM/data/features/{id} 	REST-resource: Feature-entity */	
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })		
	@Override
	public Feature addFeature(Feature featureToAdd){
		em.persist(featureToAdd);
		em.flush();
		// transactions are managed by default by container
		em.refresh(featureToAdd);
		return featureToAdd;
	}	
	
	// READ
	@GET @Path("/{id}")		/* SCRUM/data/features 		REST-resource: Feature-entity */
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })		
	@Override
	public Feature getFeatureByID(@PathParam("id")Integer featureID) {
		logger.info("**** DEBUG REST getFeatureByID(): id = " + featureID);
		return em.find(Feature.class, featureID);
	}	
	@GET 					/* SCRUM/data/features 		REST-resource: Features-collection */
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Collection<Feature> getFeatures(){
		List<Feature> features = em.createQuery("SELECT f FROM Feature f", Feature.class)
				.getResultList();
		logger.info("**** DEBUG REST features.size()= " + features.size());
		return features;
	}
	// REMOVE
	@DELETE 					/* SCRUM/data/features		REST-resource: Feature-entity */
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public String removeFeature(Feature featureToDelete){
		featureToDelete = em.merge(featureToDelete);
		em.remove(featureToDelete);
		em.flush();
		return "True";
	}
	
	// Custom READ: custom query
	@GET @Path("/{name}")		/* SCRUM/data/features 		REST-resource: Feature-entity */
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })		
	@Override
	public Feature getFeatureByName(@PathParam("name")String name) {
		return em.createQuery("SELECT f FROM Feature f WHERE f.name = :name", Feature.class)
				.setParameter("name", name)
				.getSingleResult();
	}	
	
	// Others
	public String getMessage() {
		return "FeatureServiceEJB is ON... ";
	}
}


