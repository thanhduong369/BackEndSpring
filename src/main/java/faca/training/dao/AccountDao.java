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

import faca.training.entitis.Account;
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
public interface AccountDao {
	@Select("select * from account where active = 0")
	@Results(value = { @Result(column = "id", property = "id"),
	@Result(column = "studentid", property = "student", javaType = Student.class, one = @One(select = "FindOne")) })
	// get all account 
	List<Account> getAll(); 

	@Select("SELECT * FROM account WHERE id = #{id} and active = 0")
	@Results(value = { @Result(column = "id", property = "id"),
	@Result(column = "studentid", property = "student", javaType = Student.class, one = @One(select = "FindOne")) })
	//find account by id
	public Account findById(int id); 

	@Insert("INSERT INTO account(active,password,user_name,studentid) "
		  + " VALUES (0, #{password}, #{user_name},#{student.id})")
	// save account 
	public int insert(Account account); 

	@Update("UPDATE account SET password =#{password} WHERE studentid=#{student.id}")
	// update account 
	public void updateAccount(Account accoutn);

	@Select("select * FROM student  WHERE id = #{id} AND active = 0")
	// find student 
	public Student FindOne(int id); 

	@Update("UPDATE account SET active =1 WHERE studentid = #{id}")
	//delete account by studen id
	public boolean deleteAccoutbyIdStudent(int id); 

	@Update("UPDATE account SET active =1 WHERE id = #{id}")
	// delete account by id
	public boolean deleteAccountByid(int id); 

	@Select("select * from account  where active = 0 AND password = #{txttimkiem}  or user_name = #{txttimkiem}")
	@Results(value = { @Result(column = "id", property = "id"),
	@Result(column = "studentid", property = "student", javaType = Student.class, one = @One(select = "FindOne")) })
	 // search acount 
	List<Account> Timkiem(String txttimkiem);

	@Select("select * from account where active = 0 AND user_name = #{user_name} AND password = #{password}")
	 // check login 
	public Account checkAccount(Account one);

}
