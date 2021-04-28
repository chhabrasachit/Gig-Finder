/*
 * This class represents the main activity.
 */
package ca.bcit.gigfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// This is th Main Activity that contains the login page.

/**
 * MainActivity: User Login functionality.
 */
public class MainActivity extends AppCompatActivity {

    //Initializes the EditTexts, Button and Firebase

    private EditText mEmailField;
    private EditText mPassword;
    private Button mLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    /**
     * Loads when the activity is created.
     * @param savedInstanceState :Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get instance from Firebase

        mAuth = FirebaseAuth.getInstance();

        //Get the View of email, password and login cardview

        mEmailField = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLogin = findViewById(R.id.login);

        //Set the listener to check whether there is a user or not. If yes, then login

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            /**
             * When the authorization is completed:
             * Directs to home activity.
             * @param firebaseAuth : Firebase
             */
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Intent i = new Intent(MainActivity.this, Home.class);
                    startActivity(i);
                }
            }
        };

        mLogin.setOnClickListener(new View.OnClickListener(){

            /**
             * on click for the login button
             * @param v View
             */
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });

    }

    //OnStart Function that access the user from database.

    /**
     * Access the database.
     */
    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    /**
     * When the user begins to enter info and login
     */
    private void startSignIn(){
        //Get the text from email and password and Set the text to string
        String email = mEmailField.getText().toString();
        String password = mPassword.getText().toString();

        if(TextUtils.isEmpty((email)) || TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Fields are Empty",Toast.LENGTH_LONG)
                    .show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener
                    (new OnCompleteListener<AuthResult>() {
                        /**
                         * onCompleted the task of entering correct combination.
                         * @param task Task<AuthResult></AuthResult>
                         */
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this,
                                "SignIn Problem",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    //If there is a new User, Register textView will take them to SignUp Page.
    /**
     * Register here: onclick: directs to new Activity
     * @param v View
     */
    public void Register(View v){
        Intent i = new Intent(MainActivity.this,SignUp.class);
        startActivity(i);
    }
}
