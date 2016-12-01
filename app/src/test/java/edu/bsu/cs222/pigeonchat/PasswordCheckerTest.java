package edu.bsu.cs222.pigeonchat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PasswordCheckerTest {
    private PasswordChecker checker;
    @Test
    public void samePasswords(){
        checker = new PasswordChecker("password","password");
        assertEquals(true, checker.comparePasswords());
    }

    @Test
    public void differentPasswords(){
        checker = new PasswordChecker("joelsucks","password");
        assertEquals(false, checker.comparePasswords());
    }
}
