package jeiu.imsad.validation;

import jeiu.imsad.domain.Partner;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Partner partner = new Partner();
        partner.setCeo("조영준");
        partner.setCompany("");

        Set<ConstraintViolation<Partner>> violations = validator.validate(partner);
        for (ConstraintViolation<Partner> violation : violations) {
            System.out.println("violation = " + violation);
            System.out.println("violation = " + violation.getMessage());
        }
    }
}
