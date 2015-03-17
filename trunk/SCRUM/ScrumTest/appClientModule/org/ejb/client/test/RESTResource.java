package org.ejb.client.test;

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

public class RESTResource <T extends Object> {
	private static Logger logger = Logger.getLogger(RESTResource.class.getName());
	
	private String basePath;
	private Class<T> entityResourceClass;
	private String mediaType = "application/xml";
	
	private String GETpath = "/";
	private String POSTpath;
	private String PUTpath;
	private String DELETEpath;
	
	private ClientRequest GETrequest;
	private ClientRequest POSTrequest;
	private ClientRequest PUTrequest;
	private ClientRequest DELETErequest;	
	
	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public Class<T> getEntityResourceClass() {
		return entityResourceClass;
	}

	public void setEntityResourceClass(Class<T> entityResourceClass) {
		this.entityResourceClass = entityResourceClass;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getGETpath() {
		return this.GETpath;
	}

	public void setGETpath(String path) {
		if (path != null){
			if (!path.startsWith("/") && !this.basePath.endsWith("/"))
				path = "/" + path;
			this.GETpath = path;
			this.GETrequest = new ClientRequest(basePath + path);
			this.GETrequest.accept(mediaType);
			logger.info("DEBUG GET path: " + this.GETpath);
		}
	}

	public String getPOSTpath() {
		return this.POSTpath;
	}

	public void setPOSTpath(String path) {
		if (path != null){
			if (!path.startsWith("/") && !this.basePath.endsWith("/"))
				path = "/" + path;
			this.POSTpath = path;
			this.POSTrequest = new ClientRequest(basePath + path);
			this.POSTrequest.accept(mediaType);
			logger.info("DEBUG POST path: " + this.POSTpath);
		}
	}

	public String getPUTpath() {
		return this.PUTpath;
	}

	public void setPUTpath(String path) {
		if (path != null){
			if (!path.startsWith("/") && !this.basePath.endsWith("/"))
				path = "/" + path;
			this.PUTpath = path;
			this.PUTrequest = new ClientRequest(basePath + path);
			this.PUTrequest.accept(mediaType);
			logger.info("DEBUG PUT path: " + this.PUTpath);
		}
	}

	public String getDELETEpath() {
		return this.DELETEpath;
	}

	public void setDELETEpath(String path) {
		if (path != null){
			if (!path.startsWith("/") && !this.basePath.endsWith("/"))
				path = "/" + path;
			this.DELETEpath = path;
			this.DELETErequest = new ClientRequest(basePath + path);
			this.DELETErequest.accept(mediaType);
			logger.info("DEBUG DELETE path: " + this.DELETEpath);
		}
	}

	public RESTResource(String basePath, Class<T> entityResourceClass,
			String mediaType, String GETpath, String POSTpath, String PUTpath,
			String DELETEpath) {
		super();
		this.basePath = basePath;
		this.entityResourceClass = entityResourceClass;
		this.mediaType = mediaType;
		
		this.setGETpath(GETpath);
		this.setPOSTpath(POSTpath);
		this.setPUTpath(PUTpath);
		this.setDELETEpath(DELETEpath);
	}

	/* CRUD Methods */
	public T get() throws Exception{
		if(this.GETrequest == null)
			throw new Exception("Failed REST: GET request object not initialized!");
		return (T) this.invokeResourceRequest(GETrequest, null);
	}
	public T put(T entity) throws Exception{
		if(this.PUTrequest == null)
			throw new Exception("Failed REST: PUT request object not initialized!");
		return (T) this.invokeResourceRequest(PUTrequest, entity);
	}	
	public T post(T entity) throws Exception{
		if(this.POSTrequest == null)
			throw new Exception("Failed REST: POST request object not initialized!");
		return (T) this.invokeResourceRequest(POSTrequest, entity);
	}	
	public Object delete(T entity) throws Exception{
		if(this.DELETErequest == null)
			throw new Exception("Failed REST: DELETE request object not initialized!");
		this.invokeResourceRequest(DELETErequest, entity);
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
	private  T invokeResourceRequest(ClientRequest resourceRequest, T entity) throws Exception{
		if(resourceRequest == null)
			throw new Exception("Failed REST: REST request object not initialized!");
		logger.info("DEBUG resource request " + resourceRequest.getUri());
		if(entity != null)
			resourceRequest.body(this.mediaType, mapEntityToMediaType(entity));
		ClientResponse<T> response = null;
		//Send the request
		if(resourceRequest == this.GETrequest)
			response = this.GETrequest.get();
		if(resourceRequest == this.PUTrequest)
			response = this.PUTrequest.put();
		if(resourceRequest == this.POSTrequest)
			response = this.POSTrequest.post();
		if(resourceRequest == this.DELETErequest)
			response = this.DELETErequest.delete();
	    int responseCode = response.getResponseStatus().getStatusCode();
	    if(responseCode != 200){
	        throw new RuntimeException("Failed with HTTP error code : " + responseCode);
	    }	    
	    try{
		    if (response.getEntity(this.entityResourceClass) != null)
		    	return (T) response.getEntity(this.entityResourceClass);
	    }catch(Exception ex){
	    	logger.info("DEBUG resource request ERROR " + ex.getMessage());
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