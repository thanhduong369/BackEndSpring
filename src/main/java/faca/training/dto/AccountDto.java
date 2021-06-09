package faca.training.dto;


/**
 * 
 * @author DuongDT 19
 * 
 * @version 1.0
 * 
 * @Date 2/6/2021
 * 
 * 
 * @Modification Logs:
 * 
 * @Date 				@AUTHOR 							@DESCRIPTION
 * -----------------------------------------------------------------------
 * 2/6/2021			    DuongDT19							Create
 *
 */

public class AccountDto {
	private int id; // id 
	
	private String user_name; // userName
	
	private String password; // passWork
	
	private int active; // active :0 active , 1 anactive
	
	private String studentName; // name student

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
}
