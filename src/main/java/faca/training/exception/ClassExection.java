package faca.training.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import faca.training.customer.Datacustomer;
import faca.training.customer.ErrorObject;
import faca.training.entitis.Class;

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
@ControllerAdvice
public class ClassExection {
	/**
	 * overviews: handler exception bindException
	 * 
	 * @param bindException
	 * @return Datacustomer
	 */
  @ExceptionHandler(value =BindException.class)
  ResponseEntity<?> validator(BindException bindException){ // handler exception bindexception
	  List<ErrorObject> errors = bindException.getBindingResult().getFieldErrors().stream()
              .map(x -> new ErrorObject(x.getField(), x.getDefaultMessage())).collect(Collectors.toList());
	  Datacustomer<Class> data = new Datacustomer<Class>("that bai", errors, null);
	  return new ResponseEntity<Datacustomer<Class>>(data,HttpStatus.BAD_REQUEST); 
  }
  
  /**
   * overviews: handler exception IDExection
   * 
   * @param IDExection
   * @return Datacustomer
   */
  @ExceptionHandler(value =IDExection.class)
  ResponseEntity<?> Idexeption(IDExection IDExection){ // handler exception IDExection
	  ErrorObject er = new ErrorObject("msg.E002", IDExection.getMessage());
	  List<ErrorObject> list = new ArrayList<>();
	  list.add(er);
	  Datacustomer<Class> data = new Datacustomer<Class>(null, list, null);
	  return new ResponseEntity<Datacustomer<Class>>(data,HttpStatus.BAD_REQUEST); 
  }
  
  /**
   * overviews: handler exception NameClassException
   * 
   * @param NameClassException
   * @return Datacustomer
   */
  
  @ExceptionHandler(value =NameClassException.class) 
  //handler exception NameClassException
  ResponseEntity<?> NameClass(NameClassException NameClassException){
	  ErrorObject er = new ErrorObject("msg.E011", NameClassException.getMessage());
	  List<ErrorObject> list = new ArrayList<>();
	  list.add(er);
	  Datacustomer<Class> data = new Datacustomer<Class>(null, list, null);
	  return new ResponseEntity<Datacustomer<Class>>(data,HttpStatus.BAD_REQUEST); 
  }
  
  /**
   * overviews: handler exception ClassIsUse
   * 
   * @param ClassIsUse
   * @return Datacustomer
   */
  @ExceptionHandler(value =ClassIsUse.class)
  //handler exception ClassIsUse
  ResponseEntity<?> NameClassisUsing(ClassIsUse ClassIsUse){ 
	  ErrorObject er = new ErrorObject("Name_Class", ClassIsUse.getMessage());
	  List<ErrorObject> list = new ArrayList<>();
	  list.add(er);
	  Datacustomer<Class> data = new Datacustomer<Class>(null, list, null);
	  return new ResponseEntity<Datacustomer<Class>>(data,HttpStatus.BAD_REQUEST); 
  }
  
  /**
   * overviews: handler exception MaSvexection
   * 
   * @param MaSvexection
   * @return Datacustomer
   */
  @ExceptionHandler(value =MaSvexection.class)
  //handler exception MaSvexection 
  ResponseEntity<?> MaSV(MaSvexection MaSvexection){ 
	  ErrorObject er = new ErrorObject("msgE004", MaSvexection.getMessage());
	  List<ErrorObject> list = new ArrayList<>();
	  list.add(er);
	  Datacustomer<Class> data = new Datacustomer<Class>(null, list, null);
	  return new ResponseEntity<Datacustomer<Class>>(data,HttpStatus.BAD_REQUEST); 
  }
  
  /**
   * overviews: handler exception PhoneExeption
   * 
   * @param PhoneExeption
   * @return Datacustomer
   */
  @ExceptionHandler(value =PhoneExeption.class)
  //handler exception PhoneExeption
  ResponseEntity<?> Phone(PhoneExeption PhoneExeption){  
	  ErrorObject er = new ErrorObject("msgE005", PhoneExeption.getMessage());
	  List<ErrorObject> list = new ArrayList<>();
	  list.add(er);
	  Datacustomer<Class> data = new Datacustomer<Class>(null, list, null);
	  return new ResponseEntity<Datacustomer<Class>>(data,HttpStatus.BAD_REQUEST); 
  }
  
  /**
   * overviews: handler exception GmialExeption
   * 
   * @param GmialExeption
   * @return Datacustomer
   */
  @ExceptionHandler(value =GmialExeption.class)
  // handler exception GmialExeption
  ResponseEntity<?> Gmial(GmialExeption GmialExeption){  
	  ErrorObject er = new ErrorObject("msgE006", GmialExeption.getMessage());
	  List<ErrorObject> list = new ArrayList<>();
	  list.add(er);
	  Datacustomer<Class> data = new Datacustomer<Class>(null, list, null);
	  return new ResponseEntity<Datacustomer<Class>>(data,HttpStatus.BAD_REQUEST); 
  }
  
  /**
   * overviews: handler exception validate
   * 
   * @param StudentIDException
   * @return Datacustomer
   */
  @ExceptionHandler(value =StudentIDException.class) 
  //handler exception StudentIDException
  ResponseEntity<?> idStudentException(StudentIDException StudentIDException){
	  ErrorObject er = new ErrorObject("ID_invalite", StudentIDException.getMessage());
	  List<ErrorObject> list = new ArrayList<>();
	  list.add(er);
	  Datacustomer<Class> data = new Datacustomer<Class>(null, list, null);
	  return new ResponseEntity<Datacustomer<Class>>(data,HttpStatus.BAD_REQUEST); 
  }
  
  /**
   * overviews: handler exception AccountExetion
   * 
   * @param AccountExetion
   * @return Datacustomer
   */
  @ExceptionHandler(value =AccountExetion.class)
  //handler exception StudentIDException
  ResponseEntity<?> account(AccountExetion AccountExetion){  
	  ErrorObject er = new ErrorObject("account", AccountExetion.getMessage());
	  List<ErrorObject> list = new ArrayList<>();
	  list.add(er);
	  Datacustomer<Class> data = new Datacustomer<Class>(null, list, null);
	  return new ResponseEntity<Datacustomer<Class>>(data,HttpStatus.BAD_REQUEST); 
  }
  
  /**
   * overviews: handler exception memberException
   * 
   * @param memberException
   * @return Datacustomer
   */
  @ExceptionHandler(value =memberException.class) 
 //handler memberexception
  ResponseEntity<?> checkMenber(memberException memberException){
	  ErrorObject er = new ErrorObject("msgE001", memberException.getMessage());
	  List<ErrorObject> list = new ArrayList<>();
	  list.add(er);
	  Datacustomer<Class> data = new Datacustomer<Class>(null, list, null);
	  return new ResponseEntity<Datacustomer<Class>>(data,HttpStatus.BAD_REQUEST); 
  }
}
