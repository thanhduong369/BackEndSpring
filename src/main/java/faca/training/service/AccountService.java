package faca.training.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faca.training.dao.AccountDao;
import faca.training.dao.StudentDao;
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
public class AccountService {
	@Autowired
	StudentDao studentDao;

	@Autowired
	AccountDao accountDao;
	
	/**
	 * 
	 * @return list Student
	 */
	public List<Student> getlistStudent() { // Get list student don't have account
		int count = 0;
		List<Student> list = studentDao.getListStudent();
		List<Account> listAccount = accountDao.getAll();
		List<Student> listStudent = new ArrayList<>();
		for (Student student : list) {
			for (Account account : listAccount) {
				if (student.getId() == account.getStudent().getId()) {
					count++;
				}
			}
			if (count == 0) {
				listStudent.add(student);
				count = 0;
			}
			count = 0;
		}
		return listStudent;
	}
	
	/**
	 * 
	 * @param account
	 * @return boolean
	 */
	public boolean checkAccount(String account) { // check account exist in DB
		List<Account> listAccount = accountDao.getAll();
		if (listAccount.size() == 0) { //list account is null return true
			return true;
		}
		String accountUsername = account.replaceAll("\\s+", ""); // replaceall space
		for (Account accounta : listAccount) {
			String data = accounta.getUser_name().replaceAll("\\s+", ""); // replaceall space
			if (accountUsername.toLowerCase().equals(data.toLowerCase())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param account
	 * @return booean
	 */
	public boolean checkUsername(Account account) { // check username in DB
		Account acDB = accountDao.findById(account.getId());
		String usernameDB = acDB.getUser_name().replaceAll("\\s+", ""); // replace all " "
		String username = account.getUser_name().replaceAll("\\s+", "");
		if (usernameDB.toLowerCase().equals(username.toLowerCase())) { //to LowerCase name current name and name in DB
			return true;
		} else {
			return false;
		}
	}

}
