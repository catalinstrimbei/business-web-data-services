/**
 * 
 */
package org.app.service.ejb;

import org.app.service.entities.Contract;


/**
 * @author Alina
 *
 */
public interface ContractDataService {
	public String test();

	public String getMessage();

	public Contract add(Contract contract);

	public Contract getById(int i);
	public Contract getByIdJ(int i);

	

	public Contract createNewContract(Integer id);

	public Contract getByID(int i);

	public void remove(Contract contract);

	Contract save(Contract contract);
}
