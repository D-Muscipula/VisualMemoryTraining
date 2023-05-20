package com.example.visualmemorytraining.data;

import com.example.visualmemorytraining.data.Question;

import java.util.ArrayList;

public class Task {
    //image
    private ArrayList<Question> list;

    public Task(ArrayList<Question> list) {
        this.list = list;
    }
    public ArrayList<Question> getList() {
        return list;
    }
}
