package faca.training.dao;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import faca.training.entitis.Class;
import faca.training.entitis.Student;

/**
 * 
 * @author DuongDT 19
 * 
 * @version 1.0
 * 
 * @Date 2/6/2021
 * 
 * 
 * Modification Logs:
 * 
 * Date				AUTHOR 				DESCRIPTION
 * ------------------------------------------------------
 * 2/6/2021			DuongDT19			Create
 *
 */
@Service
@Mapper
public interface ClassDao {
	@Select("select * from class where active = 0")
	// get all class is active
	List<Class> getAll(); 

	@Select("SELECT * FROM class WHERE active = 0 AND id = #{id}")
	//get one class with id class
	public Class findById(int id); 

	@Insert("INSERT INTO class(active,malop,name_class,mem_ber) " + " VALUES (0, #{malop}, #{name_class},#{mem_ber})")
	 //save class
	public int insert(Class classa);

	@Select("SELECT * FROM class WHERE active = 0 AND name_class LIKE '%' + #{txttimkiem} + '%' or malop LIKE '%' + #{txttimkiem} + '%' ")
	 // sheare class with txttimkiem param
	public List<Class> timkiemString(String txttimkiem);

	@Select("SELECT * FROM class WHERE active = 0 AND malop = #{malop}")
	// get one class by malop param
	public Class findBymalop(String malop); 

	@Select("SELECT * FROM class WHERE  mem_ber = #{txttimkiem} and active = 0")
	// sheare with mumber
	public List<Class> timkiemso(int txttimkiem); 

	@Update("UPDATE class SET mem_ber =#{mem_ber},name_class =#{name_class} WHERE id = #{id}")
	// update class 
	public void updateClass(Class a); 
	
	@Update("UPDATE class SET mem_ber =#{mem_ber},name_class =#{name_class} WHERE malop = #{malop}")
	// update class 
	public void updateClassbyMalop(Class a); 

	@Update("UPDATE class SET active = 1 WHERE id = #{id}")
	// delete class by id class
	public boolean deleteById(int id); 

	@Select("SELECT * FROM student WHERE active = 0 and id_class = #{id}")
	// get list student by id class
	public List<Student> getListStudentbyit(int id); 

	@Select("SELECT * FROM Class l INNER JOIN Student s ON s.id_class=l.id WHERE malop = #{malop} and s.active = 0")
	@Results(value = { @Result(column = "id", property = "id"),
	@Result(column = "id_class", property = "student", javaType = List.class, many = @Many(select = "getListStudent")) })
	//get lisst class inner join student
	List<Class> findAllalljoin(String malop); 

	@Select("SELECT * FROM student WHERE id_class= #{id} and active = 0")
	@Results(value = { @Result(column = "student_Id", property = "student_Id") })
	 // get all student 
	List<Student> getListStudent();
	
}
