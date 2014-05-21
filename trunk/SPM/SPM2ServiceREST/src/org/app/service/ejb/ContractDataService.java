/**
 * 
 */
package org.app.service.ejb;

import org.app.service.entities.Contract;
import org.app.service.entities.ContractSuport;
import org.app.service.entities.PersoanaJuridica;

/**
 * @author Alina
 *
 */
public interface ContractDataService {
	public String test();

	public String getMessage();

	public ContractSuport add(ContractSuport contract);

	public ContractSuport getById(int i);
	public ContractSuport getByIdJ(int i);

	public PersoanaJuridica add(PersoanaJuridica clientJ);

	public ContractSuport createNewContract(int i);

	public ContractSuport getByID(int i);

	public void remove(ContractSuport contract);

	Contract save(Contract contract);
}
