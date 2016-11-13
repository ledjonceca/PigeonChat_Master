package edu.bsu.cs222.pigeonchat;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

 class FirebaseConnector {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private Firebase rootRef;

    public FirebaseConnector(){
        rootRef = new Firebase("https://pigeonchat-e9daf.firebaseio.com/Users");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
    }

    public FirebaseUser getUser(){
        return mFirebaseUser;
    }

    public String getName() {
        return this.getUser().getEmail();
    }

}
