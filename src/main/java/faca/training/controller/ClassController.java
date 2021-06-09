package faca.training.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import faca.training.customer.Datacustomer;
import faca.training.dao.ClassDao;
import faca.training.dao.StudentDao;
import faca.training.dto.SearchDto;
import faca.training.dto.StudentDto;
import faca.training.entitis.Class;
import faca.training.entitis.Student;
import faca.training.exception.ClassIsUse;
import faca.training.exception.IDExection;
import faca.training.exception.NameClassException;
import faca.training.exception.memberException;
import faca.training.service.ClassService;

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
public class ClassController {

	@Autowired
	ClassDao daoClassDao;

	@Autowired
	ClassService classservice;

	@Autowired
	StudentDao studentDao;

	/**
	 * overviews: get all class
	 * 
	 * @return List<student>
	 */
	@GetMapping("/getall")
	ResponseEntity<?> getALL() { // get List ALL student
		if (daoClassDao.getAll() == null) {
			return new ResponseEntity<String>("msg.E002", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Class>>(daoClassDao.getAll(), HttpStatus.OK);
		}

	}

	/**
	 * 
	 * overviews: sheare class with txttimkiem
	 * 
	 * @param txttimkiem
	 * @return
	 */
	@GetMapping("/timkkiem/{txttimkiem}")
	ResponseEntity<?> timKiem(@PathVariable String txttimkiem) {
		if (classservice.checkso(txttimkiem)) {// check txttimkiem
			int txttimkiemso = Integer.parseInt(txttimkiem);// seach interger
			return new ResponseEntity<List<Class>>(daoClassDao.timkiemso(txttimkiemso), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Class>>(daoClassDao.timkiemString(txttimkiem), HttpStatus.OK);
		}

	}

	/**
	 * 
	 * overviews: get list join class with student
	 * 
	 * @param malop
	 * @return list StudentDto
	 */
	@GetMapping("/getalljoin/{malop}")
	ResponseEntity<?> getAllJoin(@PathVariable String malop) { 
		List<Class> listClass = daoClassDao.findAllalljoin(malop);
		if (listClass.size() == 0) {
			return new ResponseEntity<List<Class>>(daoClassDao.findAllalljoin(malop), HttpStatus.OK);
		}
		// create list student Dto
		List<StudentDto> listStudentDto = new ArrayList<>();
		// get list student entitis
		List<Student> listentiti = listClass.get(0).getStudent(); 
		// maping entitis to Dto
		for (Student studentEntiti : listentiti) {
			StudentDto one = new StudentDto();
			one.setStudent_Id(studentEntiti.getStudent_Id());
			one.setName(studentEntiti.getName());
			one.setAge(studentEntiti.getAge());
			one.setAdrres(studentEntiti.getAdrres());
			one.setPhonenumber(studentEntiti.getPhonenumber());
			one.setGmail(studentEntiti.getGmail());
			one.setActive(studentEntiti.getActive());
			one.setClassname(listClass.get(0).getName_class());
			//add studentDTO to list studentDTO
			listStudentDto.add(one);
		}
		return new ResponseEntity<List<StudentDto>>(listStudentDto, HttpStatus.OK);
	}
	
	/**
	 * 
	 * overviews: sheare class by txttimkiem 
	 * 
	 * @param malop
	 * @param txttimkiem
	 * @return list Class
	 */
	@GetMapping("/timkiemlop/{malop}/{txttimkiem}")
	ResponseEntity<?> timKiemStudent(@PathVariable String malop, @PathVariable String txttimkiem) {
		// find class by malop
		Class lop = daoClassDao.findBymalop(malop); 
		SearchDto timkiem = new SearchDto();
		timkiem.setTxtTimkiem(txttimkiem);
		timkiem.setIdClass(lop.getId());
		//search student
		List<Student> list = studentDao.timKimeStudent(timkiem); 
		//create list studentDTO
		List<StudentDto> listStudentDto = new ArrayList<>();
		if (list.size() == 0) {
			return new ResponseEntity<List<Class>>(daoClassDao.findAllalljoin(malop), HttpStatus.OK);
		}
		// maping entitis to Dto
		for (Student studentEntiti : list) { 
			StudentDto one = new StudentDto();
			one.setStudent_Id(studentEntiti.getStudent_Id());
			one.setName(studentEntiti.getName());
			one.setAge(studentEntiti.getAge());
			one.setAdrres(studentEntiti.getAdrres());
			one.setPhonenumber(studentEntiti.getPhonenumber());
			one.setGmail(studentEntiti.getGmail());
			one.setActive(studentEntiti.getActive());
			one.setClassname(lop.getName_class());
			// Add studentDto to list studentDto
			listStudentDto.add(one); 
		}
		
		return new ResponseEntity<List<StudentDto>>(listStudentDto, HttpStatus.OK);
	}

	/**
	 * overviews: get list class by id
	 * 
	 * 
	 * @param id
	 * @return Datacustomer<Class>
	 */
	@SuppressWarnings("null")
	@GetMapping("/getByID/{id}")
	ResponseEntity<Datacustomer<Class>> getById(@PathVariable int id) {
		 // find one class with id param
		Class classs = daoClassDao.findById(id);
		// check class null
		if (classs == null) { 
			return new ResponseEntity<Datacustomer<Class>>(new Datacustomer<Class>(null, "msg.I001"), HttpStatus.OK);
		} else {
			return new ResponseEntity<Datacustomer<Class>>(new Datacustomer<Class>(classs, "msg.I001"), 
					HttpStatus.OK);
		}
	}

	/**
	 * overviews: add new class
	 * 
	 * @param classs
	 * @return
	 * @throws NameClassException
	 */
	@PostMapping("/save") // Api save save class
	ResponseEntity<Datacustomer<Class>> save(@RequestBody Class classs) throws NameClassException {
		 // check id exsist
		if (daoClassDao.findBymalop(classs.getMalop()) != null) {
			 // throw IDExection
			throw new IDExection(classs.getId());
		}
		// check name exsidt in DB
		if (classservice.checkNameExit(classs.getName_class())) { 
			// throw NameClassException
			throw new NameClassException(classs.getName_class()); 
		}
		// save class to DB
		daoClassDao.insert(classs); 
		return new ResponseEntity<Datacustomer<Class>>(new Datacustomer<Class>(classs, "msg.I001"), HttpStatus.OK); 
	}

	/**
	 * 
	 * overviews: update class
	 * 
	 * @param classs
	 * @param result
	 * @return Datacustomer<Class>
	 * @throws BindException
	 * @throws NameClassException
	 * @throws memberException
	 */
	@PostMapping("/updateClass")
	ResponseEntity<Datacustomer<Class>> updateClass(@Valid @RequestBody Class classs, BindingResult result)
			throws BindException, NameClassException, memberException { // update class
		Class a = daoClassDao.findById(classs.getId());
		System.out.println(classservice.getNumber(classs.getId()));
		if (classservice.getNumber(classs.getId()) > classs.getMem_ber()) {
			// throw memberException
			throw new memberException();
		}
		if (a == null) {
			// throw memberException
			throw new IDExection(classs.getId());
		}
		if (classservice.checkname(classs)) {
			daoClassDao.updateClass(classs);
			return new ResponseEntity<Datacustomer<Class>>(new Datacustomer<Class>(classs, "msg.I001"),
					HttpStatus.OK);
		} else {
			// check name exsidt in DB
			if (classservice.checkNameExit(classs.getName_class())) { 
				throw new NameClassException(classs.getName_class());
			} else {
				daoClassDao.updateClass(classs);
				return new ResponseEntity<Datacustomer<Class>>(new Datacustomer<Class>(classs, "msg.I001"),
						HttpStatus.OK);
			}
		}
	}

	/**
	 * 
	 * overviews: upload file
	 * 
	 * @param classs
	 * @param result
	 * @return total class upload ok
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@PostMapping("/UploadFile")
	ResponseEntity<Datacustomer<String>> UploadFile(@RequestBody String ListClass)
			throws JsonMappingException, JsonProcessingException { // update class
		int count = 0 ;
		String a = ListClass.substring(9, ListClass.length() - 2).replace("\\", "");
		System.out.println(a);
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Class[] langs = objectMapper.readValue(a, Class[].class);
		for (Class fileDto : langs) {
			 // validate file 
			if (classservice.checkFileDto(fileDto)) {
				 // check updatec
				if (classservice.checkudpate(fileDto.getMalop())) {
					 // check member 
					if (classservice.getNumber(fileDto.getId()) < fileDto.getMem_ber()) {
						// check name in DB
						if (classservice.checkname(fileDto)) {  
							 //check update inDB
							daoClassDao.updateClassbyMalop(fileDto);
							count++; 
						} else {
							// check name exsidt in DB
							if (!classservice.checkNameExit(fileDto.getName_class())) { 
								 daoClassDao.updateClassbyMalop(fileDto);
								 count++;
							}
						}
					}
				} else {
					// save Class
					if (daoClassDao.findBymalop(fileDto.getMalop()) == null
							 // check name and malop exsit in DB
							&& !classservice.checkNameExit(fileDto.getName_class())) {
							daoClassDao.insert(fileDto);
						count++;
					}
				}
			}
		}
		return new ResponseEntity<Datacustomer<String>>(new Datacustomer<String>(count+"", "msg.I001"), HttpStatus.OK);
	}

	/**
	 * 
	 * overviews: delete by id class
	 * 
	 * @param id
	 * @return
	 * @throws ClassIsUse
	 */
	@SuppressWarnings("unused")
	@DeleteMapping("/DeleteByIdClass/{id}")
	ResponseEntity<Datacustomer<String>> delete(@PathVariable int id) throws ClassIsUse {// Delete by id
		//check class in DB
		Class a = daoClassDao.findById(id);
		//	get list Student
		List<Student> ListStudent = daoClassDao.getListStudentbyit(id);
		if (a == null) {
			// throw xection
			throw new IDExection(id); 
		} else {
			if (ListStudent.size() == 0) { 
				 // delete class
				daoClassDao.deleteById(id);
				return new ResponseEntity<Datacustomer<String>>(new Datacustomer<String>("msg.I001", "msg.I001"),
						HttpStatus.OK);
			} else {
				throw new ClassIsUse();
			}
		}
	}
}
