package cs222.bsu.edu.pigeonchat;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailTruncatorTest {
    EmailTruncator truncator = new EmailTruncator();

    @Test
    public void validEmail(){
        assertEquals("jdlevy95", truncator.truncate("jdlevy95@gmail.com"));
    }
    @Test
    public void validEmailMultiplePeriods(){assertEquals("user.name", truncator.truncate("user.name@website.com"));}
    @Test
    public void invalidEmailNoAt(){
        assertEquals(null, truncator.truncate("bob.com"));
    }
    @Test
    public void multipleAt(){assertEquals("@@bob.@@", truncator.truncate("@@bob.@@@"));}
    @Test
    public void multipleAtAndPeriod() {assertEquals("joel.d@vid.levy", truncator.truncate("joel.d@vid.levy@gmail.com"));}

}
