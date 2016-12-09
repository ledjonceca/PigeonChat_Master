package edu.bsu.cs222.pigeonchat;


public class UsernameGenerator {
    private String email;

    public UsernameGenerator(String email) {
        this.email = email;
    }

    public String GenerateUsername() {
        return TruncateCharactersAfter(positionOfAtSymbol());
    }

    public int positionOfAtSymbol() {
        return email.lastIndexOf("@");
    }

    public String TruncateCharactersAfter(int index) {
        String username = "";
        for (int i = 0; i < index; i++) {
            username += (String.valueOf(email.charAt(i)));
        }
        return username;
    }
}
