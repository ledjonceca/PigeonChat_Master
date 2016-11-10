package cs222.bsu.edu.pigeonchat;

import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.test.espresso.core.deps.guava.collect.Lists;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.List;

/**
 * Created by phil on 11/2/2016.
 */

public class MessageManager {

    private Firebase rootRef;
    private String newMessages;
    private final List<MessageManagerListener> listeners = Lists.newArrayList();
    //private FirebaseConnector connector = new FirebaseConnector();

    public MessageManager(){
        rootRef = new Firebase("https://pigeonchat-e9daf.firebaseio.com/Users");

        rootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                newMessages = dataSnapshot.getValue(String.class);

                for(MessageManagerListener listener : listeners){
                    listener.onMessageReceived();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void addListener(MessageManagerListener listener){
        Preconditions.checkNotNull(listener);
        listeners.add(listener);
    }

    public void sendMessage(String userMessage) {
        rootRef.push().setValue(userMessage);
    }

    public String postNewMessage(){
        return newMessages;
    }
/*
    public String getMessage() {
        newMessages = dataSnapshot.getValue(String.class);
        return newMessages;
    }
    */
}
