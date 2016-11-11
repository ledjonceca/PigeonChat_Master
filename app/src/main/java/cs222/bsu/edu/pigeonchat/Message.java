package cs222.bsu.edu.pigeonchat;

public class Message {
    private String email;
    private String content;
    private String username;

    public Message(String email, String content)  {
        this.email = email;
        this.content = content;
        username = new EmailTruncator(this.email).truncate();
    }

    public String getMessage(){
        String message = username + ": " + content;
        return message;
    }
}
