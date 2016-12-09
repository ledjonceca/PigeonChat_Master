package edu.bsu.cs222.pigeonchat;


public class PasswordStrengthChecker {
    private String password;
    private final String NUMBERS_CHARACTERS = "1234567890";
    private final String SPECIAL_CHARACTERS = "!@#$%^&*()";
    private final int MEDIUM_PASSWORD_LENGTH = 8;

    public PasswordStrengthChecker(String password){
        this.password = password;
    }

    public String checkStrength(){
        if(isStrongPassword()){
            return "strong";
        }
        else if(isMediumPassword()){
            return "medium";
        }
        else{
            return "weak";
        }
    }

    private boolean isStrongPassword(){
        return this.hasEnoughCharcters() && this.hasANumber() && this.hasASpecialChar();
    }

    private boolean isMediumPassword(){
        boolean hasNonAlphabetic = this.hasANumber() || this.hasASpecialChar();
        return password.length() >= 8 && hasNonAlphabetic;
    }

    public boolean hasEnoughCharcters() {
        return password.length() >= MEDIUM_PASSWORD_LENGTH;
    }

    public boolean hasANumber() {
        return countCharactersFromString(NUMBERS_CHARACTERS) > 0;
    }

    public boolean hasASpecialChar() {
        return countCharactersFromString(SPECIAL_CHARACTERS) > 0;
    }

    public int countCharactersFromString(String characterList){
        String currentListCharacter;
        int counter = 0;
        for(int i=0;i<characterList.length();i++){
            currentListCharacter = String.valueOf(characterList.charAt(i));
            counter += searchForCharacter(currentListCharacter);
        }
        return counter;
    }

    public int searchForCharacter(String character) {
        int counter = 0;
        for(int j=0;j<password.length();j++){
            String currentPasswordCharacter = String.valueOf(password.charAt(j));
            if(currentPasswordCharacter.equals(character)){
                counter += 1;
            }
        }
        return counter;
    }
}
