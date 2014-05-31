package org.app.service.entities;


/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */
public interface IManagementUser {

	public User create();

	/**
	 * 
	 * @param user
	 */
	public void delete(User user);

	/**
	 * 
	 * @param user
	 */
	public void select(User user);

	/**
	 * 
	 * @param user
	 */
	public void update(User user);

}