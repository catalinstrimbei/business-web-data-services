package org.app.service.entities;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */
public interface IManagementAtasament {

	public Atasament create();

	/**
	 * 
	 * @param atach
	 */
	public void update(Atasament atach);

	/**
	 * 
	 * @param atach
	 */
	public void delete(Atasament atach);

	/**
	 * 
	 * @param atach
	 */
	public void select(Atasament atach);

}