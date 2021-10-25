package userpack.dto;

import java.util.ArrayList;
import java.util.List;



import userpack.entity.Tasks;

public class UserDto {

     private long U_id;
	
     private String name;
	
	 private String designation;

//	 List<Tasks> task = new ArrayList<>();

	public UserDto(long u_id, String name, String designation) {
		super();
		U_id = u_id;
		this.name = name;
		this.designation = designation;
//		this.task = task;
	}

	public long getU_id() {
		return U_id;
	}

	public void setU_id(long u_id) {
		U_id = u_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

//	public List<Tasks> getTask() {
//		return task;
//	}
//
//	public void setTask(List<Tasks> task) {
//		this.task = task;
//	}
	 
	 
	 
	
}
