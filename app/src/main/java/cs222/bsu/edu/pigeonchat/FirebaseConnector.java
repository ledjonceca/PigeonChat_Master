package cs222.bsu.edu.pigeonchat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseConnector {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private Firebase rootRef;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public void authenticate(){
        rootRef = new Firebase("https://pigeonchat-e9daf.firebaseio.com/Users");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
    }

    //public FirebaseAuth getAuth() { return mFirebaseAuth; }

    public FirebaseUser getUser(){
        return mFirebaseUser;
    }

    public Firebase getRootRef(){
        return rootRef;
    }

    public String getName() {
        EmailTruncator truncator = new EmailTruncator();
        return truncator.truncate(this.getUser().getEmail());
    }

}
