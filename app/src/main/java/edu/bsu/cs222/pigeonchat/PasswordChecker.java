package edu.bsu.cs222.pigeonchat;

public class PasswordChecker {
    private String password;

    public PasswordChecker(String password) {
        this.password = password;
    }

    public boolean isValid(String confirmPassword){
        return notEmpty() && comparePasswords(confirmPassword);
    }

    private boolean notEmpty() {
        return !(password.equals(""));
    }

    public boolean comparePasswords(String confirmPassword){
        return password.equals(confirmPassword);
    }

}