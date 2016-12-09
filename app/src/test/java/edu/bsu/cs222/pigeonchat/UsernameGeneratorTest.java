package edu.bsu.cs222.pigeonchat;

import org.junit.Assert;
import org.junit.Test;

public class UsernameGeneratorTest {
    private UsernameGenerator truncator;
    private String VALID_EMAIL = "email@website.com";
    private String USERNAME = "email";

    @Test
    public void testTruncateCharactersAfter() {
        truncator = new UsernameGenerator("0123456789");
        Assert.assertEquals("012345", truncator.TruncateCharactersAfter(6));
    }

    @Test
    public void testFindPositionOfAtSymbol() {
        truncator = new UsernameGenerator(VALID_EMAIL);
        Assert.assertEquals(5, truncator.positionOfAtSymbol());
    }

    @Test
    public void testUsernameGeneratedByEmail() {
        truncator = new UsernameGenerator(VALID_EMAIL);
        Assert.assertTrue(truncator.GenerateUsername().equals(USERNAME));
    }
}
