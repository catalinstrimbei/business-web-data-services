package org.app.service.entities;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */
public interface IManagementPermisie {

	public Permisie create();

	/**
	 * 
	 * @param p
	 */
	public void delete(Permisie p);

	/**
	 * 
	 * @param p
	 */
	public void select(Permisie p);

	/**
	 * 
	 * @param p
	 */
	public void update(Permisie p);

}