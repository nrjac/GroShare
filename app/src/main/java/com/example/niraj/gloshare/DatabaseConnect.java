package com.example.niraj.gloshare;


import com.example.niraj.gloshare.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.database.ValueEventListener;

/**
 * Created by Niraj on 9/12/2017.
 */

public class DatabaseConnect {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    private static final String TAG = MainActivity.class.getSimpleName();
    DatabaseConnect(){

        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference();
        /*ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Post post = dataSnapshot.getValue(Post.class);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mPostReference.addValueEventListener(postListener);*/

    }
    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mFirebaseDatabase.child("users").child(userId).setValue(user);
    }
    /**
     * Creating new user node under 'users'
     */
    private void createUser(String name, String email) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        User user = new User(name, email);

        mFirebaseDatabase.child(userId).setValue(user);

        addUserChangeListener();
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.getName() + ", " + user.getEmail());

                // Display newly updated name and email
                //txtDetails.setText(user.name + ", " + user.email);
                Log.d(TAG, "onDataChange: new user :"+ user.getName());
                // clear edit text
                //inputEmail.setText("");
                //inputName.setText("");

               // toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateUser(String name, String email) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(email))
            mFirebaseDatabase.child(userId).child("email").setValue(email);
    }






    public User getUser(DataSnapshot ds){
        User us= ds.getValue(User.class);
        // Check for null
        if (us == null) {
            Log.e(TAG, "User data is null!");
            return null;
        }
        return us;
    }

}
