package org.app.service.entities;


/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */
public interface IManagementRol {

	public Rol create();

	/**
	 * 
	 * @param r
	 */
	public void delete(Rol r);

	/**
	 * 
	 * @param r
	 */
	public void select(Rol r);

	/**
	 * 
	 * @param r
	 */
	public void update(Rol r);

}