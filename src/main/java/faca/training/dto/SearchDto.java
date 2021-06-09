package faca.training.dto;
/**
 * 
 * @author DuongDT 19
 * 
 * @version 1.0
 * 
 * @Date 2/6/2021
 * 
 * 
 * @Modification Logs:
 * 
 * @Date 				@AUTHOR 							@DESCRIPTION
 * -----------------------------------------------------------------------
 * 2/6/2021			    DuongDT19							Create
 *
 */
public class SearchDto {
	private String txtTimkiem;
	private int idClass;

	public String getTxtTimkiem() {
		return txtTimkiem;
	}

	public void setTxtTimkiem(String txtTimkiem) {
		this.txtTimkiem = txtTimkiem;
	}

	public int getIdClass() {
		return idClass;
	}

	public void setIdClass(int idClass) {
		this.idClass = idClass;
	}

}
