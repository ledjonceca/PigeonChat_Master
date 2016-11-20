package edu.bsu.cs222.pigeonchat;

public class Message {
    private String content;
    private String username;

    public Message(MessageBuilder builder)  {
        this.content = builder.content;
        this.username = new EmailTruncator(builder.email).truncate();
    }

    String getMessageText(){
        return username + ": " + content;
    }

    public static class MessageBuilder {
        private String content, email;

       public MessageBuilder email(String email) {
           this.email = email;
           return this;
       }

        public MessageBuilder content(String content) {
            this.content = content;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

}
