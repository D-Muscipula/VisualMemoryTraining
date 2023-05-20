package com.example.visualmemorytraining.data;

public class Data {
    String name;
    //String isDone = "No";
    //TODO свойство для изменения цвета при выполнении задания
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data(String name) {
        this.name = name;
    }
}
