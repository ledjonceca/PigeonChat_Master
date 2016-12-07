package edu.bsu.cs222.pigeonchat;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class PasswordStrengthCheckerTest {
    private PasswordStrengthChecker checker;

    @Test public void testZeroNumbers(){
        checker = new PasswordStrengthChecker("password");
        assertFalse(checker.hasANumber());
    }

    @Test public void testOneNumber(){
        checker = new PasswordStrengthChecker("passw0rd");
        assertTrue(checker.hasANumber());
    }

    @Test public void testMultipleNumbers(){
        checker = new PasswordStrengthChecker("p2as1sw0r6d7");
        assertTrue(checker.hasANumber());
    }

    @Test public void testZeroSpecialChars(){
        checker = new PasswordStrengthChecker("password");
        assertFalse(checker.hasASpecialChar());
    }

    @Test public void testOneSpecialChar(){
        checker = new PasswordStrengthChecker("p@sswrd");
        assertTrue(checker.hasASpecialChar());
    }

    @Test public void testMultipleSpecialChars(){
        checker = new PasswordStrengthChecker("pa%s)s(wo^rd!");
        assertTrue(checker.hasASpecialChar());
    }

    @Test public void testFiveCharactersTooShort(){
        checker = new PasswordStrengthChecker("12345");
        assertFalse(checker.hasEnoughCharcters());
    }

    @Test public void testEightCharactersIsGood(){
        checker = new PasswordStrengthChecker("12345678");
        assertTrue(checker.hasEnoughCharcters());
    }

    @Test public void testWeakPassword() {
        checker = new PasswordStrengthChecker("password");
        assertEquals("weak", checker.checkStrength());
    }

    @Test public void testMediumPassword(){
        checker = new PasswordStrengthChecker("passw0rd");
        assertEquals("medium", checker.checkStrength());
    }

    @Test public void testStrongPassword(){
        checker = new PasswordStrengthChecker("p@ssw0rd");
        assertEquals("strong", checker.checkStrength());
    }
}
