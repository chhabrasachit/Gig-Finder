/*
 * This class represents the Home Fragment.
 */
package ca.bcit.gigfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

// This is the HomeFragment that displays the most important features.
/**
 * Loads when the fragment is initialized.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    /**
     * Home Fragment: Allows user to go over all the available options.
     * @param inflater : LayoutInflater
     * @param container : ViewGroup
     * @param savedInstanceState : Bundle
     * @return View
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get the View for the HomeFragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Get the context into the Animation
        Animation frombottom = AnimationUtils.loadAnimation(getContext(), R.anim.frombottom);

        // Initializes different views.
        ImageView bgApp = rootView.findViewById(R.id.bgapp);
        Animation bganim = AnimationUtils.loadAnimation(getContext(), R.anim.bganim);
        bgApp.animate().translationY(-2100).setDuration(600).setStartDelay(800);

        LinearLayout menu = rootView.findViewById(R.id.menu11);
        menu.startAnimation(frombottom);

        ImageView logo_home = rootView.findViewById(R.id.logo_home);
        bganim = AnimationUtils.loadAnimation(getContext(), R.anim.bganim);
        logo_home.animate().translationY(-0).translationX(-0)
                .setDuration(700).setStartDelay(800).scaleX(1.0F).scaleY(1.0F);

        // Get the view of Button and set an onClickListener to it.
        Button button = rootView.findViewById(R.id.browse_btn);
        button.setOnClickListener(new View.OnClickListener()
        {
            /**
             * on click listener on the browse button to initialize tabbed fragment.
             * @param v : View
             */
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), Tabbed_Page.class);
                intent.putExtra("Position",1);
                startActivity(intent);
            }
        });

        Button button2 = rootView.findViewById(R.id.add_jobs);
        button2.setOnClickListener(new View.OnClickListener()
        {
            /**
             * on click listener on the add jobs button to initialize tabbed fragment.
             * @param v : View
             */
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), Tabbed_Page.class);
                intent.putExtra("Position",0);
                startActivity(intent);
            }
        });

        Button button3 = rootView.findViewById(R.id.find_events);
        button3.setOnClickListener(new View.OnClickListener()
        {
            /**
             * on click listener on the find events button to initialize tabbed fragment.
             * @param v : View
             */
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), Tabbed_Page.class);
                intent.putExtra("Position",2);
                startActivity(intent);
            }
        });

        Button button4 = rootView.findViewById(R.id.job_training);
        button4.setOnClickListener(new View.OnClickListener()
        {
            /**
             * on click listener on the job training button to initialize tabbed fragment.
             * @param v : View
             */
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), Tabbed_Page.class);
                intent.putExtra("Position",1);
                startActivity(intent);
            }
        });

        // return the view.
        return rootView;
    }

    // Call the onClick function to go to the tabbed page
    /**
     * Opens new Activity.
     */
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getContext(), Tabbed_Page.class);
        startActivity(intent);
    }
}
