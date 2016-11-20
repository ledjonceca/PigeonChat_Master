package edu.bsu.cs222.pigeonchat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageRelayTest {
    private MessageRelay msgManager = new TestableMessageRelay();
    @Test
    public void testMessagePushed(){
        msgManager.sendMessage("hello world");
        assertEquals("hello world", msgManager.getNewMessage());
    }
    @Test
    public void testMessageUpdates(){
        msgManager.sendMessage("goodbye world");
        assertEquals("goodbye world", msgManager.getNewMessage());
    }
}
