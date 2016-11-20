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
    private ArrayList<String> messages = new ArrayList<>();
    private String email;
    private final FirebaseConnector connector = new FirebaseConnector();
    private ArrayAdapter<String> arrayAdapter;
    private FirebaseMessageRelay firebaseMessageManager = new FirebaseMessageRelay();
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

        firebaseMessageManager.addListener(new MessageRelayListener() {
            @Override
            public void onMessageReceived() {
                newMessages = firebaseMessageManager.getNewMessage();
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
            setEmail();
        }
    }

    private void setEmail() { email = connector.getName(); }

    private void sendMessage() {
        String messageText = createMessage().getMessageText();
        firebaseMessageManager.sendMessage(messageText);
        userMessage.getText().clear();
    }

    private Message createMessage(){
        Message message = new Message.MessageBuilder().email(email).content(getContentOfTextbox()).build();
        return message;
    }

    private String getContentOfTextbox() {
        return userMessage.getText().toString();
    }

    private void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}