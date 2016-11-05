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
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private Button sendButton;
    private EditText userMessage;
    private String message;
    private ListView chatWindow;
    private ArrayList<String> messages = new ArrayList<>();
    private String mUsername;
    private final FirebaseConnector connector = new FirebaseConnector();
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setup();
    }

    private void setup() {
        connector.authenticate();
        confirmUserExists();
        associateObjects();
        addListeners();
    }

    private void addListeners() {
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendMessage();
            }
        });

        connector.getRootRef().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String newMessages = dataSnapshot.getValue(String.class);
                messages.add(newMessages);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });
    }

    private void sendMessage() {
        createMessage();
        pushMessage();
        userMessage.getText().clear();
    }

    private void associateObjects() {
        userMessage = (EditText) findViewById(R.id.userMessage);
        sendButton = (Button) findViewById(R.id.sendButton);
        chatWindow = (ListView) findViewById(R.id.chatWindow);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages);
        chatWindow.setAdapter(arrayAdapter);
    }

    private void confirmUserExists() {
        if (connector.getUser() == null) {
            goToLogin();
        } else {
            setUserName();
        }
    }

    private void setUserName() { mUsername = connector.getName(); }

    private void pushMessage(){ connector.getRootRef().push().setValue(message); }

    private void createMessage(){
        message = mUsername + ":  " + userMessage.getText().toString();
    }

    private void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}