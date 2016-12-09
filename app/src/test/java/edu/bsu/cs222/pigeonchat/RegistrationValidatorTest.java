package edu.bsu.cs222.pigeonchat;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


public class RegistrationValidatorTest {
    private String INVALID_EMAIL = "username";
    private String VALID_EMAIL = "email@website.com";
    private String SHORT_PASSWORD_A = "1234";
    private String SHORT_PASSWORD_B = "abcd";
    private String LONG_PASSWORD_A = "123456";
    private String LONG_PASSWORD_B = "abcdef";
    private RegistrationValidator validator;
    private RegistrationValidator.RegistrationValidatorBuilder invalidPasswords = new RegistrationValidator.RegistrationValidatorBuilder().password(SHORT_PASSWORD_A).confirmPassword(SHORT_PASSWORD_B);
    private RegistrationValidator.RegistrationValidatorBuilder validPasswords = new RegistrationValidator.RegistrationValidatorBuilder().password(LONG_PASSWORD_A).confirmPassword(LONG_PASSWORD_A);

    @Test
    public void testShortMatchingPasswordsAreInvalid() {
        validator = new RegistrationValidator.RegistrationValidatorBuilder().password(SHORT_PASSWORD_A).confirmPassword(SHORT_PASSWORD_B).build();
        assertFalse(validator.passwordIsValid());
    }

    @Test
    public void testLongNonMatchingPasswordsAreInvalid() {
        validator = new RegistrationValidator.RegistrationValidatorBuilder().password(LONG_PASSWORD_A).confirmPassword(LONG_PASSWORD_B).build();
        assertFalse(validator.passwordIsValid());
    }

    @Test
    public void testLongMatchingPasswordsAreValid() {
        validator = new RegistrationValidator.RegistrationValidatorBuilder().password(LONG_PASSWORD_A).confirmPassword(LONG_PASSWORD_A).build();
        assertTrue(validator.passwordIsValid());
    }

    @Test
    public void testInvalidEmailAndInvalidPasswordIsInvalid() {
        validator = invalidPasswords.email(INVALID_EMAIL).build();
        assertFalse(validator.isValid());
    }

    @Test
    public void testInvalidEmailAndValidPasswordIsInvalid() {
        validator = validPasswords.email(INVALID_EMAIL).build();
        assertFalse(validator.isValid());
    }

    @Test
    public void testValidEmailAndInvalidPasswordIsInvalid() {
        validator = invalidPasswords.email(VALID_EMAIL).build();
        assertFalse(validator.isValid());
    }

    @Test
    public void testValidEmailAndValidPassword() {
        validator = validPasswords.email(VALID_EMAIL).build();
        assertTrue(validator.isValid());
    }

}
