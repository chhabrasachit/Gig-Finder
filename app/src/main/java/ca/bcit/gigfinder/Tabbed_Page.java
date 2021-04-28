/*
    This class represents the tabbed activity.
 */
package ca.bcit.gigfinder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

// This is Tabbed-Page Activity that contains fragments like add_jobs, browse_jobs

/**
 * Tabbed Page: shows all the tabs
 */
public class Tabbed_Page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Initializes the Layout

    TabLayout tabLayout;
    ViewPager viewPager;

    /**
     * loads when the activity is created.
     * @param savedInstanceState :Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed__page);

        // Set a toolbar to the tabbed page

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get an instance to drawer_layout

        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        toggle.getDrawerArrowDrawable().setColor(Color.BLACK);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        // Get an instance of firebase to get information of current user

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(user.getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            /**
             * Retrieval of the data
             * @param dataSnapshot :DataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Profile p = dataSnapshot.getValue(Profile.class);
                TextView email = findViewById(R.id.email);
                assert p != null;
                email.setText(p.getEmail());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(Color.BLACK);
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources()
                .getDisplayMetrics().density));
    }

    // Set a navigation Drawer to Tabbed Page

    /**
     * Navigation bar:
     * initializes the fragments/ activities according to the position clicked.
     * @param item :MenuItem
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;

        switch(id) {

            case R.id.nav_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.nav_help:
                intent = new Intent(this, HelpActivity.class);
                break;
            case R.id.nav_feedback:
                intent = new Intent(this, FeedbackActivity.class);
                break;
            default:
                fragment = new HomeFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        } else {
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Sets up the tabs for the activity.
     * @param viewPager :ViewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        Intent intent = getIntent();
        int extrasPosition;
        if(intent == null) {
            extrasPosition = 0;
        } else {
            extrasPosition = intent.getIntExtra("Position", 0);
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new add_jobs_Fragment(),"Add Jobs");
        adapter.addFrag(new add_jobs_Fragment(),"Browse Jobs");
        adapter.addFrag(new add_jobs_Fragment(),"Browse Events");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(extrasPosition, true);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        /**
         * Opens the tabs of the activity according to the position
         * @param index :integer
         * @return Fragment
         */
        @NonNull
        @Override
        public Fragment getItem(int index) {

            switch (index) {
                case 0:
                    // Top Rated fragment activity
                    return new add_jobs_Fragment();
                case 1:
                    // Games fragment activity
                    return new browse_jobs_Fragment();
                case 2:
                    // Movies fragment activity
                    return new find_events();
            }
            return null;
        }

        /**
         * Gets the count of tabs
         * @return Integer
         */
        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        /**
         * adds the fragment to the activity
         * @param fragment : Fragment
         * @param title :String
         */
        void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        /**
         * gets the title of the tab
         * @param position :Integer
         * @return Integer
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}