package faca.training.customer;

import java.time.LocalTime;
import java.util.List;

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

public class Datacustomer<T>{
	
	private LocalTime timestamp = LocalTime.now(); // time now
	private String message; // masage 
	private List<ErrorObject> error; // list Error 
	private T data; // Data 
	
	public Datacustomer(T data,String mss) {
		super();
		this.data = data;
		this.message = mss;
	}
	public Datacustomer(LocalTime timestamp, String message, List<ErrorObject> error, T data) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.error = error;
		this.data = data;
	}
	


	public Datacustomer(String message, List<ErrorObject> error, T data) {
		super();
		this.message = message;
		this.error = error;
		this.data = data;
	}


	public void setLocaltime(LocalTime localtime) {
		this.timestamp = localtime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


	public LocalTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalTime timestamp) {
		this.timestamp = timestamp;
	}


	public List<ErrorObject> getError() {
		return error;
	}


	public void setError(List<ErrorObject> error) {
		this.error = error;
	}
	

}
