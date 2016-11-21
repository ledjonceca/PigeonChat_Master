package edu.bsu.cs222.pigeonchat;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailTruncatorTest {
    private EmailTruncator truncator;
    @Test
    public void validEmail(){
        truncator = new EmailTruncator("jdlevy95@gmail.com");
        assertEquals("jdlevy95", truncator.truncate());
    }
    @Test
    public void validEmailMultiplePeriods(){
        truncator = new EmailTruncator("user.name@website.com");
        assertEquals("user.name", truncator.truncate());}

    @Test
    public void invalidEmailNoAt(){
        truncator = new EmailTruncator("bob.com");
        assertEquals(null, truncator.truncate());
    }
    @Test
    public void multipleAt(){
        truncator = new EmailTruncator("@@bob.@@@");
        assertEquals("@@bob.@@", truncator.truncate());}
    @Test
    public void multipleAtAndPeriod() {
        truncator = new EmailTruncator("joel.d@vid.levy@gmail.com");
        assertEquals("joel.d@vid.levy", truncator.truncate());}

}
