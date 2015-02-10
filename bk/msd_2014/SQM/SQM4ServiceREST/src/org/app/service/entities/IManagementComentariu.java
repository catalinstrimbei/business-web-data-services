package org.app.service.entities;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */
public interface IManagementComentariu {

	public Comentariu create();

	/**
	 * 
	 * @param c
	 * @param b
	 */
	public void delete(Comentariu comentariu, Bug bug);

	/**
	 * 
	 * @param c
	 * @param b
	 */
	public void update(Comentariu comentariu, Bug bug);

	/**
	 * 
	 * @param c
	 * @param b
	 */
	public Comentariu select(Comentariu comentariu, Bug bug);

}