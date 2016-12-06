package edu.bsu.cs222.pigeonchat;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

 public class FirebaseConnector {
     private FirebaseAuth authenticator;
     private FirebaseUser firebaseUser;
     private Firebase rootRef;

     public FirebaseConnector(){
        rootRef = new Firebase("https://pigeonchat-e9daf.firebaseio.com/Messages");
        authenticator = FirebaseAuth.getInstance();
        firebaseUser = authenticator.getCurrentUser();
     }

     public Firebase getRootRef() { return rootRef; }

     public FirebaseAuth getAuthenticator() { return authenticator; }

     public FirebaseUser getUser(){
        return firebaseUser;
    }

     public String getName() {
        return this.getUser().getEmail();
    }

}
