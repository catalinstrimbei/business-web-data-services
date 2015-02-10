package org.app.service.entities;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */
public interface IManagementBug{

	public void adauga();

	/**
	 * 
	 * @param bug
	 */
	public void sterge(Bug bug);

	/**
	 * 
	 * @param bug
	 */
	public void select(Bug bug);

	/**
	 * 
	 * @param bug
	 * @param status
	 */
	public void modificaStatus(Bug bug, StatusEnum status);

	/**
	 * 
	 * @param bug
	 * @param comentariu
	 */
	public void adaugaComentariu(Bug bug, Comentariu comentariu);

	/**
	 * 
	 * @param bug
	 * @param prioritate
	 */
	public void setPrioritate(Bug bug, PrioritateEnum prioritate);

	public void adaugaAtasamnet();

	
	public void notifica();

}