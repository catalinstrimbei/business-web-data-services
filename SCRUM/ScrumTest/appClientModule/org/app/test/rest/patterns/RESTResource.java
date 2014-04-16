package org.app.test.rest.patterns;

import java.io.StringWriter;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.app.scrum.project.Project;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

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
			
		ClientResponse<T> response = this.GETrequest.get(this.entityResourceClass);
		int getResponseCode = response.getResponseStatus().getStatusCode();
	    if(getResponseCode != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + getResponseCode);
	    }
	    //Get entity from response
	    return (T) response.getEntity();
	}
	public T put(T entity) throws Exception{
		if(this.PUTrequest == null)
			throw new Exception("Failed REST: PUT request object not initialized!");
		logger.info("DEBUG PUTpath " + this.PUTpath);
		logger.info("DEBUG PUTrequest " + this.PUTrequest.getUri());
		
		String s = mapEntityToMediaType(entity);
		logger.info("DEBUG PUTrequest entity: " + s);
		
	    this.PUTrequest.body(this.mediaType, s);
	     
	    //Send the request
	    ClientResponse<T> response = this.PUTrequest.put();
//	    T resource = response.getEntity(this.entityResourceClass);
//	    logger.info("DEBUG PUTrequest resource: " + resource);
	    
	    int putResponseCode = response.getResponseStatus().getStatusCode();
	    logger.info("DEBUG PUTrequest" + putResponseCode);
	    
	    if(putResponseCode != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + putResponseCode);
	    }	    
	    
	    if (response.getEntity(this.entityResourceClass) != null)
	    	return (T) response.getEntity(this.entityResourceClass);
	    return null;
	}	
	public T post(T entity) throws Exception{
		if(this.POSTrequest == null)
			throw new Exception("Failed REST: POST request object not initialized!");
		
	    this.POSTrequest.body(this.mediaType, mapEntityToMediaType(entity));
	     
	    //Send the request
	    ClientResponse<T> response = this.POSTrequest.post();
	    int postResponseCode = response.getResponseStatus().getStatusCode();
	    if(postResponseCode != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + postResponseCode);
	    }	    
	    
	    if (response.getEntity(this.entityResourceClass) != null)
	    	return (T) response.getEntity(this.entityResourceClass);
	    return null;
	}	
	public Object delete(T entity) throws Exception{
		if(this.DELETErequest == null)
			throw new Exception("Failed REST: DELETE request object not initialized!");
		
	    this.DELETErequest.body(this.mediaType, mapEntityToMediaType(entity));
	     
	    //Send the request
//	    ClientResponse<T> response = this.DELETErequest.delete();
	    ClientResponse response = this.DELETErequest.delete();
	    int deleteResponseCode = response.getResponseStatus().getStatusCode();
	    if(deleteResponseCode != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + deleteResponseCode);
	    }	    
	    
//	    if (response.getEntity(this.entityResourceClass) != null)
//	    	return (T) response.getEntity(this.entityResourceClass);
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