package faca.training.entitis;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "Student")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "studentId",columnDefinition = "nvarchar(255)")
	private String student_Id;
	
	@Column(name = "name",columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name = "age",columnDefinition = "int")
	private int age;
	
	@Column(name = "adrres",columnDefinition = "nvarchar(1000)")
	private String adrres;
	
	@Column(name = "phonenumber",columnDefinition = "nvarchar(255)")
	private String phonenumber;
	
	@Column(name = "gmail",columnDefinition = "nvarchar(255)")
	private String gmail;
	
	@Column(name = "active",columnDefinition = "int")
	private int active;
	
	
	@ManyToOne
	@JoinColumn(name = "idClass",referencedColumnName ="id")
	Class classs;
	
	@OneToOne(mappedBy ="student",cascade = CascadeType.ALL)
	Account account;

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

	public Class getClasss() {
		return classs;
	}

	public void setClasss(Class classs) {
		this.classs = classs;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
