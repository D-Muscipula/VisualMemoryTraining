package com.example.visualmemorytraining.data;

public class User {
    public String email, id;
public int scores;
    //public User(String key, String email, int scores) {
    //


    public User() {
    }

    public User(String id, String email, int scores) {
        this.id = id;
        this.email = email;
        this.scores = scores;
    }
}
