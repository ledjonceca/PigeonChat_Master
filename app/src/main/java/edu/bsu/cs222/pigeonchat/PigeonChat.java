package edu.bsu.cs222.pigeonchat;

import android.app.Application;

import com.firebase.client.Firebase;

public class PigeonChat extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
