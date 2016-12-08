package edu.bsu.cs222.pigeonchat;

public class PasswordChecker {
    private String password, confirmPassword;

    public PasswordChecker(PasswordCheckerBuilder builder) {
        this.password = builder.password;
        this.confirmPassword = builder.confirmPassword;
    }

    public boolean isValid(){
        return passwordIsNotEmpty() && passwordsAreTheSame();
    }

    private boolean passwordIsNotEmpty() {
        return !(password.equals(""));
    }

    public boolean passwordsAreTheSame(){
        return password.equals(confirmPassword);
    }

    public static class PasswordCheckerBuilder {
        private String password, confirmPassword;

        public PasswordCheckerBuilder password(String password) {
            this.password = password;
            return this;
        }

        public PasswordCheckerBuilder confirmPasword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public PasswordChecker build() {
            return new PasswordChecker(this);
        }
    }

}