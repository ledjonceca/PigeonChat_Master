package edu.bsu.cs222.pigeonchat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PasswordCheckerTest {
    private PasswordChecker checker;

    @Test public void zeroNumberCount(){
        checker = new PasswordChecker("password");
        assertEquals(0, checker.numberCharacterCount());
    }
    @Test public void oneNumberCount(){
        checker = new PasswordChecker("passw0rd");
        assertEquals(1, checker.numberCharacterCount());
    }

    @Test public void fiveNumberCount(){
        checker = new PasswordChecker("p2as1sw0r6d7");
        assertEquals(5, checker.numberCharacterCount());
    }
    @Test public void oneSpecialCharCount(){
        checker = new PasswordChecker("p@sswrd");
        assertEquals(1, checker.specialCharacterCount());
    }

    @Test public void fiveSpecialCharCount(){
        checker = new PasswordChecker("pa%s)s(wo^rd!");
        assertEquals(5, checker.specialCharacterCount());
    }

    @Test public void zeroSpecialCharCount(){
        checker = new PasswordChecker("password");
        assertEquals(0, checker.specialCharacterCount());
    }

    @Test
    public void testWeakPassword() {
        checker = new PasswordChecker("password");
        assertEquals("weak", checker.checkStrength());
    }

    @Test
    public void testMediumPassword(){
        checker = new PasswordChecker("passw0rd");
        assertEquals("medium", checker.checkStrength());
    }

    @Test
    public void testStrongPassword(){
        checker = new PasswordChecker("p@ssw0rd");
        assertEquals("strong", checker.checkStrength());
    }
}
