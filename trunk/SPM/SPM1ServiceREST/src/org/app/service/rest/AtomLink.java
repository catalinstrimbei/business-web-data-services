package org.app.service.rest;

import java.io.Serializable;
import java.net.URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value = XmlAccessType.NONE)
public class AtomLink implements Serializable{
	
private URI href;
private String rel;
private String type;

@XmlAttribute(name="href")
public URI getHref() {
	return href;
}
public void setHref(URI href) {
	this.href = href;
}

@XmlAttribute(name="rel")
public String getRel() {
	return rel;
}
public void setRel(String rel) {
	this.rel = rel;
}

@XmlAttribute(name="type")
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}


public AtomLink(String href, String rel) throws Exception{
	super();
	this.href =new URI(href);
	this.rel = rel;
	this.type = "text/html";
}

//public static String BASE_URL = "http://localhost:8080/ScrumREST/produse/";
//@XmlElement(name=link)
//public AtomLink getLink() throws Exception{
	//String restUrl = BASE_URL + this.get();
	
}


	
