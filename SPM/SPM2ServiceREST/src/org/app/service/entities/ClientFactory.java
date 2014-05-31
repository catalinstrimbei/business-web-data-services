package org.app.service.entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;

@Singleton
public class ClientFactory {
	public Client buildClient(Integer id,String nume, Integer contractCount ){
		Client client= new Client(id, nume +"."+ id, contractCount);
		List<Contract> contracteClient= new ArrayList<>();
		Date dataContract= new Date();
		Long interval = 30l*24*60*60*1000;
		for (int i=0; i<=contractCount-1;i++){
			contracteClient.add(new Contract(null, "C:"+client.getIdClient() + "."+i, new Date(dataContract.getTime()+i+interval), client));
		}
		client.setContracte(contracteClient);
		return client;
	}
	

	private Contract Contract(Object object, String string, Date date, Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	public ClientFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client buildClient(Integer id, String nume, int contractCount) {
		// TODO Auto-generated method stub
		return null;
	}
} 
