package faca.training.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator  implements ConstraintValidator<AnnotationMax, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return false;
	}

}
