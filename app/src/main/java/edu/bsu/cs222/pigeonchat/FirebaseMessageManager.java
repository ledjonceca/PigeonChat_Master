package edu.bsu.cs222.pigeonchat;

import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.test.espresso.core.deps.guava.collect.Lists;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.List;

public class FirebaseMessageManager implements MessageManager{

    private Firebase rootRef;
    private FirebaseConnector connector = new FirebaseConnector();
    private String newMessages;
    private final List<MessageManagerListener> listeners = Lists.newArrayList();
    private Pushable pusher = new FirebasePusher();

    public FirebaseMessageManager(){
        rootRef = connector.getRootRef();
        rootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                newMessages = dataSnapshot.getValue(String.class);
                for(MessageManagerListener listener : listeners){
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

    public void addListener(MessageManagerListener listener){
        Preconditions.checkNotNull(listener);
        listeners.add(listener);
    }

    public void sendMessage(String userMessage) {
        //rootRef.push().setValue(userMessage);
        pusher.push(userMessage);
    }

    public String getNewMessage(){
        return newMessages;
    }

}
