package edu.bsu.cs222.pigeonchat;

public class PasswordValidator {
    private String password;
    private final int MINIMUM_PASSWORD_LENGTH = 6;

    public PasswordValidator(String password) {
        this.password = password;
    }

    public boolean passwordIsLongEnough() {
        return (password.length() >= MINIMUM_PASSWORD_LENGTH);
    }

    public boolean passwordsAreTheSame(String confirmPassword) {
        return password.equals(confirmPassword);
    }

}
