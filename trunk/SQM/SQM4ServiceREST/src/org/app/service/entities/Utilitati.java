package org.app.service.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */

@Entity
public class Utilitati implements EmailManagement {
	@Id @GeneratedValue
	private Integer id;
	private User user;
	private Bug bug;
	
	
	//trebuie stearsa legatura intre utilitati si atasament
	
	
//	private List<Bug> assignedBug = new ArrayList<>();
	//public Assign m_Assign;

	public Utilitati(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param b
	 * @param u
	 */
	public void TrimiteMailAsumare(Bug b, User u){

	}

	/**
	 * 
	 * @param b
	 * @param u
	 */
	public void TrimiteMailCreare(Bug b, User u){

	}

	/**
	 * 
	 * @param b
	 * @param u
	 */
	public void TrimiteMailSchimbare(Bug b, User u){

	}
	// get & set 

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