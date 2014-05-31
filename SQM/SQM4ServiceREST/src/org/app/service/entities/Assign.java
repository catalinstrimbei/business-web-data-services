package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.02
 */

@Entity 
public class Assign implements IAssign {
	
	@Id @GeneratedValue
	private String id;
	private User user;
	private Bug bug;

	public Assign(){
		

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param b
	 * @param u
	 */
	public void assign(Bug b, User u){

	}
	//get & set 

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}
	
	


}