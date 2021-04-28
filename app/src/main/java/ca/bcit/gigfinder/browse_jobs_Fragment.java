/*
  This class represents Browse Jobs fragment.
 */
package ca.bcit.gigfinder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
 * Browse Jobs: allows users to browse through the Available jobs
 * and employment training along with that.
 */
public class browse_jobs_Fragment extends Fragment {
    // Variables
    private ListView lvJobs;
    private List<String> jobs = new ArrayList<>();
    private Map<String, String> map;
    private List <String> Jobs2 = new ArrayList<>();
    private boolean mIsCreated;

    public browse_jobs_Fragment() {}

    /**
     * Loads when the fragment is initialized.
     * @param inflater : LayoutInflater
     * @param container : View
     * @param savedInstanceState : Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_browse_jobs_, container, false);

        final DatabaseReference ref2 = FirebaseDatabase.getInstance()
                .getReference("Jobs");
        // Retrieval from the database.
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
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
                    jobs.add(String.valueOf(map.get("Name")));
                }
                for (int i = 0; i < 12; i++) {
                    Jobs2.add(jobs.get(i));
                }

                ArrayAdapter adapter = new ArrayAdapter<String>
                        (Objects.requireNonNull(getActivity()),
                        android.R.layout.simple_list_item_1, Jobs2)
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
            public void onCancelled(@NonNull DatabaseError databaseError) {
                ArrayAdapter adapter = new ArrayAdapter<String>
                        (Objects.requireNonNull(getActivity()),
                        android.R.layout.simple_list_item_1, Jobs2)
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
        });

        lvJobs = v.findViewById(R.id.lvBrowse);
        mIsCreated = true;

        lvJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * calls for the details of the jobs asked for.
             * @param parent : AdapterView<?>
             * @param view : View
             * @param position : Integer
             * @param id :Long
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), detailsJobs.class);
                Log.i("position", String.valueOf(position+1));
                startActivity(intent);
                intent.putExtra("Position",(position+1));
            }
        });
        return v;
    }

}