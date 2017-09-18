package com.example.niraj.gloshare.model;

/**
 * Created by Niraj on 9/12/2017.
 */

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ravi Tamada on 07/10/16.
 * www.androidhive.info
 */

@IgnoreExtraProperties
public class User {

    private String name;
    private String email;
    private int id;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    // Empty constructor
    public User(){

    }
    // constructor
    public User(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email =email;
    }

    // constructor
    public User(String name, String email) {
        this.setName(name);
        this.setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}