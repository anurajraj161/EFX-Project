package userpack.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="User_id")
	private Long U_id;
	
	@Column(name="User_name")
	private String name;
	
	@Column(name="designation")
	private String designation;


	public User(Long u_id, String name, String designation) {
		super();
		U_id = u_id;
		this.name = name;
		this.designation = designation;
		
	}

	public long getU_id() {
		return U_id;
	}

	public void setU_id(Long u_id) {
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


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


}
