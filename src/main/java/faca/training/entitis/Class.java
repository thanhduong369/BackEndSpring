package faca.training.entitis;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "Class")
public class Class {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name ="malop",columnDefinition = "nvarchar(255)")
	private String malop;
	
	@Column(name ="nameClass",columnDefinition = "nvarchar(255)")
	private String name_class;
	
	@Column(name ="memBer",columnDefinition = "int")
	
	private int mem_ber;
	
	@Column(name ="active",columnDefinition = "int")
	private int active;
	
	@OneToMany(mappedBy = "classs",cascade = CascadeType.ALL)
	List<Student> student;



	public String getName_class() {
		return name_class;
	}

	public void setName_class(String name_class) {
		this.name_class = name_class;
	}

	public int getMem_ber() {
		return mem_ber;
	}

	public void setMem_ber(int mem_ber) {
		this.mem_ber = mem_ber;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getMalop() {
		return malop;
	}

	public void setMalop(String malop) {
		this.malop = malop;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
}
