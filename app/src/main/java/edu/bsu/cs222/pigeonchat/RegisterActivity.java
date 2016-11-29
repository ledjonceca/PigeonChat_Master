package edu.bsu.cs222.pigeonchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private Button mRegisterButton;
    private FirebaseAuth registerAuth;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        setup();
        mProgress = new ProgressDialog(this);

    }

    private void setup() {
        associateViewItemsWithModel();
        addListeners();
    }

    private void addListeners() {
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        confirmPasswordField = (EditText) findViewById(R.id.confirmPasswordField);
        mRegisterButton = (Button) findViewById(R.id.registerButton);
    }

    private void associateViewItemsWithModel() {
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {startRegistration();

            }
        });
    }

    private void startRegistration() {
        final String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String confirmPassword = confirmPasswordField.getText().toString().trim();

        if(!TextUtils.isEmpty(email)  && !TextUtils.isEmpty(password) &&
                !TextUtils.isEmpty(confirmPassword) ){
            mProgress.setMessage("Registering. Please Wait...");
            mProgress.show();

            registerAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        String user_id = registerAuth.getCurrentUser().getUid();
                        DatabaseReference currentUserDatabase = mDatabase.child(user_id);
                        currentUserDatabase.child("Name").setValue(email);
                        mProgress.dismiss();
                        Intent mainIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);


                    }
                }
            });
        }
    }
}

