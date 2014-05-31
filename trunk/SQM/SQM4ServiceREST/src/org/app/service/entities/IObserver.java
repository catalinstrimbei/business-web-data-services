package org.app.service.entities;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */
public interface IObserver {

	/**
	 * 
	 * @param bug
	 */
	public LogObserver WriteLog(Bug bug);

}