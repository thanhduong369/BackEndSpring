package faca.training.entitis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_name",columnDefinition = "nvarchar(255)")
	private String user_name;
	
	@Column(name = "password",columnDefinition = "nvarchar(255)")
	private String password;
	
	@Column(name = "active",columnDefinition = "int")
	private int active;
	
	@OneToOne
	@JoinColumn(name = "studentid",referencedColumnName ="id")
	Student student;

	
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}
