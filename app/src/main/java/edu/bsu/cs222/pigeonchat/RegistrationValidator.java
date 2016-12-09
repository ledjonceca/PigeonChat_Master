package edu.bsu.cs222.pigeonchat;

public class RegistrationValidator {
    private String email, password, confirmPassword;
    private PasswordValidator passwordValidator;
    private EmailValidator emailValidator;

    public RegistrationValidator(RegistrationValidatorBuilder builder) {
        this.email = builder.email;
        this.password = builder.password;
        this.confirmPassword = builder.confirmPassword;
        passwordValidator = new PasswordValidator(this.password);
        emailValidator = new EmailValidator(this.email);
    }

    public boolean isValid() {
        return emailIsValid() && passwordIsValid();
    }

    public boolean emailIsValid() {
        return emailValidator.emailContainsProperSymbols();
    }

    public boolean passwordIsValid() {
        return passwordValidator.passwordIsLongEnough() && passwordValidator.passwordsAreTheSame(confirmPassword);
    }

    public static class RegistrationValidatorBuilder {
        private String email, password, confirmPassword;

        public RegistrationValidatorBuilder email(String email) {
            this.email = email;
            return this;
        }

        public RegistrationValidatorBuilder password(String password) {
            this.password = password;
            return this;
        }

        public RegistrationValidatorBuilder confirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public RegistrationValidator build() {
            return new RegistrationValidator(this);
        }
    }

}