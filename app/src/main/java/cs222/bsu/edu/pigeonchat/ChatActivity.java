package cs222.bsu.edu.pigeonchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private Button sendButton;
    private EditText userMessage;
    private String message;
    private ListView chatWindow;
    private ArrayList<String> messages = new ArrayList<>();
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final FirebaseConnector connector = new FirebaseConnector();
        connector.authenticate();
        EmailTruncator truncator = new EmailTruncator();

        userMessage = (EditText) findViewById(R.id.userMessage);
        sendButton = (Button) findViewById(R.id.sendButton);
        chatWindow = (ListView) findViewById(R.id.chatWindow);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages);
        chatWindow.setAdapter(arrayAdapter);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                createMessage();
                sendMessage(connector);
                userMessage.getText().clear();
            }
        });

        if (connector.getUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        } else {
            mUsername = truncator.truncate(connector.getUser().getEmail());
        }

        connector.getRootRef().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String newMessages = dataSnapshot.getValue(String.class);
                messages.add(newMessages);
                arrayAdapter.notifyDataSetChanged();
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

    private void sendMessage(FirebaseConnector connector){
        connector.getRootRef().push().setValue(message);
    }
    private void createMessage(){
        message = mUsername + ":  " + userMessage.getText().toString();
    }
}