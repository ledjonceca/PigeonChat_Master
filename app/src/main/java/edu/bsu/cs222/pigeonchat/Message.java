package edu.bsu.cs222.pigeonchat;

public class Message {
    private String content;
    private String username;

    public Message(String email, String content)  {
        this.content = content;
        username = new EmailTruncator(email).truncate();
    }

     String getMessage(){
        return username + ": " + content;
    }
}
