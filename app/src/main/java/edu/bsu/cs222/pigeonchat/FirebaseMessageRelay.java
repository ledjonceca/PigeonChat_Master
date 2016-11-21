package edu.bsu.cs222.pigeonchat;

import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.test.espresso.core.deps.guava.collect.Lists;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import java.util.List;

public class FirebaseMessageRelay implements MessageRelay {

    private Firebase rootRef;
    private FirebaseConnector connector = new FirebaseConnector();
    private String newMessages;
    private final List<MessageRelayListener> observers = Lists.newArrayList();
    private Pushable firebasePusher = new FirebasePusher();

    public FirebaseMessageRelay(){
        rootRef = connector.getRootRef();
        addRootRefListener();
    }

    private void addRootRefListener() {
        rootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                newMessages = dataSnapshot.getValue(String.class);
                for(MessageRelayListener listener : observers){
                    listener.onMessageReceived();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {            }
        });
    }

    public void addObserver(MessageRelayListener listener){
        Preconditions.checkNotNull(listener);
        observers.add(listener);
    }

    public void sendMessage(String userMessage) {
        firebasePusher.push(userMessage);
    }

    public String getNewMessage(){
        return newMessages;
    }

}
