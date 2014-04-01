package org.app.scrum.rest;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@RequestScoped
//@ApplicationScoped
//@SessionScoped
public class CredentialBean implements Serializable{
	private static Logger logger = Logger.getLogger(CredentialBean.class.getName());
	
	private String creds;

	public String getCreds() {
		return creds;
	}

	public void setCreds(String creds) {
		this.creds = creds;
	}

	public CredentialBean() {
		super();
		this.creds = "default";
		logger.info("DEBUG " + "... initialized CredentialBean");
	}

	@Override
	public String toString() {
		return "CredentialBean [creds=" + creds + "]";
	}
	
	
}
