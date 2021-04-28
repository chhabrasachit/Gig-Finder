/*
 * This class represents find events fragment.
 */
package ca.bcit.gigfinder;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Find events fragment:
 * allows users to find the events happening around.
 */

public class find_events extends Fragment {

    // Variables.
    private ListView lvEvents;
    private List<String> jobs = new ArrayList<>();
    private Map<String, String> map;
    private List <String> Jobs2 = new ArrayList<>();

    public find_events() {}

    /**
     * Loads when the fragment is initialized.
     * @param inflater : LayoutInflater
     * @param container :ViewGroup
     * @param savedInstanceState :Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_find_events, container, false);

        // Database reference.
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Events");
        // Data Retrieval
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            /**
             * Retrieval of the data
             * @param dataSnapshot :DataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    for(DataSnapshot ds2 : ds.getChildren()){
                        GenericTypeIndicator<Map<String, String>> to = new
                                GenericTypeIndicator<Map<String, String>>() {};
                        map = ds.getValue(to);
                    }

                    assert map != null;
                    String info1 = map.get("Name") + "\n" +"\n" ;
                    String info2 = map.get("URL");

                    SpannableString ss = new SpannableString(info2);
                    StyleSpan fcs = new StyleSpan(Typeface.BOLD);
                    assert info2 != null;
                    ss.setSpan(fcs,0,info2.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    String info = info1 + ss;

                    jobs.add(info);
                }
                for (int i = 0; i < 18; i++) {
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
                lvEvents.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        lvEvents = v.findViewById(R.id.listEvents);

        return v;
    }
}