package edu.bsu.cs222.pigeonchat;

/**
 * Created by brian on 11/20/2016.
 */

public class TestablePusher implements Pushable {

    private String pushedMessage;

    public void push(String input) {
        pushedMessage = input;
    }

    public String getMessage() { return pushedMessage; }

}

