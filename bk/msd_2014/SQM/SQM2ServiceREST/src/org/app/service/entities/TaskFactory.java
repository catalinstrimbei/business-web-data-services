package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;

@Singleton
public class TaskFactory {
	
	/*Build a new task  with an specific id, name 
	 * and a number of tests 
	 * 
	 */

	public Task buildTask(int idTask, String name, int testNo)
	{
		Task task= new Task(idTask,name +"."+idTask, new Date() );
		List<Test> testList= new ArrayList<>();
		
		for(int i=0;i<=testNo-1;i++)
		{
			testList.add(new Test(null,"Test: "+task.getName(),task.getIdTask()+i,task));
			
		}
		
		task.setTests(testList);
		return task;
		
	}

}
