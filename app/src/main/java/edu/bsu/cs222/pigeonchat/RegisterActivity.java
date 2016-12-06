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

public class RegisterActivity extends AppCompatActivity {
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private Button registerButton;
    private FirebaseAuth registerAuth;
    private ProgressDialog progressWindow;
    private String email, password, confirmPassword;
    private Toaster toaster = new Toaster(RegisterActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerAuth = FirebaseAuth.getInstance();
        setup();
    }

    private void setup() {
        associateViewItemsWithModel();
        addListeners();
    }

    private void associateViewItemsWithModel() {
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        confirmPasswordField = (EditText) findViewById(R.id.confirmPasswordField);
        registerButton = (Button) findViewById(R.id.registerButton);
    }

    private void addListeners() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { startRegistration(); }
        });
    }

    private void startRegistration() {
        getDataFromTextBoxes();
        PasswordChecker passwordChecker = new PasswordChecker(password);
        if (!TextUtils.isEmpty(email) && passwordChecker.isValid(confirmPassword) ){
            openProgressWindow();
            createUser();
        }
        else {
            toaster.popUp("Invalid Email or password");
        }
    }


    private void getDataFromTextBoxes() {
        email = emailField.getText().toString().trim();
        password = passwordField.getText().toString().trim();
        confirmPassword = confirmPasswordField.getText().toString().trim();
    }

    private void createUser() {
        registerAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    completeRegistration();
                }
            }
        });
    }

    private void completeRegistration() {
        closeProgressWindow();
        goToLoginScreen();
    }

    private void openProgressWindow() {
        progressWindow = new ProgressDialog(this);
        progressWindow.setMessage("Registering. Please Wait...");
        progressWindow.show();
    }

    private void closeProgressWindow() {
        progressWindow.dismiss();
    }

    private void goToLoginScreen() {
        Intent mainIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
    }

}