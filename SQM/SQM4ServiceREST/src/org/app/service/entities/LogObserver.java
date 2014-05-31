package org.app.service.entities;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */
public class LogObserver implements IObserver {

	private String idLog;
	public Bug m_Bug;

	public LogObserver(){

	}

	public void finalize() throws Throwable {

	}

	@Override
	public LogObserver WriteLog(Bug bug) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 
	 * @param bug
	 */
// get & set 
	public String getIdLog() {
		return idLog;
	}

	public void setIdLog(String idLog) {
		this.idLog = idLog;
	}

}