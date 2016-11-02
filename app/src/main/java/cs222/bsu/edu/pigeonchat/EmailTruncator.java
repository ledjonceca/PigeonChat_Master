package cs222.bsu.edu.pigeonchat;


public class EmailTruncator {
    public String truncate(String email){
        if (email.contains(".") && email.contains("@")){
            String username = "";
            int index = email.lastIndexOf("@");
            for (int i = 0;i<index;i++){
                username += (String.valueOf(email.charAt(i)));
            }
            return username;
        }
        else{
            return null;
        }
    }
}
