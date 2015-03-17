package org.app.scrum.rest;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CredentialBean implements Serializable{
	private static Logger logger = Logger.getLogger(CredentialBean.class.getName());
	
	private String username;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String creds;

	public String getCreds() {
		return creds;
	}

	public void setCreds(String creds) {
		this.creds = creds;
	}

	public CredentialBean() {
		super();
		this.username = "default";
		this.creds = "default";
		logger.info("DEBUG " + "... initialized CredentialBean");
	}

	@Override
	public String toString() {
		return "CredentialBean [username=" + username + "]";
	}
	
	
}
