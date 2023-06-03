package com.example.visualmemorytraining.data;

import java.util.ArrayList;
//Класс задания(набор вопросов)
public class Task {
    private ArrayList<Question> list;

    public Task(ArrayList<Question> list) {
        this.list = list;
    }

    public ArrayList<Question> getList() {
        return list;
    }
}
