package edu.bsu.cs222.pigeonchat;

/**
 * Created by brian on 11/20/2016.
 */

public interface MessageRelay {

    void sendMessage(String input);

    String getNewMessage();

}
