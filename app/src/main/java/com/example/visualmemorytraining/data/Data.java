package com.example.visualmemorytraining.data;

public class Data {
    String name;
    String color ="";
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

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
