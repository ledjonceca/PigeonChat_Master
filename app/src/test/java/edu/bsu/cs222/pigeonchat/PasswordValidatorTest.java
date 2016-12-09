package edu.bsu.cs222.pigeonchat;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


public class PasswordValidatorTest {
    private String SHORT_PASSWORD = "12345";
    private String LONG_PASSWORD = "12345abc";
    private PasswordValidator shortPasswordValidator = new PasswordValidator(SHORT_PASSWORD);
    private PasswordValidator longPasswordValidator = new PasswordValidator(LONG_PASSWORD);

    @Test
    public void testShortPasswordIsNotLongEnough() {
        assertFalse(shortPasswordValidator.passwordIsLongEnough());
    }

    @Test
    public void testLongPasswordIsLongEnough() {
        assertTrue(longPasswordValidator.passwordIsLongEnough());
    }

    @Test
    public void testPasswordsDoNotMatch() {
        assertFalse(longPasswordValidator.passwordsAreTheSame(SHORT_PASSWORD));
    }

    @Test
    public void testPasswordsDoMatch() {
        assertTrue(longPasswordValidator.passwordsAreTheSame(LONG_PASSWORD));
    }

}
