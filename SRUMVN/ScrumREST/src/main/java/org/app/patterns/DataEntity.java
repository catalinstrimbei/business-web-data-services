package org.app.patterns;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(of = {"description"})
public class DataEntity {
	private Integer id;
	private String description;
	
	public static void main(String... args){
		DataEntity data1 = new DataEntity();
		data1.setId(1);
		data1.setDescription("Data1");
		System.out.println(data1.getId() + " of " + data1);
		
		DataEntity data2 = new DataEntity();
		data2.setId(1);
		data2.setDescription("Data2");
		System.out.println(data2.getId() + " of " + data2);
		
		System.out.println("is data1 equals data2: " +data1.equals(data2));
	}
	
	public void setId(Integer id){
		System.out.println("Custom setter ...");
		this.id = id+1;
	}
}