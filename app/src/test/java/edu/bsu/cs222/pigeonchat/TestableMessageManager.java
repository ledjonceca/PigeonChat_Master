package edu.bsu.cs222.pigeonchat;

/**
 * Created by brian on 11/20/2016.
 */

public class TestableMessageManager implements MessageManager{

    private Pushable pusher = new TestablePusher();

    public void sendMessage(String testMessage) {
        pusher.push(testMessage);
    }

    public String getNewMessage(){
        return this.pusher.getMessage();
    }

}
