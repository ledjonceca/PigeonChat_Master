package edu.bsu.cs222.pigeonchat;

import com.firebase.client.Firebase;

public class FirebasePusher implements Pushable {
    private Firebase rootRef;

    public FirebasePusher() {
        rootRef = new FirebaseConnector().getRootRef();
    }

    public void push(String input) {
        rootRef.push().setValue(input);
    }

}
