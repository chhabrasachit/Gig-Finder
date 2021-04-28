/*
    This class represents the Profile fragment.
 */
package ca.bcit.gigfinder;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

// This is ProfileFragment in the navigation drawer.

/**
 * ProfileFragment: User Profile
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    // Variables
    private ImageView imageView;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    private StorageReference storageReference;

    /**
     * Loads when the fragment is initialized.
     * @param inflater :LayoutInflater
     * @param container : ViewGroup
     * @param savedInstanceState : Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //  get the view of profile fragment

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        // get instance of different fields -- textView

        final TextView name = v.findViewById(R.id.userName);
        final TextView email = v.findViewById(R.id.email);
        final TextView phoneNumber = v.findViewById(R.id.phoneNumber);
        final TextView bioDetail = v.findViewById(R.id.detail);
        final TextView expDetail = v.findViewById(R.id.detail2);
        final TextView prefDetail = v.findViewById(R.id.detail3);

        // This is to set an image in our Profile

        // Initializes the button, imageView
        Button btnChoose = v.findViewById(R.id.btnChoose);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            /**
             * onlclick for the upload button
             * @param v View
             */
            @Override
            public void onClick(View v) {
                chooseImage();
                uploadImage();
            }
        });

        // get instance from firebase to store the image in it.

        // Get reference from firebase
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        imageView = v.findViewById(R.id.roundedimage);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(user.getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            /**
             * Retrieval of the Profile data
             * @param dataSnapshot :DataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Profile p = dataSnapshot.getValue(Profile.class);
                assert p != null;
                name.setText(p.getFullName());
                email.setText(p.getEmail());
                phoneNumber.setText(p.getPhoneNumber());
                bioDetail.setText(p.getBiography());
                expDetail.setText(p.getExperience());
                prefDetail.setText(p.getPreference());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // This is required empty OnCancelled field
            }
        });

        // set onClickListener to the buttons.
        Button button = v.findViewById(R.id.testNotify);
        button.setOnClickListener(this);

        TextView signOut = v.findViewById(R.id.textView3);
        signOut.setOnClickListener(this);

        return v;
    }

    /**
     * on onclik for buttons
     * @param v View
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.testNotify:
                alertDialog();
                break;
            case R.id.textView3:
                SignOut();
                break;
        }
    }

    // Set an alert Dialog Box to turn on notification

    /**
     * Alert dialog
     */
        private void alertDialog() {
            AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
            dialog.setMessage("Notification is turned on successfully.");
            dialog.setTitle("Notification");
            dialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            Toast.makeText(getActivity(),
                                    "Notification Tested", Toast.LENGTH_SHORT).show();
                        }
                    });

            AlertDialog alertDialog=dialog.create();
            alertDialog.show();
        }

        // This helps the user to signout.
        /**
         * Sign out the user
         */
        private void SignOut(){
            FirebaseAuth.getInstance().signOut();
            Objects.requireNonNull(getActivity()).getFragmentManager().popBackStack();
            startActivity(new Intent(getActivity(),MainActivity.class));
        }

    /**
     * Allows user to select image from their phone.
     */
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Your photo"), PICK_IMAGE_REQUEST);
    }

    /**
     * Picks Image from phone
     * @param requestCode :Integer
     * @param resultCode: Integer
     * @param data : Intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap =
                        MediaStore.Images.Media.getBitmap
                                (Objects.requireNonNull(
                                        getActivity()).getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    // This is to choose and upload image to our Profile

    /**
     * Uploads the image to storage.
     */
    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        /**
                         * On successful upload of the image
                         * @param taskSnapshot : UploadTask.TaskSnapshot
                         */
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Uploaded",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        /**
                         * on failure of the image upload
                         * @param e :Exception
                         */
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(),
                                    "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        /**
                         * On progress of the upload
                         * @param taskSnapshot : UploadTask.TaskSnapshot
                         */
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}
