package edu.bsu.cs222.pigeonchat;

/**
 * Created by brian on 12/6/2016.
 */

public class PasswordStrengthChecker {
    private String password;

    public PasswordStrengthChecker(String password){
        this.password = password;
    }

    public String checkStrength(){
        if(isStrong()){
            return "strong";
        }
        else if(isMedium()){
            return "medium";
        }
        else{
            return "weak";
        }
    }

    public boolean isStrong(){
        return this.hasEnoughCharcters() && this.hasANumber() && this.hasASpecialChar();
    }

    public boolean isMedium(){
        boolean hasNonAlphabetic = this.hasANumber() || this.hasASpecialChar();
        return password.length() >= 8 && hasNonAlphabetic;
    }

    public boolean hasEnoughCharcters() {
        return password.length() >= 8;
    }

    public boolean hasANumber() {
        final String numberCharacters = "1234567890";
        return countCharactersFromString(numberCharacters) > 0;
    }

    public boolean hasASpecialChar() {
        final String specialCharacters = "!@#$%^&*()";
        return countCharactersFromString(specialCharacters) > 0;
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
