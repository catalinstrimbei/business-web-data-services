package org.app.scrum.rest;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ApplicationConfigBean {

private static Logger logger = Logger.getLogger(ApplicationConfigBean.class.getName());
	
//
//	@Inject
//	private CredentialBean creds;
	
	private String cfgs;

	public void setCfgs(String cfgs) {
		this.cfgs = cfgs;
	}

	public String getCfgs() {
		return cfgs;
	}

	public ApplicationConfigBean() {
		super();
		this.cfgs = "default";
		logger.info("DEBUG " + "initialized ApplicationConfigBean ");
	}

	@Override
	public String toString() {
		return "ApplicationConfigBean [cfgs=" + cfgs + "]";
	}
	
	
}