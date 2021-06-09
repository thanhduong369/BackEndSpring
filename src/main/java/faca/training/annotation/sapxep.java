package faca.training.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class sapxep implements ConstraintValidator<no, String> {
	public  int indexa;
    @Override
    public void initialize(no constraintAnnotation) {
    	this.indexa = constraintAnnotation.index();
    }
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return false;
	}

}
