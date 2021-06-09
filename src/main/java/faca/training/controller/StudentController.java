package faca.training.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import faca.training.customer.Datacustomer;
import faca.training.dao.AccountDao;
import faca.training.dao.StudentDao;
import faca.training.dto.StudentDto;
import faca.training.entitis.Account;
import faca.training.entitis.Class;
import faca.training.entitis.Student;
import faca.training.exception.GmialExeption;
import faca.training.exception.MaSvexection;
import faca.training.exception.PhoneExeption;
import faca.training.exception.StudentIDException;
import faca.training.service.ClassService;
import faca.training.service.StudentService;

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
@RestController
public class StudentController {
	@Autowired
	StudentDao studentDao;

	@Autowired
	StudentService studentService;
	
	@Autowired 
	ClassService classervice;
	
	@Autowired
	AccountDao AccountDao;
	
	/**
	 * 
	 * overviews: add new student
	 * 
	 * @param student
	 * @return
	 * @throws MaSvexection
	 * @throws PhoneExeption
	 * @throws GmialExeption
	 */
	@PostMapping("/savestudent") // Api save student
	ResponseEntity<Datacustomer<Student>> save(@RequestBody Student student)
			throws MaSvexection, PhoneExeption, GmialExeption {
		// check Masv is exist in DB
		if (studentService.checkMasv(student.getStudent_Id())) { 
			throw new MaSvexection(student.getStudent_Id());
		}
		// check Phone is exist in DB
		if (studentService.checkPhone(student.getPhonenumber())) { 
			throw new PhoneExeption(student.getPhonenumber());
		}
		 // check gmail exist in DB
		if (studentService.checkGmail(student.getGmail())) {
			throw new GmialExeption(student.getGmail());
		}
		// save student
		studentDao.save(student);
		 // get student to save account
		Student DB = studentDao.FindOne(student.getStudent_Id());
		// create account 
		Account account = new Account(); 
		account.setStudent(DB);
		// set data for account
		account.setUser_name(student.getStudent_Id()); 
		account.setPassword(student.getStudent_Id()+"@12345"); 
		// save account of student 
		AccountDao.insert(account); 
		return new ResponseEntity<Datacustomer<Student>>(new Datacustomer<Student>(student, "msg.I001"),
				HttpStatus.OK);
	}
	
    /**
     * 
     * overviews: get list studetnt
     * 
     * @return list Student Dto
     */
	@GetMapping("/getList")
	ResponseEntity<?> getALL() {
		// get list student 
		List<Student> listentiti = studentDao.getListStudent(); 
		//create list studentDto
		List<StudentDto> listDto = new ArrayList<>(); 
		// check list student null
		if (studentDao.getListStudent() == null) { 
			return new ResponseEntity<String>("msg.E002", HttpStatus.BAD_REQUEST);
		} else {
			// maping entiti to Dto
			for (Student student : listentiti) { 
				StudentDto map = new StudentDto();
				map.setStudent_Id(student.getStudent_Id());
				map.setName(student.getName());
				map.setAge(student.getAge());
				map.setAdrres(student.getAdrres());
				map.setPhonenumber(student.getPhonenumber());
				map.setGmail(student.getGmail());
				map.setActive(student.getActive());
				map.setClassname(student.getClasss().getName_class());
				listDto.add(map);
			}
			return new ResponseEntity<List<StudentDto>>(listDto, HttpStatus.OK);
		}
	}
	
	/**
	 * 
	 * overviews: get student by id
	 * 
	 * @param id
	 * @return student
	 */
	@GetMapping("/studentById/{id}")
	ResponseEntity<Datacustomer<Student>> getById(@PathVariable String id) { // get student by id
		// get student by id
		Student one = studentDao.FindOne(id); 
		if (one == null) {
			return new ResponseEntity<Datacustomer<Student>>(new Datacustomer<Student>(null, "msg.E002"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<Datacustomer<Student>>(new Datacustomer<Student>(one, "msg.I001"),
					HttpStatus.OK);
		}
	}
	
	/**
	 * 
	 * overviews: get list class
	 * 
	 * @return list Student
	 */
	@GetMapping("/getStudentclass")
	ResponseEntity<?> getALLclass() {		
		if (classervice.getListClass() == null) { // get list student
			return new ResponseEntity<String>("msg.E002", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Class>>(classervice.getListClass(), HttpStatus.OK);
		}
	}
	
	
	/**
	 * 
	 * overviews: delete student by id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/DeleteStudent/{id}")
	ResponseEntity<Datacustomer<String>> Delete(@PathVariable String id) { //delete student
			// check student exist in DB
		    Student one  = studentDao.FindOne(id); 
		    // delete account with student id
		    AccountDao.deleteAccoutbyIdStudent(one.getId());
		    // delete student
			studentDao.deleteById(id); 
			return new ResponseEntity<Datacustomer<String>>(new Datacustomer<String>("ok", "msg.I001"),HttpStatus.OK);
	}
	
	/**
	 * 
	 * overviews: update student
	 * 
	 * 
	 * @param student
	 * @return
	 * @throws MaSvexection
	 * @throws PhoneExeption
	 * @throws GmialExeption
	 * @throws StudentIDException
	 */
	@PostMapping("/updateStdent") // Api save student
	ResponseEntity<Datacustomer<Student>> update(@RequestBody Student student)
			throws MaSvexection, PhoneExeption, GmialExeption, StudentIDException {
			// check student is exist in DB
		if(studentDao.FindOne(student.getStudent_Id())==null) { 
			throw new StudentIDException(student.getStudent_Id());
		}
		 // Check Email and phone in DB
		if (studentService.checkEmailUpdate(student) && studentService.checkPhoneUpdate(student)) {
			// update student
			studentDao.updateStudent(student); 
			return new ResponseEntity<Datacustomer<Student>>(new Datacustomer<Student>(student, "msg.I001"),
					HttpStatus.OK);
		} else {
			// email not in DB and Phone in DB
			if (studentService.checkEmailUpdate(student) == false && studentService.checkPhoneUpdate(student) == true) { 
				// check gmail exist in DB
				if (studentService.checkGmail(student.getGmail())) {
					throw new GmialExeption(student.getGmail());
				} else {
					// update student
					studentDao.updateStudent(student); 
					return new ResponseEntity<Datacustomer<Student>>(new Datacustomer<Student>(student, "msg.I001"),
							HttpStatus.OK);
				}
			}
			if (studentService.checkEmailUpdate(student) == true && studentService.checkPhoneUpdate(student) == false) { // name student not in DB AND phone in DB
				if (studentService.checkPhone(student.getPhonenumber())) {
					throw new PhoneExeption(student.getPhonenumber());
				} else {
					// update student
					studentDao.updateStudent(student); 
					return new ResponseEntity<Datacustomer<Student>>(new Datacustomer<Student>(student, "msg.I001"),
							HttpStatus.OK);
				}
			}
			if (studentService.checkEmailUpdate(student) == false
					&& studentService.checkPhoneUpdate(student) == false) {
				if (studentService.checkGmail(student.getGmail())) {
					throw new GmialExeption(student.getGmail());
				} else {
					if (studentService.checkPhone(student.getPhonenumber())) {
						throw new PhoneExeption(student.getPhonenumber());
					} else {
						// update Student
						studentDao.updateStudent(student); 
						return new ResponseEntity<Datacustomer<Student>>(
								new Datacustomer<Student>(student, "msg.I001"), HttpStatus.OK);
					}
				}
			}
		}
		return null;
	}

}
