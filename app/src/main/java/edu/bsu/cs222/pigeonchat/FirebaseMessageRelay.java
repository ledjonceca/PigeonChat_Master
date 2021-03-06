package edu.bsu.cs222.pigeonchat;

import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.test.espresso.core.deps.guava.collect.Lists;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import java.util.List;

public class FirebaseMessageRelay {
    private String newMessages;
    private final List<MessageRelayListener> observers = Lists.newArrayList();
    private Pushable firebasePusher = new FirebasePusher();

    public FirebaseMessageRelay(){
        Firebase rootRef = new FirebaseConnector().getRootRef();
        rootRef.addChildEventListener( new ChildEventAdapter() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                newMessages = dataSnapshot.getValue(String.class);
                for(MessageRelayListener listener : observers){
                    listener.onMessageReceived();
                }
            }
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
