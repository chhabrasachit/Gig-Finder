/*
    This class represents the Sign up Activity.
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// This is the SIgnUp page where a new User register.

/**
 * Sign Up: User can sign up to the app.
 */
public class SignUp extends AppCompatActivity {

    // Initializes the EditText fields and button.

    private EditText mEmailField;
    private EditText mPassword;
    private EditText mName;
    private EditText mPhoneNumber;
    private EditText mPreference;
    private EditText mExperience;
    private EditText mBio;
    private Button mLogin;

    // Initializes the Firebase.

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    /**
     * loads when the activity is created.
     * @param savedInstanceState : Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Get the instance from Firebase and all the fields -- editText, Button.

        mAuth = FirebaseAuth.getInstance();
        mEmailField = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mName = findViewById(R.id.Name);
        mPhoneNumber = findViewById(R.id.Number);
        mPreference = findViewById(R.id.pref);
        mExperience = findViewById(R.id.exp);
        mBio = findViewById(R.id.bio);
        mLogin = findViewById(R.id.login);

        // Set an OnClickListener to the Login button4

        mLogin.setOnClickListener(new View.OnClickListener(){
            /**
             * onclick for the register button
             * @param v : View
             */
            @Override
            public void onClick(View v) {
                startSignUp();
            }
        });

    }

    /**
     * starts the sign up for the user:
     * Loads all the view elements.
     */
    private void startSignUp(){

        // Get the text and Set them to the string

        final String email = mEmailField.getText().toString();
        String password = mPassword.getText().toString();
        final String name = mName.getText().toString();
        final String phone = mPhoneNumber.getText().toString();
        final String pref = mPreference.getText().toString();
        final String bio = mBio.getText().toString();
        final String exp = mExperience.getText().toString();

        // Warns the User with a toast message if any of the field remain empty

        if(TextUtils.isEmpty((email)) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone)){
            Toast.makeText(SignUp.this, "Fields are Empty",Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener
                    (new OnCompleteListener<AuthResult>() {
                        /**
                         * on completed task to authorize the user
                         * @param task : Task<AuthResult>
                         */
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        FirebaseDatabase database =  FirebaseDatabase.getInstance();
                        FirebaseUser user =  mAuth.getCurrentUser();

                        String userId = user.getUid();
                        Profile p = new Profile(name,email,phone,bio,pref,exp);

                        DatabaseReference mRef =  database.getReference().child("Users").child(userId);
                        mRef.setValue(p);

                        Toast.makeText(SignUp.this, "Successfully Registered",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(SignUp.this, "Registration Failed",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
