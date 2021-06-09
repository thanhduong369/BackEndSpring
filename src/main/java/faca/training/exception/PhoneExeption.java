package faca.training.exception;

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
public class PhoneExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public PhoneExeption(String id) {
		super("Id\t" + id + "\tis exist !!");
	}
}
