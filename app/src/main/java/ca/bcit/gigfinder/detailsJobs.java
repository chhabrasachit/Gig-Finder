/*
 * This class represents the jobs Details.
 */
package ca.bcit.gigfinder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Provides the details of the jobs.
 */
public class detailsJobs extends AppCompatActivity {
    /**
     * Loads when the activity is created.
     * @param savedInstanceState :Bundle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_jobs);
    }
}
