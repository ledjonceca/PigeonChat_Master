package edu.bsu.cs222.pigeonchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private Button sendButton;
    private EditText userMessage;
    private String message;
    private ArrayList<String> messages = new ArrayList<>();
    private String mUsername;
    private final FirebaseConnector connector = new FirebaseConnector();
    private ArrayAdapter<String> arrayAdapter;
    private MessageManager messageManager = new MessageManager();
    private String newMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setup();
    }

    private void setup() {
        confirmUserExists();
        associateViewItemsWithModel();
        addListeners();
    }

    private void addListeners() {
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendMessage();
            }
        });

        messageManager.addListener(new MessageManagerListener() {
            @Override
            public void onMessageReceived() {
                newMessages = messageManager.postNewMessage();
                messages.add(newMessages);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

    private void associateViewItemsWithModel() {
        userMessage = (EditText) findViewById(R.id.userMessage);
        sendButton = (Button) findViewById(R.id.sendButton);
        ListView chatWindow = (ListView) findViewById(R.id.chatWindow);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
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

    private void sendMessage() {
        createMessage();
        messageManager.sendMessage(message);
        userMessage.getText().clear();
    }

    private void createMessage(){
        message = new Message(mUsername, userMessage.getText().toString()).getMessage();
    }

    private void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}