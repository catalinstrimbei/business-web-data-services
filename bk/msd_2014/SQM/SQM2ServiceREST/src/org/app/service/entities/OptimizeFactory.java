package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;

@Singleton
public class OptimizeFactory {

	public Task buildTask(int idTask, String name, int optimizationNo)
	{
		Task task= new Task(idTask,name +"."+idTask, new Date() );
		List<Optimize> optimizeList= new ArrayList<>();
		
		for(int i=0;i<=optimizationNo-1;i++)
		{
			optimizeList.add(new Optimize(new Date(),"Optimize: "+task.getName(),task.getIdTask()+i,task));
			
		}
		
		task.setOptimizations(optimizeList);
		return task;
		
	}
}
