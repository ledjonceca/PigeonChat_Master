package edu.bsu.cs222.pigeonchat;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

public class ChildEventAdapter implements ChildEventListener {

    public void onChildAdded(DataSnapshot dataSnapshot, String s) {    }

    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
    }

    public void onChildRemoved(DataSnapshot dataSnapshot) {
    }

    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
    }

    public void onCancelled(FirebaseError firebaseError) {
    }

}
