package faca.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faca.training.dao.StudentDao;
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
public class StudentService {
  @Autowired
  StudentDao studentDao;
  
  /**
   * 
   * @param phone
   * @return
   */
  public boolean checkPhone(String phone) { // check phone exsist in DB
	  List<Student> list = studentDao.getListStudent();
	  for (Student student : list) {
		if(phone.equals(student.getPhonenumber())) {
			return true;
		}
	 }
	  return false;
  }
  
  /**
   * 
   * @param masv
   * @return
   */
  public boolean checkMasv(String masv) { // check maSv exsist in DB
	  List<Student> list = studentDao.getListStudent(); // get list student
	  for (Student student : list) {
		if(masv.equals(student.getStudent_Id())) {
			return true;
		}
	 }
	  return false;
  }
  
  /**
   * 
   * @param gmail
   * @return
   */
  public boolean checkGmail(String gmail) { // check gmail exist in DB
	  List<Student> list = studentDao.getListStudent(); // get list student
	  for (Student student : list) {
		if(gmail.equals(student.getGmail())) {
			return true;
		}
	 }
	  return false;
  }
  
  /**
   * 
   * @param one
   * @return
   */
  public boolean checkPhoneUpdate(Student one) { // check phone exist in DB
	  Student DB = studentDao.FindOne(one.getStudent_Id());
	  if(one.getPhonenumber().equals(DB.getPhonenumber())) {
		  return true;
	  }else {
		  return false;
	  }
  }
  
  /**
   * 
   * @param one
   * @return
   */
  public boolean checkEmailUpdate(Student one) { // check gmail exist in DB
	  Student DB = studentDao.FindOne(one.getStudent_Id());
	  if(one.getGmail().equals(DB.getGmail())) {
		  return true;
	  }else {
		  return false;
	  }
  }

}
