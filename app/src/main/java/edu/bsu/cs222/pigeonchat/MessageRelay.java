package edu.bsu.cs222.pigeonchat;

public interface MessageRelay {

    void sendMessage(String input);

    String getNewMessage();

}
