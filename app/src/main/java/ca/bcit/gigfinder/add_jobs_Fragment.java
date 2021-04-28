/*
    THIS CLASS REPRESENTS ADD JOBS FRAGMENT.
 */
package ca.bcit.gigfinder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * add_jobs_fragment: allows us to add any
 * jobs we want to add to get people to work.
 */
public class add_jobs_Fragment extends Fragment {

    //variables
    private int len;

    private ListView lvJobs;
    private List <String> jobs = new ArrayList<>();
    private Map<String, String> map;
    private List <String> Jobs = new ArrayList<>();

    private boolean mIsCreated;

    private AlertDialog dialog;

    public add_jobs_Fragment() {}

    /**
     * Loads when the fragment is initialized
     * @param inflater : LayoutInflater
     * @param container : ViewGroup
     * @param savedInstanceState : Bundle
     * @return View
     */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_add_jobs_, container, false);

            ImageView addImg;
            addImg = v.findViewById(R.id.addImg);

            lvJobs = v.findViewById(R.id.lvJobs);
            listData();
            mIsCreated = true;

        addImg.setOnClickListener(new View.OnClickListener() {

            /**
             * On click listener on the addImg button:
             * to allow user to add job title and description
             * @param v : View
             */
            @Override
            public void onClick(View v) {

                Context context = getActivity();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                AlertDialog.Builder alertDialog;
                alertDialog = new AlertDialog.Builder(getActivity(),R.style.MyDialogTheme);
                alertDialog.setTitle("Add New Job");

                // Job title textView
                final EditText titleBox = new EditText(context);
                titleBox.setId(R.id.titleBox);
                titleBox.setHintTextColor(Color.rgb(255,179,0));
                titleBox.setHint("Title of the Job");
                titleBox.setTextColor(Color.rgb(255,179,0));
                layout.addView(titleBox); // Notice this is an add method

                // Description textView
                final EditText descriptionBox = new EditText(context);
                descriptionBox.setId(R.id.desc);
                descriptionBox.setHintTextColor(Color.rgb(255,179,0));
                descriptionBox.setHint("Description of the Job");
                descriptionBox.setTextColor(Color.rgb(255,179,0));

                layout.addView(descriptionBox); // Another add method

                alertDialog.setView(layout);
                alertDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            /**
                             * On click method for the alert dialog:
                             * checks the fields
                             * if not empty: adds to the database.
                             * @param dialog : DialodInterface
                             * @param which : Integer
                             */
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                String title = titleBox.getText().toString();
                                String desc = descriptionBox.getText().toString();

                                // If the fields are empty
                                if(TextUtils.isEmpty((title)) || TextUtils.isEmpty(desc)){
                                    Toast.makeText(getActivity(),
                                            "Fields are Empty",Toast.LENGTH_LONG);

                                }else {
                                    // Write a message to the database
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    assert user != null;
                                    // Database reference
                                    final DatabaseReference myRef = FirebaseDatabase.getInstance()
                                            .getReference("Users").child(user.getUid());

                                    final Profile j = new Profile(title,desc);
                                    myRef.push().setValue(j);

                                    Toast.makeText(getActivity(),
                                            "Job Created", Toast.LENGTH_SHORT).show();
                                    updateData();
                                }
                            }
                        });

                 dialog = alertDialog.create();
                dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    /**
                     * On show for the alert dialog.
                     * @param arg0 : DialogInterface.
                     */
                    @Override
                    public void onShow(DialogInterface arg0) {

                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).
                                setTextColor(Color.rgb(255,179,0));
                    }
                });

                dialog.show();
            }
        });
        return v;
    }

    /**
     * Lists the data in the listView that was initially there in the database.
     */
    private void listData(){
        if (!mIsCreated) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            assert user != null;
            final DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("Users").child(user.getUid());
            ref.addListenerForSingleValueEvent(new ValueEventListener() {

                /**
                 * Retrieval of the data.
                 * @param dataSnapshot : DataSnapshot
                 */
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        for (DataSnapshot ds2 : ds.getChildren()) {

                            GenericTypeIndicator<Map<String, String>> to = new
                                    GenericTypeIndicator<Map<String, String>>() {
                                    };
                            map = ds.getValue(to);
                            jobs.add(String.valueOf(map.get("jobTitle")));
                        }
                    }
                    for (int i = 0; i < jobs.size(); i = i + 2) {
                        Jobs.add(jobs.get(i));
                    }
                    len = (int) Math.pow(Jobs.size(),2);

                    ArrayAdapter adapter = new ArrayAdapter<String>
                            (Objects.requireNonNull(getActivity()),
                            android.R.layout.simple_list_item_1, Jobs)
                    {
                        /**
                         * Getter for the TextView for the ArrayAdapter.
                         * @param position : Integer
                         * @param convertView : View
                         * @param parent : ViewGroup
                         * @return View
                         */

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent){
                            // Get the Item from ListView
                            View view = super.getView(position, convertView, parent);

                            // Initialize a TextView for ListView each Item
                            TextView tv = view.findViewById(android.R.id.text1);

                            // Set the text color of TextView (ListView Item)
                            tv.setTextColor(Color.rgb(255,179,0));

                            // Generate ListView Item using TextView
                            return view;
                        }
                    };
                    lvJobs.setAdapter(adapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }
        else{
            ArrayAdapter adapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
                    android.R.layout.simple_list_item_1, Jobs)
            {
                /**
                 * Getter for the TextView for the ArrayAdapter.
                 * @param position : Integer
                 * @param convertView : View
                 * @param parent : ViewGroup
                 * @return View
                 */
                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    // Get the Item from ListView
                    View view = super.getView(position, convertView, parent);

                    // Initialize a TextView for ListView each Item
                    TextView tv = view.findViewById(android.R.id.text1);

                    // Set the text color of TextView (ListView Item)
                    tv.setTextColor(Color.rgb(255,179,0));

                    // Generate ListView Item using TextView
                    return view;
                }
            };
            lvJobs.setAdapter(adapter);
        }
    }

    /**
     * Updates the listView when jobs added to the already created jobs
     */
    private void updateData(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        final DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("Users").child(user.getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener(){

            /**
             * Retrieval of the data.
             * @param dataSnapshot : DataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    for(DataSnapshot ds2 : ds.getChildren()){
                        GenericTypeIndicator<Map<String, String>> to = new
                                GenericTypeIndicator<Map<String, String>>() {};
                        map = ds.getValue(to);
                    }
                }
                jobs.add(String.valueOf(map.get("jobTitle")));
                Jobs.add(jobs.get(jobs.size()-1));

                ArrayAdapter adapter = new ArrayAdapter<String>(
                        Objects.requireNonNull(getActivity()),
                        android.R.layout.simple_list_item_1, Jobs)
                {
                    /**
                     * Getter for the TextView for the ArrayAdapter.
                     * @param position : Integer
                     * @param convertView : View
                     * @param parent : ViewGroup
                     * @return View
                     */
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                        // Get the Item from ListView
                        View view = super.getView(position, convertView, parent);

                        // Initialize a TextView for ListView each Item
                        TextView tv = view.findViewById(android.R.id.text1);

                        // Set the text color of TextView (ListView Item)
                        tv.setTextColor(Color.rgb(255,179,0));

                        // Generate ListView Item using TextView
                        return view;
                    }
                };
                lvJobs.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}