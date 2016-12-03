package edu.bsu.cs222.pigeonchat;

public class PasswordChecker {
    private String password;
    private String confirmPassword;

    public PasswordChecker(String password) {
        this.password = password;
    }

    public boolean isValid(String confirmPassword){
        this.confirmPassword = confirmPassword;
        return notEmpty() && comparePasswords();
    }

    private boolean notEmpty() {
        return !(password.equals(""));
    }

    public boolean comparePasswords(){
        return password.equals(confirmPassword);
    }

/**
    public String checkStrength(){
        if password.length() < 8 ||
        //if password only contains characters, and is less than 8 charactesr return weak
        //if password contains at least one number and the length is equal to or greater than 8, return medium
        //if password contains a symbol, a number, and is greater than 8, return strong
        return "words";
    }
 **/
}
