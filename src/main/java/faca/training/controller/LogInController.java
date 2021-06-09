package faca.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import faca.training.customer.Datacustomer;
import faca.training.dao.AccountDao;
import faca.training.entitis.Account;
import faca.training.exception.ClassIsUse;
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
public class LogInController {
	@Autowired
    AccountDao accountDao;
	
	/**
	 * overviews: check account in DB
	 * 
	 * 
	 * @param Account
	 * @return
	 * @throws ClassIsUse
	 */
	@PostMapping("/checkAccount")
 	ResponseEntity<Datacustomer<String>> Delete(@RequestBody Account Account) throws ClassIsUse {//check login
		// chcek account in DB
	  	Account account = accountDao.checkAccount(Account); 
	  	if(account==null) {
	  		return new ResponseEntity<Datacustomer<String>>(new Datacustomer<String>("msg.E004", "msg.E004"),HttpStatus.OK);		
	  	}else {
	  		return new ResponseEntity<Datacustomer<String>>(new Datacustomer<String>("msg.I001", "msg.E004"),HttpStatus.OK);
	  	}
	 	
	}
}
