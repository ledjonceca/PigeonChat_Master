package edu.bsu.cs222.pigeonchat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageRelayTest {
    private MessageRelay messageRelay = new TestableMessageRelay();
    @Test
    public void testMessagePushed(){
        messageRelay.sendMessage("hello world");
        assertEquals("hello world", messageRelay.getNewMessage());
    }
    @Test
    public void testMessageUpdates(){
        messageRelay.sendMessage("goodbye world");
        assertEquals("goodbye world", messageRelay.getNewMessage());
    }
}
