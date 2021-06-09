package faca.training.customer;

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
public class ErrorObject {
 private String column; // column error
 private String msserror; // masage error
public String getColumn() {
	return column;
}
public void setColumn(String column) {
	this.column = column;
}
public String getMsserror() {
	return msserror;
}
public void setMsserror(String msserror) {
	this.msserror = msserror;
}
public ErrorObject(String column, String msserror) {
	super();
	this.column = column;
	this.msserror = msserror;
}

}
