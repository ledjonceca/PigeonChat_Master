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

    private EditText etEmail;
    private EditText etPassword;
    private Button bLogin;
    private Toaster toaster = new Toaster(LoginActivity.this);
    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        setup();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) { mAuth.removeAuthStateListener(mAuthListener); }
    }

    private void setup() {
        associateViewItemsWithModel();
        addListeners();
        mAuth.signOut();
    }

    private void associateViewItemsWithModel() {
        etEmail =(EditText) findViewById(R.id.etEmail);
        etPassword =(EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
    }

    private void addListeners() {
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
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
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            toaster.popUp("Please enter an email or password");
        }
        else{
            validateLogin(email, password);
        }
    }

    private void validateLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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