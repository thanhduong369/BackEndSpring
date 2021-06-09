package faca.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import faca.training.dto.SearchDto;
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
 * @Modification Logs:
 * 
 * @Date 				@AUTHOR 							@DESCRIPTION
 * -----------------------------------------------------------------------
 * 2/6/2021			    DuongDT19							Create
 *
 */
@Service
@Mapper
public interface StudentDao {
	
	
	@Insert("Insert into student(student_id,age,name,id_class,gmail,phonenumber,active,adrres) "
			+ "VALUES (#{student_Id}, #{age}, #{name},#{classs.id},#{gmail},#{phonenumber},0,#{adrres})")
	// save student 
	public int save(Student student);

	@Select("SELECT * FROM student WHERE active = 0")
	@Results(value = { @Result(column = "student_id", property = "student_Id"),
	@Result(column = "id_class", property = "classs", javaType = Class.class, one = @One(select = "findById")) })
	 // get list student join class
	List<Student> getListStudent();

	@Select("select * FROM student WHERE student_id = #{student_id} AND active = 0")
	@Results(value = { @Result(column = "student_id", property = "student_Id"),
	@Result(column = "id_class", property = "classs", javaType = Class.class, one = @One(select = "findById")) })
	//find one student with student id 
	Student FindOne(String student_id); 

	@Select("SELECT * FROM class WHERE id = #{id}")
	// find class by id
	public Class findById(int id);

	@Update("UPDATE student SET active = 1 WHERE student_id = #{student_id}")
	// delete student by id
	public boolean deleteById(String student_id);

	@Update("UPDATE student "
		  + "SET name = #{name},age = #{age},id_class = #{classs.id},gmail = #{gmail},phonenumber = #{phonenumber},adrres = #{adrres} WHERE student_id = #{student_Id}")
	//update student 
	public void updateStudent(Student one); 

	@Select("select * FROM student WHERE active = 0 and id_class = #{id} ")
	 //get list student by id class / and name LIKE '%' + #{txtTimkiem} + '%'
	public List<Student> getListStudentByID(int id);
	
	@Select("select * FROM student WHERE active = 0 and id_class = #{idClass} and (name LIKE '%' + #{txtTimkiem} + '%' or adrres LIKE '%' + #{txtTimkiem} + '%' or phonenumber LIKE '%' + #{txtTimkiem} + '%')")
	// sheare student by id class
	public List<Student> timKimeStudent(SearchDto timkiem);

}
