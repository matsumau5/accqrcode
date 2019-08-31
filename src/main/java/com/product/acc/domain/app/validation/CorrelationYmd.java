package com.product.acc.domain.app.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CorrelationYmd.YmdValidation.class})
public @interface CorrelationYmd {
    
    String message() default "";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload> [] payload() default {};
    
    String gengoField();
    String[] dateFields();
    String showField();
    String word();
    
    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CorrelationYmd[] value();
    }
    
    public class YmdValidation implements ConstraintValidator<CorrelationYmd, Object> {
        
        private String gengoField;
        private String[] dateFields;
        private String showField;
        private String word;
        private String message;
        private String defaultMessageId = "";
        private String showMessage = "";
        
        @Override
        public void initialize(CorrelationYmd constraintAnnotation) {
            
        }
        
        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(value);
            String gengo = "seireki";
            if (StringUtils.isNotEmpty(gengoField)) {
                gengo = (String)beanWrapper.getPropertyValue(gengoField);
            }
            String[] formDate = Arrays.copyOf(Arrays.stream(dateFields)
                    .map(beanWrapper::getPropertyValue)
                    .toArray(), dateFields.length, String[].class);
            String year = formDate[0];
            String month = formDate[1];
            String day = formDate[2];
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(showMessage)
                .addPropertyNode(showField)
                .addConstraintViolation();
            
            if (!Arrays.stream(formDate).allMatch(StringUtils::isBlank)
                    && !Arrays.stream(formDate).allMatch(StringUtils::isNotBlank)) {
                return false;
            } else {
                // TODO 日付チェック
                return true;
            }
        }
    }
}
