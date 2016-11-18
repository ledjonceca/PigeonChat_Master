package edu.bsu.cs222.pigeonchat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private Toaster toaster = new Toaster(LoginActivity.this);
    private static final String TAG = "LoginActivity";
    private FirebaseAuth authenticator;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        authenticator = FirebaseAuth.getInstance();
        setup();
    }

    @Override
    public void onStart() {
        super.onStart();
        authenticator.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) { authenticator.removeAuthStateListener(authListener); }
    }

    private void setup() {
        associateViewItemsWithModel();
        addListeners();
        authenticator.signOut();
    }

    private void associateViewItemsWithModel() {
        emailInput =(EditText) findViewById(R.id.etEmail);
        passwordInput =(EditText) findViewById(R.id.etPassword);
        loginButton = (Button) findViewById(R.id.bLogin);
    }

    private void addListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    signIn(user);
                } else {
                    signOut();
                }
            }
        };
    }

    private void logOutput(String message) {
        Log.d(TAG, message);
    }

    private void signIn(FirebaseUser user) {
        logOutput("Auth-State changed: " + user.getUid() + " has signed in.");
        toaster.popUp("logged in successfully");
        openChatActivity();
    }

    private void signOut() {
        logOutput("Auth-State changed: A user has signed out");
    }

    private void attemptLogin() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            toaster.popUp("Please enter an email or password");
        }
        else{
            validateLogin(email, password);
        }
    }

    private void validateLogin(String email, String password) {
        authenticator.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful() ){
                    toaster.popUp("invalid username or password!");
                }
            }
        });
    }

    private void openChatActivity() {
        Context currentActivity = LoginActivity.this;
        Intent chatIntent = new Intent(LoginActivity.this, ChatActivity.class);
        currentActivity.startActivity( chatIntent );
    }

}