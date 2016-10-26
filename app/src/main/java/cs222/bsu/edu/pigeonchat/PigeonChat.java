package cs222.bsu.edu.pigeonchat;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by phil on 10/22/2016.
 */

public class PigeonChat extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);

    }

}
