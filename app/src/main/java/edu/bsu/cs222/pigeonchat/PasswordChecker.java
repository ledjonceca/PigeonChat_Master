package edu.bsu.cs222.pigeonchat;

public class PasswordChecker {
    private String password;
    private final String specialCharacters = "!@#$%^&*()";
    private final String numberCharacters = "1234567890";

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
        return password.length() >= 8 && this.countCharactersFromString(numberCharacters) > 0 && this.countCharactersFromString(specialCharacters) > 0;
    }

    public boolean isMedium(){
        return password.length() >= 8 && this.countCharactersFromString(numberCharacters) > 0;
    }

    public int numberCharacterCount(){
        return countCharactersFromString(numberCharacters);
    }

     public int specialCharacterCount(){
         return countCharactersFromString(specialCharacters);
     }

    public int countCharactersFromString(String characterList){
        String currentListCharacter;
        String currentPasswordCharacter;
        int counter = 0;
        for(int i=0;i<characterList.length();i++){
            currentListCharacter = String.valueOf(characterList.charAt(i));
            for(int j=0;j<password.length();j++){
                currentPasswordCharacter = String.valueOf(password.charAt(j));
                if(currentPasswordCharacter.equals(currentListCharacter)){
                    counter += 1;
                }
            }
        }
        return counter;
    }
}