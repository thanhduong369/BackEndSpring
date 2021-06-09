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

public class StudentDto {
	
	private String student_Id;
	
	private String name;
	
	private int age;
	
	private String adrres;
	
	private String phonenumber;
	
	private String gmail;
	
	private String classname;

	private int active;

	public String getClassname() {
		return classname;
	}


	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getStudent_Id() {
		return student_Id;
	}


	public void setStudent_Id(String student_Id) {
		this.student_Id = student_Id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getAdrres() {
		return adrres;
	}


	public void setAdrres(String adrres) {
		this.adrres = adrres;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getGmail() {
		return gmail;
	}


	public void setGmail(String gmail) {
		this.gmail = gmail;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}
	

}
