package org.app.service.ejb;

import javax.ejb.Remote;

@Remote
public interface DataService {
	String sayMessage(String string);
}
