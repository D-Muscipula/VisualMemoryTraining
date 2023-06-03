package com.example.visualmemorytraining.data;
//Класс данных кнопки
public class Data {
    String name;
    String color ="";
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
