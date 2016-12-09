package edu.bsu.cs222.pigeonchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private TextView passwordStrengthText;
    private FirebaseConnector firebaseConnector = new FirebaseConnector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerAuth = firebaseConnector.getAuthenticator();
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
        passwordStrengthText = (TextView) findViewById(R.id.passwordStrengthText);
    }

    private void addListeners() {
        passwordStrengthText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { showPasswordStrength(); }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegistration();
            }
        });
    }

    private void showPasswordStrength() {
        String passwordToCheck = passwordField.getText().toString().trim();
        PasswordStrengthChecker passwordStrengthChecker = new PasswordStrengthChecker(passwordToCheck);
        toaster.popUp("Password is " + passwordStrengthChecker.checkStrength());
    }

    private void startRegistration() {
        getDataFromTextBoxes();
        if (inputsAreValid()){
            openProgressWindow();
            createUser();
        }
        else {
            toaster.popUp("Invalid Email or Passwords");
        }
    }

    private void getDataFromTextBoxes() {
        email = emailField.getText().toString().trim();
        password = passwordField.getText().toString().trim();
        confirmPassword = confirmPasswordField.getText().toString().trim();
    }

    private boolean inputsAreValid(){
        RegistrationValidator registrationValidator = new RegistrationValidator.RegistrationValidatorBuilder().email(email).password(password).confirmPassword(confirmPassword).build();
        return registrationValidator.isValid();
    }

    private void openProgressWindow() {
        progressWindow = new ProgressDialog(this);
        progressWindow.setMessage("Registering. Please Wait...");
        progressWindow.show();
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

    private void closeProgressWindow() {
        progressWindow.dismiss();
    }

    private void goToLoginScreen() {
        Intent mainIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
    }

}