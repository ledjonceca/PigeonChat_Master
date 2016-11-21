package edu.bsu.cs222.pigeonchat;

public class TestableMessageRelay implements MessageRelay {

    private TestablePusher pusher = new TestablePusher();

    public void sendMessage(String testMessage) {
        pusher.push(testMessage);
    }

    public String getNewMessage(){
        return this.pusher.getMessage();
    }

}
