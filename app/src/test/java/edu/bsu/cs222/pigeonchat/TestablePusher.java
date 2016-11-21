package edu.bsu.cs222.pigeonchat;

public class TestablePusher implements Pushable {

    private String pushedMessage;

    public void push(String input) {
        pushedMessage = input;
    }

    public String getMessage() { return pushedMessage; }

}

