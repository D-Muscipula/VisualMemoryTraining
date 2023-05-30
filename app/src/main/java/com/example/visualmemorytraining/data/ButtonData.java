package com.example.visualmemorytraining.data;

import java.util.ArrayList;
import java.util.List;

public class ButtonData {
    //TODO свойство для изменения цвета при выполнении задания(получение из сохраненных данных)
    //map?
    public static ArrayList<Data> getSource(){
        ArrayList<Data> list = new ArrayList<>();
        for (int i = 1; i < 26; i++) {
            Data button = new Data(String.valueOf(i));
            list.add(button);
        }
        return list;
    }
}
