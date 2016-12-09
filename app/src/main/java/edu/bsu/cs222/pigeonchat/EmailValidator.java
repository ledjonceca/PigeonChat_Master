package edu.bsu.cs222.pigeonchat;

public class EmailValidator {
    private String email;

    public EmailValidator(String email) {
        this.email = email;
    }

    public boolean emailContainsProperSymbols() {
        return emailContainsAtSymbol() && emailContainsPeriod();
    }

    public boolean emailContainsAtSymbol() {
        return email.contains("@");
    }

    public boolean emailContainsPeriod() {
        return email.contains(".");
    }
}
