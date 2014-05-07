package org.app.test.rest.patterns;

import java.io.StringWriter;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.app.scrum.project.Project;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

/*
 * REST Resource CRUD convention:
 * * GET will return desired resource entity instance;
 * * POST, PUT will return transacted resource in its new state;
 * * DELETE will return null or error object in case of failure; 
 * 
 */

public class RESTfullResource <T extends Object> {
	private static Logger logger = Logger.getLogger(RESTfullResource.class.getName());
	private enum HTTP_METHOD {GET, POST, PUT, DELETE};
	
	private String basePath;
	private Class<T> entityResourceClass;
//	private Class entityResourceClass;
	private String mediaType = "application/xml";
	
	private ClientRequest request;
	
	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public Class<T> getEntityResourceClass() {
		return entityResourceClass;
	}

	public void setEntityResourceClass(Class entityResourceClass) {
		this.entityResourceClass = entityResourceClass;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public RESTfullResource(String basePath, Class entityResourceClass, String mediaType) {
		this.basePath = basePath;
		this.entityResourceClass = entityResourceClass;
		this.mediaType = mediaType;
		this.request = new ClientRequest(basePath);
		this.request.accept(mediaType);		
	}
	
	public RESTfullResource(String basePath) {
		this.basePath = basePath;
		this.entityResourceClass = (Class<T>) String.class;
		this.request = new ClientRequest(basePath);
	}	

	/* CRUD Methods */
	public T get() throws Exception{
//		if(this.request == null)
//			throw new Exception("Failed REST: GET request object not initialized!");
		return (T) this.invokeResourceRequest(HTTP_METHOD.GET, null);
	}
	public T put(T entity) throws Exception{
		return (T) this.invokeResourceRequest(HTTP_METHOD.PUT, entity);
	}	
	public T post(T entity) throws Exception{
		return (T) this.invokeResourceRequest(HTTP_METHOD.POST, entity);
	}	
	public Object delete(T entity) throws Exception{
		this.invokeResourceRequest(HTTP_METHOD.DELETE, entity);
	    return null;
	}	
	public Object delete() throws Exception{
		this.invokeResourceRequest(HTTP_METHOD.DELETE, null);
	    return null;
	}	

	
	/* Free Form Methods*/
	public Object get(String customPath) throws Exception{
		if (customPath != null){
			if (!customPath.startsWith("/") && !this.basePath.endsWith("/"))
				customPath = "/" + customPath;
			customPath = basePath + customPath;
			logger.info("DEBUG custom GET path: " + customPath);
			ClientRequest customGETRequest = new ClientRequest(customPath);
			
			ClientResponse response = customGETRequest.get();
			int responseCode = response.getResponseStatus().getStatusCode();
		    if(responseCode != 200){
		        throw new RuntimeException("Failed with HTTP error code : " + responseCode);
		    }
		    return response.getEntity(String.class);
		}
		return null;
	}
	
	/* Internals */
	@SuppressWarnings("unchecked")
	private  T invokeResourceRequest(HTTP_METHOD requestType, T entity) throws Exception{
		if(this.request == null)
			throw new Exception("Failed REST: REST request object not initialized!");
		logger.info("DEBUG resource request " + request.getUri());
		
		if(entity != null)
			this.request.body(this.mediaType, mapEntityToMediaType(entity));
		ClientResponse<T> response = null;
		
		//Send the request
		if(requestType.equals(HTTP_METHOD.GET))
			response = this.request.get();
		if(requestType.equals(HTTP_METHOD.PUT))
			response = this.request.put();
		if(requestType.equals(HTTP_METHOD.POST))
			response = this.request.post();
		if(requestType.equals(HTTP_METHOD.DELETE))
			response = this.request.delete();
	    int responseCode = response.getResponseStatus().getStatusCode();
	    if(responseCode != 200){
	        throw new RuntimeException("Failed with HTTP error code : " + responseCode);
	    }	  
	    logger.info("DEBUG resource RESPONSE " + response + " --- " + this.entityResourceClass);
	    try{
		    if (response.getEntity(this.entityResourceClass) != null)
		    	return (T) response.getEntity(this.entityResourceClass);
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    	logger.info("DEBUG resource request ERROR INVOCATION " + ex.getMessage());
	    }
	    return null;		
	}
	
	private String mapEntityToMediaType(T entity) throws Exception{
		StringWriter writer = new StringWriter();
	    JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    jaxbMarshaller.marshal(entity, writer);
	    return writer.getBuffer().toString();
	}
	
	/* Simple GET Test*/
	public static <T extends Object> T getResource(String restURL, String mediaType, Class<T> entityClass) throws Exception{
		ClientRequest request = new ClientRequest(restURL);
		//Set the accept header to tell the accepted response format
	    request.accept(mediaType);
	     
	    //RESTEasy client automatically map response entity to target entity.
	    ClientResponse<T> response = request.get(entityClass);
	     
	    //Check response status code
	    int apiResponseCode = response.getResponseStatus().getStatusCode();
	    if(response.getResponseStatus().getStatusCode() != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + apiResponseCode);
	    }
	     
	    //Get entity from response
	    return (T) response.getEntity();
	}
}