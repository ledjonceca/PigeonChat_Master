package edu.bsu.cs222.pigeonchat;

import com.firebase.client.Firebase;

/**
 * Created by brian on 11/20/2016.
 */

public class FirebasePusher implements Pushable {

    private Firebase rootRef;

    public FirebasePusher() {
        rootRef = new Firebase("https://pigeonchat-e9daf.firebaseio.com/Users");
    }

    public void push(String input) {
        rootRef.push().setValue(input);
    }

    public String getMessage() {
        return  null;
    }
}
