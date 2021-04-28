/*
 This class represents the Feedback activity.
 */
package ca.bcit.gigfinder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// This is the FeedBack Activity that contain the form and rating bar.
/**
 * FeedBack Activity:
 * Allows user to give feedback.
 */
public class FeedbackActivity extends AppCompatActivity {
    // Variables
    EditText nameData, emailData, messageData;
    Button send, details;
    private RatingBar ratingBar;
    private TextView tvRateCount, tvRateMessage;
    private float ratedValue;

    /**
     * Lodas when the activity is created.
     * @param savedInstanceState : Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Get an instance of RatingBar and set different messages according to ratings

        ratingBar = findViewById(R.id.ratingBar);
        tvRateCount = findViewById(R.id.tvRateCount);
        tvRateMessage = findViewById(R.id.tvRateMessage);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            /**
             * Sets texts to the textView according to rating given by the user.
             * @param ratingBar :RatingBar
             * @param rating :float
             * @param fromUser :boolean
             */
            @SuppressLint("SetTextI18n")
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                ratedValue = ratingBar.getRating();
                tvRateCount.setText("Your Rating : " + ratedValue + "/5.");
                if(ratedValue<1){
                    tvRateMessage.setText("ohh ho...");
                }else if(ratedValue<2){
                    tvRateMessage.setText("Ok.");
                }else if(ratedValue<3){
                    tvRateMessage.setText("Not bad.");
                }else if(ratedValue<4){
                    tvRateMessage.setText("Nice");
                }else if(ratedValue<5){
                    tvRateMessage.setText("Very Nice");
                }else if(ratedValue==5){
                    tvRateMessage.setText("Thank you..!!!");
                }
            }

            });

        // Get an instance of fields -- name, email, message and buttons
        nameData = findViewById(R.id.namedata);
        emailData = findViewById(R.id.emaildata);
        messageData = findViewById(R.id.messagedata);

        send = findViewById(R.id.btn_send);
        details = findViewById(R.id.btn_details);

        // Get an instance of firebase and set the values from the form to the firebase.
        final DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("FeedBack");

        send.setOnClickListener(new View.OnClickListener() {
            /**
             * On click listener for send button:
             * sends the entered data to the database.
             * @param v View
             */
            @Override
            public void onClick(View v){

                details.setEnabled(true);
                final String name = nameData.getText().toString();
                final String email = emailData.getText().toString();
                final String message = messageData.getText().toString();

                if(TextUtils.isEmpty((name)) || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(message)){
                    Toast.makeText(FeedbackActivity.this,
                            "Fields are Empty",Toast.LENGTH_LONG).show();

                }else {
                    Profile p = new Profile(email,name,message);
                    ref.child("FeedBack").setValue(p);
                }

                // Set a toast message that will aware the user when data is sent to firebase
                Toast.makeText(FeedbackActivity.this,
                        "Your data was sent to Server!", Toast.LENGTH_SHORT).show();

                // Set an onClickListener to Details button and will pop-up the values sent.
                details.setOnClickListener(new View.OnClickListener() {
                    /**
                     * Shows the sent details to the user.
                     * @param v View
                     */
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(FeedbackActivity.this)
                                .setTitle("Sent Details: ")
                                .setMessage("Name = " + name +
                                        "\n\nEmail = " + email + "\n\nMessage = " + message)
                                .show();
                    }
                });

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}

