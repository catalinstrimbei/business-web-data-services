package org.app.service.entities;


/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */
public interface EmailManagement {

	/**
	 * 
	 * @param b
	 * @param u
	 */
	public void TrimiteMailCreare(Bug b, User u);

	/**
	 * 
	 * @param b
	 * @param u
	 */
	public void TrimiteMailSchimbare(Bug b, User u);

	/**
	 * 
	 * @param b
	 * @param u
	 */
	public void TrimiteMailAsumare(Bug b, User u);

}