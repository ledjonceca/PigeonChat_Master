package edu.bsu.cs222.pigeonchat;


import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class EmailValidatorTest {
    private String VALID_EMAIL = "email@website.com";
    private String ONLY_PERIOD = "website.com";
    private String ONLY_AT_SYMBOL = "email@com";
    private String NOT_AN_EMAIL = "username";
    private EmailValidator validEmail = new EmailValidator(VALID_EMAIL);
    private EmailValidator hasAPeriod = new EmailValidator(ONLY_PERIOD);
    private EmailValidator hasAnAtSymbol = new EmailValidator(ONLY_AT_SYMBOL);
    private EmailValidator username = new EmailValidator(NOT_AN_EMAIL);

    @Test
    public void testUsernameHasNoAtSymbol() {
        assertFalse(username.emailContainsAtSymbol());
    }

    @Test
    public void testValidEmailHasAnAtSymbol() {
        assertTrue(validEmail.emailContainsAtSymbol());
    }

    @Test
    public void testUsernameHasNoPeriod() {
        assertFalse(username.emailContainsPeriod());
    }

    @Test
    public void testValidEmailHasAPeriod() {
        assertTrue(validEmail.emailContainsPeriod());
    }

    @Test
    public void testOnlyAtSymbolIsInsufficient() {
        assertFalse(hasAnAtSymbol.emailContainsProperSymbols());
    }

    @Test
    public void testOnlyPeriodIsInsufficient() {
        assertFalse(hasAPeriod.emailContainsProperSymbols());
    }

    @Test
    public void testValidEmailContainsProperSymbols() {
        assertTrue(validEmail.emailContainsProperSymbols());
    }
}
