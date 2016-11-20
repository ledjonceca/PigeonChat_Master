package edu.bsu.cs222.pigeonchat;

import com.firebase.client.Firebase;

public class FirebasePusher implements Pushable {

    private FirebaseConnector connector = new FirebaseConnector();
    private Firebase rootRef;

    public FirebasePusher() {
        rootRef = connector.getRootRef();
    }

    public void push(String input) {
        rootRef.push().setValue(input);
    }

}
