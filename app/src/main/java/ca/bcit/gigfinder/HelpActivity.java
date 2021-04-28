/*
 * This class represents Help Activity.
 */
package ca.bcit.gigfinder;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

// This is the help activity in the navigation Drawer that contain Frequently Asked Questions.
/**
 * Help Activity: FAQs
 */
public class HelpActivity extends AppCompatActivity {

    // Initializes the textViews to display questions and answers
    TextView ques1;
    TextView ques2;
    TextView ques3;
    TextView ques4;
    TextView ques5;
    TextView ques6;
    TextView ans1;
    TextView ans2;
    TextView ans3;
    TextView ans4;
    TextView ans5;
    TextView ans6;

    /**
     * Loads when the activity is created.
     * @param savedInstanceState :Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Get instance of the textViews
        ques1 = findViewById(R.id.qus1);
        ans1 =  findViewById(R.id.ans1);
        ques2 = findViewById(R.id.qus2);
        ans2 =  findViewById(R.id.ans2);
        ques3 = findViewById(R.id.qus3);
        ans3 =  findViewById(R.id.ans3);
        ques4 = findViewById(R.id.qus4);
        ans4 =  findViewById(R.id.ans4);
        ques5 = findViewById(R.id.qus5);
        ans5 =  findViewById(R.id.ans5);
        ques6 = findViewById(R.id.qus6);
        ans6 =  findViewById(R.id.ans6);

        // set an onclickListener to all the questions to display answers
        ques1.setOnClickListener(new View.OnClickListener() {
            /**
             * on click listener for the questions clicked.
             * @param v View
             */
            @Override
            public void onClick(View v) {

                ans1.setVisibility(View.VISIBLE);
                ans2.setVisibility(View.GONE);
                ans3.setVisibility(View.GONE);
                ans4.setVisibility(View.GONE);
                ans5.setVisibility(View.GONE);
                ans6.setVisibility(View.GONE);

                int height = 0;
                int width = 0;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                ans2.setLayoutParams(params);
                ans3.setLayoutParams(params);
                ans4.setLayoutParams(params);
                ans5.setLayoutParams(params);
                ans6.setLayoutParams(params);

                height = FrameLayout.LayoutParams.WRAP_CONTENT;
                width = FrameLayout.LayoutParams.MATCH_PARENT;

                params = new LinearLayout.LayoutParams(width, height);
                ans1.setLayoutParams(params);
            }
        });

        // set an onclickListener to all the questions to display answers
        ques2.setOnClickListener(new View.OnClickListener() {
            /**
             * on click listener for the questions clicked.
             * @param v View
             */
            @Override
            public void onClick(View v) {

                ans1.setVisibility(View.GONE);
                ans3.setVisibility(View.GONE);
                ans4.setVisibility(View.GONE);
                ans2.setVisibility(View.VISIBLE);
                ans5.setVisibility(View.VISIBLE);
                ans6.setVisibility(View.VISIBLE);

                int height = 0;
                int width = 0;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                ans1.setLayoutParams(params);
                ans3.setLayoutParams(params);
                ans4.setLayoutParams(params);
                ans5.setLayoutParams(params);
                ans6.setLayoutParams(params);

                height = FrameLayout.LayoutParams.WRAP_CONTENT;
                width = FrameLayout.LayoutParams.MATCH_PARENT;

                params = new LinearLayout.LayoutParams(width, height);
                ans2.setLayoutParams(params);

            }
        });
        // set an onclickListener to all the questions to display answers
        ques3.setOnClickListener(new View.OnClickListener() {
            /**
             * on click listener for the questions clicked.
             * @param v View
             */
            @Override
            public void onClick(View v) {

                ans3.setVisibility(View.VISIBLE);
                ans1.setVisibility(View.GONE);
                ans2.setVisibility(View.GONE);
                ans4.setVisibility(View.GONE);
                ans5.setVisibility(View.GONE);
                ans6.setVisibility(View.GONE);

                int height = 0;
                int width = 0;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                ans1.setLayoutParams(params);
                ans2.setLayoutParams(params);
                ans4.setLayoutParams(params);
                ans5.setLayoutParams(params);
                ans6.setLayoutParams(params);

                height = FrameLayout.LayoutParams.WRAP_CONTENT;
                width = FrameLayout.LayoutParams.MATCH_PARENT;

                params = new LinearLayout.LayoutParams(width, height);
                ans3.setLayoutParams(params);
            }
        });

        // set an onclickListener to all the questions to display answers
        ques4.setOnClickListener(new View.OnClickListener() {
            /**
             * on click listener for the questions clicked.
             * @param v View
             */
            @Override
            public void onClick(View v) {

                ans4.setVisibility(View.VISIBLE);
                ans2.setVisibility(View.GONE);
                ans3.setVisibility(View.GONE);
                ans1.setVisibility(View.GONE);
                ans5.setVisibility(View.GONE);
                ans6.setVisibility(View.GONE);

                int height = 0;
                int width = 0;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                ans2.setLayoutParams(params);
                ans3.setLayoutParams(params);
                ans1.setLayoutParams(params);
                ans5.setLayoutParams(params);
                ans6.setLayoutParams(params);

                height = FrameLayout.LayoutParams.WRAP_CONTENT;
                width = FrameLayout.LayoutParams.MATCH_PARENT;

                params = new LinearLayout.LayoutParams(width, height);
                ans4.setLayoutParams(params);
            }
        });

        // set an onclickListener to all the questions to display answers
        ques5.setOnClickListener(new View.OnClickListener() {
            /**
             * on click listener for the questions clicked.
             * @param v View
             */
            @Override
            public void onClick(View v) {

                ans5.setVisibility(View.VISIBLE);
                ans2.setVisibility(View.GONE);
                ans3.setVisibility(View.GONE);
                ans1.setVisibility(View.GONE);
                ans4.setVisibility(View.GONE);
                ans6.setVisibility(View.GONE);

                int height = 0;
                int width = 0;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                ans2.setLayoutParams(params);
                ans3.setLayoutParams(params);
                ans1.setLayoutParams(params);
                ans4.setLayoutParams(params);
                ans6.setLayoutParams(params);

                height = FrameLayout.LayoutParams.WRAP_CONTENT;
                width = FrameLayout.LayoutParams.MATCH_PARENT;

                params = new LinearLayout.LayoutParams(width, height);
                ans5.setLayoutParams(params);
            }
        });

        // set an onclickListener to all the questions to display answers
        ques6.setOnClickListener(new View.OnClickListener() {
            /**
             * on click listener for the questions clicked.
             * @param v View
             */
            @Override
            public void onClick(View v) {

                ans6.setVisibility(View.VISIBLE);
                ans2.setVisibility(View.GONE);
                ans3.setVisibility(View.GONE);
                ans1.setVisibility(View.GONE);
                ans4.setVisibility(View.GONE);
                ans5.setVisibility(View.GONE);

                int height = 0;
                int width = 0;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                ans2.setLayoutParams(params);
                ans3.setLayoutParams(params);
                ans1.setLayoutParams(params);
                ans4.setLayoutParams(params);
                ans5.setLayoutParams(params);

                height = FrameLayout.LayoutParams.WRAP_CONTENT;
                width = FrameLayout.LayoutParams.MATCH_PARENT;

                params = new LinearLayout.LayoutParams(width, height);
                ans6.setLayoutParams(params);
            }
        });

        // Set a toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
}

