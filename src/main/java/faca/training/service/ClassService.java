package faca.training.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faca.training.dao.ClassDao;
import faca.training.dao.StudentDao;
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
public class ClassService {
	@Autowired
	ClassDao daoClassDao;

	@Autowired
	StudentDao student;
	
	/**
	 * 
	 * @param txttimkiem
	 * @return boolean
	 */
	public boolean checkso(String txttimkiem) { //check string is number
		try {
			int a = Integer.parseInt(txttimkiem);
			System.out.println(a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return boolean
	 */
	public boolean checkNameExit(String name) { //check
		String nameclass = name.replaceAll("\\s+", "");
		List<Class> list = daoClassDao.getAll();
		for (Class class1 : list) {
			String a = class1.getName_class().replaceAll("\\s+", "");
			if (nameclass.toLowerCase().equals(a.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param one
	 * @return boolean
	 */
	public boolean checkname(Class one) { // check name  exist in DB
		Class data = daoClassDao.findBymalop(one.getMalop());
		String nameclass = data.getName_class().replaceAll("\\s+", "");
		if (one.getName_class().replaceAll("\\s+", "").toLowerCase().equals(nameclass.toLowerCase())) {
			return true;
		} else {
			return false;
		}
		// return true;
	}
	
	/**
	 * 
	 * @param number
	 * @param id
	 * @return boolean
	 */
	public boolean checknemBer(int number, int id) { // check member exsit in DB
		List<Student> list = daoClassDao.getListStudentbyit(id);
		if (list.size() == 0) { // check size
			return true;
		} else if (list.size() < number) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return int 
	 */
	public int getNumber(int id) { // check phone in DB
		List<Student> list = daoClassDao.getListStudentbyit(id);
		return list.size();
	}
	
	/**
	 * 
	 * @return list Class
	 */
	public List<Class> getListClass() {// get list student
		List<Class> liss = daoClassDao.getAll(); // get list Student
		List<Class> listShow = new ArrayList<>();
		for (Class class1 : liss) {
			if (checknemBer(class1.getMem_ber(), class1.getId())) {
				listShow.add(class1);
			}
		}
		return listShow;
	}
	
	/**
	 * 
	 * @param file
	 * @return boolean
	 */
	public boolean checkFileDto(Class file) {//validate file 
		String name = "^[a-zA-Z0-9\\s]*$";
		String malop = "^((LT)|(DH))+[0-9]{5}$";
		if (Pattern.matches(name, file.getName_class()) && Pattern.matches(malop, file.getMalop()) && file.getMem_ber() >= 1
				&& file.getMem_ber() <= 100) { // check file validate
			return true;
		}
		return false;
	}
    
	/**
	 * 
	 * @param malop
	 * @return booean
	 */
	public boolean checkudpate(String malop) { // check malop exsit in DB
		Class one = daoClassDao.findBymalop(malop); //get class by malop
		if (one != null) {
			return true;
		}
		return false;
	}

}
