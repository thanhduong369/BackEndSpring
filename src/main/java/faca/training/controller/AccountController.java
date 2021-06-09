package faca.training.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import faca.training.customer.Datacustomer;
import faca.training.dao.AccountDao;
import faca.training.dto.AccountDto;
import faca.training.entitis.Account;
import faca.training.entitis.Student;
import faca.training.exception.AccountExetion;
import faca.training.service.AccountService;

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
public class AccountController {
	@Autowired
	AccountDao accountDao; 

	@Autowired
	AccountService accoutservice;
	
	/**
	 * 
	 * overviews: add new account
	 * 
	 * @param Account
	 * @param result
	 * @return
	 * @throws AccountExetion
	 */
	@PostMapping("/saveAccount") // save account to DB
	ResponseEntity<Datacustomer<Account>> save(@Valid @RequestBody Account Account, BindingResult result)
			throws AccountExetion {
		if (!accoutservice.checkAccount(Account.getUser_name())) {
			// check account exist in DB
			throw new AccountExetion(Account.getUser_name()); 
		}
		accountDao.insert(Account);
		return new ResponseEntity<Datacustomer<Account>>(new Datacustomer<Account>(Account, "msg.I001"),
				HttpStatus.OK);
	}
	
	/**
	 * 
	 * overviews: get all account
	 * 
	 * @return
	 */
	@GetMapping("/getallAccount") // get list Account from DB
	ResponseEntity<?> getALLAccount() {
		List<Account> list = accountDao.getAll();
		List<AccountDto> listDto = new ArrayList<>();
		// Map account entitis with account Dto
		for (Account account : list) {
			AccountDto one = new AccountDto();
			one.setId(account.getId());
			one.setStudentName(account.getStudent().getName());
			one.setUser_name(account.getUser_name());
			one.setPassword(account.getPassword());
			listDto.add(one);
		}
		return new ResponseEntity<List<AccountDto>>(listDto, HttpStatus.OK);
	}
	
	/**
	 * 
	 * overviews: sheare account by txtimkiem
	 * 
	 * @param txttimkiem
	 * @return
	 */
	@GetMapping("/timkiemaccount/{txttimkiem}") // get list Account from DB
	ResponseEntity<?> timkiem(@PathVariable String txttimkiem) {
		List<Account> list = accountDao.Timkiem(txttimkiem);
		// Map account entitis with account Dto
		List<AccountDto> listDto = new ArrayList<>(); 
		if(list.size()!=0) {
			for (Account account : list) {
				AccountDto one = new AccountDto();
				one.setId(account.getId());
				one.setStudentName(account.getStudent().getName());
				one.setUser_name(account.getUser_name());
				one.setPassword(account.getPassword());
				listDto.add(one);
			}
		}
		
		return new ResponseEntity<List<AccountDto>>(listDto, HttpStatus.OK);
	}
	
	/**
	 * 
	 * overviews: get list student
	 * 
	 * @return
	 */
	@GetMapping("/getLstudent") // get list student to add account
	ResponseEntity<?> getStudentAccount() {
		return new ResponseEntity<List<Student>>(accoutservice.getlistStudent(), HttpStatus.OK);
	}
	
	/**
	 * 
	 * overviews: find account by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/FindAccount/{id}")
	ResponseEntity<Datacustomer<Account>> getById(@PathVariable int id) {
		// Class classs = daoClassDao.findById(id);
		if (accountDao.findById(id) == null) {
			return new ResponseEntity<Datacustomer<Account>>(new Datacustomer<Account>(null, "msg.I001"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<Datacustomer<Account>>(
					new Datacustomer<Account>(accountDao.findById(id), "msg.I001"), HttpStatus.OK);
		}
	}
	
	/**
	 * overviews: delete account byid
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteAccount/{id}")
	ResponseEntity<Datacustomer<String>> Delete(@PathVariable int id) {//delete account by id
		accountDao.deleteAccountByid(id);
		return new ResponseEntity<Datacustomer<String>>(new Datacustomer<String>(null, "msg.I001"), HttpStatus.OK);

	}
	
	/**
	 * overviews: update account
	 * 
	 * @param Account
	 * @return
	 * @throws AccountExetion
	 */
	@PostMapping("/updateAccount") // update account 
	ResponseEntity<Datacustomer<Account>> updateAccount(@RequestBody Account Account)
			throws AccountExetion {
		//update account
		accountDao.updateAccount(Account);
		return new ResponseEntity<Datacustomer<Account>>(new Datacustomer<Account>(Account, "msg.I001"),
		HttpStatus.OK);
	}

}
